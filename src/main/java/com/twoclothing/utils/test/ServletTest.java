package com.twoclothing.utils.test;

import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.JedisPoolUtil;
import org.hibernate.SessionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class ServletTest extends HttpServlet {

    private final SessionFactory  sessionFactory = HibernateUtil.getSessionFactory();

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // HibernateUtil Test
        System.out.println("SessionFactory：" + sessionFactory);
        // JedisPoolUtil Test
        System.out.println("JedisPool：" + jedisPool);
        Jedis jedis = jedisPool.getResource();
        // 選擇自己的空間
        jedis.select(15);
        jedis.set("test","HelloRedis");
        System.out.println(jedis.get("test"));
        // 關閉資源
        jedis.close();
    }
}
