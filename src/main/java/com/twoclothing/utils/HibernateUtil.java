package com.twoclothing.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


// SessionFactory是重量級的資源，加上執行緒安全
// 代表整個應用程式環境裡，SessionFacotry 物件只要創建一個出來即可
// 利用Singleton Design Pattern即可實現，稱為單例模式(一種設計模式)
//      單例模式：用於確保一個類別只有一個實體（物件）存在，並提供一個全域訪問點以獲取該實體（物件）。
//      這表示無論何時請求該類別的實體（物件），都會返回相同的實體（物件），而不是創建新的實體（物件）。
public class HibernateUtil {
    private static StandardServiceRegistry registry;

    // 1.靜態變量：在類加載時進行初始化 = 類加載時執行createSessionFactory()
    private static final SessionFactory sessionFactory = createSessionFactory();


    // 3.靜態方法：直接透過類名,取得類別的靜態實體變數sessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    // 2.創建並返回SessionFactory
    private static SessionFactory createSessionFactory() {
        try {

            registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            SessionFactory sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

            return sessionFactory;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }

    }

    public static void shutdown() {
        if (registry != null)
            StandardServiceRegistryBuilder.destroy(registry);
    }
}
