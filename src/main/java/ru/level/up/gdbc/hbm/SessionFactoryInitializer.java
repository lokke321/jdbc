package ru.level.up.gdbc.hbm;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryInitializer {

    private static SessionFactory factory;

    static {
        Configuration configuration = new Configuration()
                .configure();
//        ServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .build();

        factory = configuration.buildSessionFactory();
    }


    public static SessionFactory getFactory() {
        return factory;
    }
}
