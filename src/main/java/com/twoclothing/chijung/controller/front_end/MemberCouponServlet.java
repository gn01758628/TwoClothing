package com.twoclothing.chijung.controller.front_end;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.chijung.Mapping;
import com.twoclothing.redismodel.allotedCoupon.AllotedCoupon;
import com.twoclothing.redismodel.allotedCoupon.AllotedCouponRedisDAO;
import com.twoclothing.utils.generic.GenericService;

@WebServlet("/MemberCouponServlet.check")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
public class MemberCouponServlet extends HttpServlet{




	private GenericService gs;
	AllotedCouponRedisDAO allotedCouponDao;

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
		this.allotedCouponDao = new AllotedCouponRedisDAO();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		
		switch (action) {
			case "get_All_Coupon":
				forwardPath = getAllCoupon(req,res);
				break;
			case "get_Member_Coupon":
				
				break;
			case "receive_Coupon":
				
				break;
				
				
				
		}
		if(!forwardPath.isEmpty()) {
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}
	
	
	private String getAllCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<AllotedCoupon> allotedCouponList= allotedCouponDao.getAll();
		
		allotedCouponList = allotedCouponList.stream()
	    .filter(coupon -> coupon.getStatus() == 0 || coupon.getStatus() == 1 || coupon.getStatus() == 3)
	    .collect(Collectors.toList());
		
		req.setAttribute("allotedCouponList", allotedCouponList);
		req.setAttribute("AllotedCouponStatusMap", Mapping.AllotedCouponStatusMap);
		
		return "/front_end/coupon/receiveCoupon.jsp";
	}
	
	private void getMemberCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		return;
	}
	
	private void receiveCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		return;
	}
	
}



