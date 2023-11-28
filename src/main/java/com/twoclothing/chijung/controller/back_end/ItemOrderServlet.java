package com.twoclothing.chijung.controller.back_end;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.chijung.Mapping;
import com.twoclothing.model.aproduct.itemorder.ItemOrder;
import com.twoclothing.utils.generic.GenericService;


@WebServlet("/back_end/ItemOrderServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50,       // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class ItemOrderServlet extends HttpServlet{
	
	private GenericService gs;

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    List <ItemOrder> itemOrderList = gs.getAll(ItemOrder.class);
	    req.setAttribute("itemOrderList", itemOrderList);
	    req.setAttribute("OrderStatusMap", Mapping.OrderStatusMap);
	    
	    res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/back_end/itemOrder/listAllItemOrder.jsp");
		dispatcher.forward(req, res);
	    
	}


    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req,res);
    }

   
	
}

