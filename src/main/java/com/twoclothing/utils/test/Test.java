package com.twoclothing.utils.test;

import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

public class Test {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        SessionFactory sessionFactory1 = HibernateUtil.getSessionFactory();
        System.out.println(sessionFactory);
        System.out.println(sessionFactory1);
    }
}
