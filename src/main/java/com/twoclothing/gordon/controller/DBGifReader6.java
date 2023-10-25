package com.twoclothing.gordon.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/DBGifReader6")
public class DBGifReader6 extends HttpServlet {
	

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String mbrid = req.getParameter("mbrid").trim();
			ResultSet rs = stmt.executeQuery(
				"SELECT shopImg01 FROM twoclothing.members where mbrid="+mbrid);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("shopImg01"));//�L����
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND); //404 p324  p134
				InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");//2g����
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");//��������
			byte[] b = in.readAllBytes();
//			byte[] b = new byte[in.available()];
//			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext(); // p182
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB"); // p182
			con = ds.getConnection(); // p182
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}