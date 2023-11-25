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
import javax.servlet.http.HttpSession;

import com.twoclothing.chijung.Mapping;
import com.twoclothing.model.coupon.Coupon;
import com.twoclothing.model.memberscoupon.MembersCoupon;
import com.twoclothing.redismodel.allotedCoupon.AllotedCoupon;
import com.twoclothing.redismodel.allotedCoupon.AllotedCouponRedisDAO;
import com.twoclothing.utils.HibernateUtil;
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
		
		HttpSession session = req.getSession();
		
		List<MembersCoupon> membersCouponList = (List<MembersCoupon>) session.getAttribute("membersCouponList");
		
		if( membersCouponList == null ) {
			Integer mdrId = (Integer)session.getAttribute("mbrId");
			membersCouponList = gs.getBy(MembersCoupon.class, "compositeKey.memberId", mdrId);
			session.setAttribute("membersCouponList", membersCouponList);
		}
		
		switch (action) {
			case "get_All_Coupon":
				forwardPath = getAllCoupon(req,res,membersCouponList);
				break;
			case "get_Member_Coupon":
				forwardPath = "/front_end/coupon/memberCoupon.jsp";
				break;
			case "receive_Coupon":
				Integer cpnId = Integer.parseInt(req.getParameter("cpnId"));
		    	Integer index = Integer.parseInt(req.getParameter("index"));
		    	
		    	Coupon coupon = gs.getByPrimaryKey(Coupon.class, cpnId);
		    	HibernateUtil.getSessionFactory().getCurrentSession().evict(coupon);
		    	AllotedCoupon allotedCoupon = new AllotedCoupon();
		    	allotedCoupon.setCpnId(cpnId);
		    	allotedCoupon.setIndex(index);
				System.out.println(allotedCoupon);
				allotedCouponDao.receiveCoupon(allotedCoupon);
				break;
				
				
				
		}
		if(!forwardPath.isEmpty()) {
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}
	
	
	private String getAllCoupon(HttpServletRequest req, HttpServletResponse res,List<MembersCoupon> membersCouponList) throws ServletException, IOException {
		List<AllotedCoupon> allotedCouponList= allotedCouponDao.getAll();
		
		allotedCouponList = allotedCouponList.stream()
	    .filter(coupon -> coupon.getStatus() == 0 || coupon.getStatus() == 1 || coupon.getStatus() == 3)
	    .collect(Collectors.toList());
		
		req.setAttribute("allotedCouponList", allotedCouponList);
		req.setAttribute("AllotedCouponStatusMap", Mapping.AllotedCouponStatusMap);
		req.setAttribute("membersCouponList", membersCouponList);
		
		return "/front_end/coupon/receiveCoupon.jsp";
	}
	
	private void getMemberCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		return;
	}
	
	private void receiveCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		return;
	}
	
}



