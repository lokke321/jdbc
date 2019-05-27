package ru.level.up.gdbc.hbm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.level.up.gdbc.hbm.domain.ApplicationUser;

import javax.persistence.Id;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

       SessionFactory factory = SessionFactoryInitializer.getFactory();

        BufferedReader BF = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите логин");
        String login = BF.readLine();
        System.out.println("Введите пароль");
        String password = BF.readLine();


       Session session = factory.openSession();

        ApplicationUser user = new ApplicationUser();


            user.setUserLogin(login);
            user.setPassword(password);

         Transaction t = session.beginTransaction();


        Integer id = (Integer) session.save(user);
        System.out.println("Id: " +id);


        t.commit();
        session.close();

        factory.close();

    }
}
