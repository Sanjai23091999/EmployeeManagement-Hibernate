package com.i2i.util;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.i2i.entity.Trainee;
import com.i2i.entity.Trainer;


public class Connection{

    private static Connection connection;
    private static Session session = null;

    private Connection() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            configuration.addAnnotatedClass(Trainer.class);
            configuration.addAnnotatedClass(Trainee.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            session  = sessionFactory.openSession();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Session hibernateConnection()  {
        if (session == null || !session.isOpen())  {
            connection = new Connection();
        }
        return session;
    }
}