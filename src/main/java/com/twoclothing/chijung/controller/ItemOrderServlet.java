package com.twoclothing.chijung.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.members.Members;
import com.twoclothing.utils.generic.GenericService;

@WebServlet("/front_end/itemorder/itemorder.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ItemOrderServlet extends HttpServlet{
	
	private GenericService gs;

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		
		switch (action) {
			case "getmemberitems":
				List<Members> memberList = gs.getAll(Members.class);
				List<Item> itemList = gs.getAll(Item.class);
				
				req.setAttribute("memberList", memberList);
				req.setAttribute("itemList", itemList);
				forwardPath = "/front_end/itemorder/index.jsp";
				break;
			case "compositeQuery":
//				forwardPath = getCompositeEmpsQuery(req, res);
				System.out.println("compositeQuery");
				break;
			default:
//				forwardPath = "/index.jsp";
				System.out.println("default");
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
}
