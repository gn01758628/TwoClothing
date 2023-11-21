package com.twoclothing.utils.test;

import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.JedisPoolUtil;
import org.hibernate.SessionFactory;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hello")
public class ServletTest extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final JedisPool jedisPool = JedisPoolUtil.getJedisPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryTags> list = (List<CategoryTags>) getServletContext().getAttribute("categoryTags");
        for (CategoryTags tag : list) {
            System.out.println(tag);
        }
    }
}
