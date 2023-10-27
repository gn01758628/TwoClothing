package com.twoclothing.listener;

import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.JedisPoolUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


// 利用Annotation來註冊
@WebListener
public class InitToolListener implements ServletContextListener {

    // 當webapp啟動時利用HibernateUtil.getSessionFactory(),來創建SessionFactory
    // 當在其它地方呼叫HibernateUtil.getSessionFactory()時,不會再創建新的SessionFactory
    // 而是返回HibernateUtil的實體變數sessionFactory,確保整個webapp只存在一個sessionFactory
    // 加入JedisPool,原理與SessionFactory相同
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory();
        System.out.println("Build SessionFactory");
        JedisPoolUtil.getJedisPool();
        System.out.println("Create JedisPool");
        System.currentTimeMillis();
    }

    // 當webapp關閉時關閉資源
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateUtil.shutdown();
        System.out.println("Shutdown SessionFactory");
        JedisPoolUtil.shutdown();
        System.out.println("Shutdown JedisPool");
    }
}
