package ru.level.up.gdbc.hbm;

        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.Transaction;
        import ru.level.up.gdbc.hbm.domain.ApplicationUser;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;

public class SelectInID {
    public static void main(String[] args) throws IOException {


        SessionFactory factory = SessionFactoryInitializer.getFactory();
        BufferedReader BF = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите ID");
        String i = BF.readLine();

        Session session = factory.openSession();

        ApplicationUser getUser = session.get(ApplicationUser.class, new Integer(i));
        System.out.println("Пользователь с ID "+ i + " = " +getUser.getUserLogin());

        Transaction t = session.beginTransaction();

        t.commit();
        session.close();

        factory.close();

    }
}
