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

public class upDatePassword {

        public static void main(String[] args) throws IOException {

            SessionFactory factory = SessionFactoryInitializer.getFactory();
            BufferedReader BF = new BufferedReader(new InputStreamReader(System.in));


            System.out.println("Введите логин ");
            String login = BF.readLine();

            Session session = factory.openSession();

            Query query = session.createQuery("select id from ApplicationUser where login ='" + login + "'");

            if(!((org.hibernate.query.Query)query).list().isEmpty()) {

            List<Integer> idUser = ((org.hibernate.query.Query) query).list();

               int id =  idUser.get(0);
             //  System.out.println("ID " + id);



            Transaction t = session.beginTransaction();


                ApplicationUser getUserlog = (ApplicationUser) session.get(ApplicationUser.class, new Integer(id));


                   String userpass = getUserlog.getPassword();

                    System.out.println("Введите пароль для пользоввтеля " + getUserlog.getUserLogin());
                    String password = BF.readLine();

                    if (password.equals(userpass)) {
                        System.out.println("Введите новый пароль ");
                        String newPass = BF.readLine();
                        getUserlog.setPassword(newPass);
                        session.update(getUserlog);
                        System.out.println("Пароль изменён");

                    } else {
                        System.out.println("Пароль не верен");
                    }

                t.commit();
                session.close();

                factory.close();
            }
           System.out.println("Пользователь не найден");
        }
    }




