package ru.level.up.gdbc.hbm;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import ru.level.up.gdbc.hbm.domain.ApplicationUser;

import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Authorization {
    public static void main(String[] args) throws IOException {

        SessionFactory factory = SessionFactoryInitializer.getFactory();
        BufferedReader BF = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("Введите логин ");
        String login = BF.readLine();

        System.out.println("Введите пароль ");
        String password = BF.readLine();

        Session session = factory.openSession();

        Query query = session.createQuery("select id from ApplicationUser where login ='" + login + "'" +
                "and password ='" + password + "'");

        if (!((org.hibernate.query.Query) query).list().isEmpty()) {

            List<Integer> idUser = ((org.hibernate.query.Query) query).list();

            int id = idUser.get(0);
            //  System.out.println("ID " + id);

            Transaction t = session.beginTransaction();


            ApplicationUser getUserlog = (ApplicationUser) session.get(ApplicationUser.class, new Integer(id));

            String userlog = getUserlog.getUserLogin();
            String userpass = getUserlog.getPassword();

            if (password.equals(userpass) && login.equals(userlog)) {
                System.out.println("Авторизация прошла успешно");

            } else {
                System.out.println("Логин или Пароль не верен");
            }

            t.commit();
            session.close();

            factory.close();
        } else {
            System.out.println("Пользователь или пароль не верен");
        }

    }
}
