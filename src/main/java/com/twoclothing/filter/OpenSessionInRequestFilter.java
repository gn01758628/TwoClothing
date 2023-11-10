package com.twoclothing.filter;


import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//利用Annotation來註冊()
@WebFilter("/*")
public class OpenSessionInRequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try {
            sessionFactory.getCurrentSession().beginTransaction();
            filterChain.doFilter(servletRequest, servletResponse);
            sessionFactory.getCurrentSession().getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
