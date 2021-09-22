package com.vladimir1506.rest.util;

import com.vladimir1506.rest.model.MyFile;
import com.vladimir1506.rest.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static Session getSession() {
        if (sessionFactory == null) {
            return new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(MyFile.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory()
                    .openSession();
        }
        return sessionFactory.openSession();
    }

    String getDate() {
        return new Date().toString();
    }
}
