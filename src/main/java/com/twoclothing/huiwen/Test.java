package com.twoclothing.huiwen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.item.ItemDAO;
import com.twoclothing.model.aproduct.item.ItemHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

@WebServlet("/Test")
public class Test extends HttpServlet{
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

		 ItemDAO itemDAO = new ItemHibernateDAO(HibernateUtil.getSessionFactory());
		 Item pk = itemDAO.getByPrimaryKey(1);
		 PrintWriter out = response.getWriter();
		 out.println(pk);
	    }

}
