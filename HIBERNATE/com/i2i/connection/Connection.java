package com.i2i.hybernateConnection;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Connection{

    private Connection connection;

    private Connection() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(Trainer.class);
        configuration.addAnnotatedClass(Trainee.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegist

        Session session  = sessionFactory.openSession();
    }

    public static Session session() {
        if (session.isClosed() || connection == null) {
        connection = new Connection();
    }