package com.twoclothing.gordon.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/DBGifReader5")
public class DBGifReader5 extends HttpServlet {
	

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
			String imageid = null;
			String imageidValue = req.getParameter("imageId");
			if (imageidValue != null) {
			    // 現在你可以安全地在 parameterValue 上調用 trim() 方法
			    imageid = imageidValue.trim();
			}
			
			String mbrid = null;
			String mbridValue = req.getParameter("mbrid");
			if (mbridValue != null) {
			    // 現在你可以安全地在 parameterValue 上調用 trim() 方法
			    mbrid = mbridValue.trim();
			}
			
			String imgType = null;
	        String imgTypeValue = req.getParameter("imgType");
	        if (imgTypeValue != null) {
	        	imgType = imgTypeValue.trim();
	        }else {imgType = "xx";  //隨意給的
	        	
	        }
	        
	        System.out.println("imageid="+ imageid);
	        System.out.println("mbrid="+mbrid);
	        System.out.println("imgType="+imgType);
	        ResultSet rs = null;
	        
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			
			if(mbrid != null) 
			rs = stmt.executeQuery(
					"SELECT " + imgType + " FROM twoclothing.members where mbrid=" + mbrid);
			
			if(imageid != null) 	
				rs = stmt.executeQuery(
						"SELECT " + imgType + " FROM twoclothing.bidorderratingimage where imageid=" + imageid);
			
			

			
	
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(imgType));//�L����
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND); //404 p324  p134
				InputStream in = getServletContext().getResourceAsStream("/images/NoIMG.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/images/NoIMG.png");
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