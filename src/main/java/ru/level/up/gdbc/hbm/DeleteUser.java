package ru.level.up.gdbc.hbm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.level.up.gdbc.hbm.domain.ApplicationUser;

import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteUser {

        public static void main(String[] args) throws IOException {


            SessionFactory factory = SessionFactoryInitializer.getFactory();
            BufferedReader BF = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите ID для удаления");
            String ID = BF.readLine();

            Session session = factory.openSession();
            Transaction t = session.beginTransaction();

            ApplicationUser getUser = session.get(ApplicationUser.class, new Integer(ID));
            String userLogin = getUser.getUserLogin();
            System.out.println("Пользователь с ID "+ ID + " = " + getUser.getUserLogin());
            System.out.println("Удалить пользователя (yes/no) ?");

            String result = BF.readLine();

            if (result.equalsIgnoreCase("yes") ){
                Query q = session.createQuery("delete ApplicationUser where id = '" + ID + "'");
                q.executeUpdate();
                System.out.println("Пользователь "+ userLogin + " удалён");

            }else {
                System.out.println("Пользователь не удалён");

            }

            t.commit();
            session.close();

            factory.close();

        }
    }



