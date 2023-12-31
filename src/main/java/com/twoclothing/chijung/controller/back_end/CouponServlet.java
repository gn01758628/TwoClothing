package com.twoclothing.chijung.controller.back_end;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.chijung.Mapping;
import com.twoclothing.model.coupon.Coupon;
import com.twoclothing.redismodel.allotedCoupon.AllotedCoupon;
import com.twoclothing.redismodel.allotedCoupon.AllotedCouponRedisDAO;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.JedisPoolUtil;
import com.twoclothing.utils.generic.GenericService;

import redis.clients.jedis.Jedis;


@WebServlet("/back_end/coupon/CouponServlet.do")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
public class CouponServlet extends HttpServlet {

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
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		
		switch (action) {
			case "get_All_Coupon":
				forwardPath = getAllCoupon(req,res);
				break;
			case "turn_To_Add_Coupon":
				forwardPath =turnToAddCoupon(req,res);
				break;
			case "insert_Coupon":
				forwardPath =insertCoupon(req,res);
				break;
			case "allot_Coupon":
				allotCoupon(req,res);
				break;
			case "get_All_Allot_Coupon":
				forwardPath = getAllAllotCoupon(req,res);
				break;
			case "stop_Issuing_Coupon":
				stopIssuingCoupon(req,res);
				break;
		}
		if(!forwardPath.isEmpty()) {
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
		
	}
	
	private String getAllCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Coupon> couponList = gs.getAll(Coupon.class);
		
		req.setAttribute("couponList", couponList);
		req.setAttribute("couponDisTypeMap", Mapping.couponDisTypeMap);
		return"/back_end/coupon/listAllCoupon.jsp";
		
	}
	
	private String turnToAddCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("couponDisTypeMap", Mapping.couponDisTypeMap);
		return"/back_end/coupon/addCoupon.jsp";
		
	}
	
	private String insertCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("couponDisTypeMap", Mapping.couponDisTypeMap);
		req.setAttribute("errorMsgs", errorMsgs);
		
		String cpnname = req.getParameter("cpnName");
		String cpnNameReg = "^[（\\u4e00-\\u9fa5a-zA-Z0-9_）]{1,20}$";
		if (cpnname == null || cpnname.trim().length() == 0) {
			errorMsgs.put("cpnName","優惠券名稱: 請勿空白");
		} else if(!cpnname.trim().matches(cpnNameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("cpnName","優惠券名稱: 只能是中、英文字母、數字和_ , 且長度必需在20字以內");
        }
		
		Date currentDate = new Date();
        Date createDate = null;
        
        String createDateString = req.getParameter("createDate");
        if (createDateString != null && !createDateString.isEmpty()) {
    	    try {
    	        // 將 startDateString 轉換為日期對象
    	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    	        createDate = dateFormat.parse(createDateString);

    	        // 檢查 startDate 是否大於當前時間
    	        if (!createDate.after(currentDate)) {
    	        	errorMsgs.put("createDate", "使用時間不得小於或等於當前時間");
    	        }
    	        
    	    } catch (java.text.ParseException e) {
    	        errorMsgs.put("createDate", "日期格式無效");
    	    }
    	}else {
    		errorMsgs.put("createDate", "使用日期不得為空");
    	}
        

    	Date expireDate = null;
        String expireDateString = req.getParameter("expireDate");
    	
    	if (expireDateString != null && !expireDateString.isEmpty()) {
    	    try {
    	        // 將 startDateString 轉換為日期對象
    	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    	        expireDate = dateFormat.parse(expireDateString);

    	        // 檢查 startDate 是否大於當前時間
    	        if (!expireDate.after(currentDate)) {
    	        	errorMsgs.put("expireDate", "結束時間不得小於或等於當前時間");
    	        }
    	        
    	    } catch (java.text.ParseException e) {
    	    }
    	}
    	
    	// 結束時間比對發布時間
    	if (createDateString != null && expireDateString != null && !createDateString.isEmpty() && !expireDateString.isEmpty()) {
    	    // 檢查 endDate 是否大於 startDate
    	    if (!expireDate.after(createDate)) {
    	    	errorMsgs.put("expireDate", "結束時間不得小於或等於使用時間");
    	    } 
    	}
        
        int disType = Integer.parseInt(req.getParameter("disType"));
        int disValue = Integer.parseInt(req.getParameter("disValue"));
        
        
        if (disValue < 0) {
        	errorMsgs.put("disValue", "折扣額度不能為負數");
        } else {
            if (disType == 1 && (disValue > 100) ) {
            	errorMsgs.put("disValue", "折扣類型為折%數時，折扣額度必須在0到100之間");
            }
        }
        
        int minAmount = Integer.parseInt(req.getParameter("minAmount"));
        
        
        if (minAmount < 0) {
        	errorMsgs.put("minAmount", "最低金額條件不能為負數");
        }
		
        if (!errorMsgs.isEmpty()) {
			req.setAttribute("cpnName",cpnname);
	        req.setAttribute("createDate",createDateString);
	        req.setAttribute("expireDate",expireDateString);
	        req.setAttribute("disType",disType);
        	req.setAttribute("disValue",disValue);
        	req.setAttribute("minAmount",minAmount);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back_end/coupon/addCoupon.jsp");
			failureView.forward(req, res);
			return""; //程式中斷
		}
        
        Coupon coupon = new Coupon();
    	coupon.setCpnName(cpnname);
    	coupon.setCreateDate(new Timestamp(createDate.getTime()));
    	coupon.setExpireDate(expireDate == null ? null : new Timestamp(expireDate.getTime()));
    	coupon.setDisType(disType);
    	coupon.setDisValue(disValue);
    	coupon.setMinAmount(minAmount);
    	coupon.setEmpId((Integer)req.getSession().getAttribute("empId"));
    	gs.insert(coupon);
    	req.setAttribute("success","新增成功");
    	return"/back_end/coupon/addCoupon.jsp";
		
	}
	
	private void allotCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Date currentDate = new Date();
		Date allotDate = null;
		Date expireDate = null;
		String allotDateString = req.getParameter("allotDate");
		String expireDateString = req.getParameter("expireDate");
    	
    	if (allotDateString != null && !allotDateString.isEmpty()) {
    	    try {
    	        // 將 allotDateString 轉換為日期對象
    	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    	        allotDate = dateFormat.parse(allotDateString);

    	        // 檢查 allotDate 是否大於當前時間
    	        if (!allotDate.after(currentDate)) {
    	        	res.getWriter().write("發放時間不得小於或等於當前時間");
					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					return;
    	        }
    	        
    	    } catch (java.text.ParseException e) {
    	        res.getWriter().write("日期格式無效");
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
    	    }
    	}else {
    		res.getWriter().write("發放日期不得為空");
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
    	}
    	
    	
    	
    	if (expireDateString != null && !expireDateString.isEmpty()) {
    	    try {
    	        // 將 allotDateString 轉換為日期對象
    	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    	        expireDate = dateFormat.parse(expireDateString);
    	    } catch (java.text.ParseException e) {
    	    }
    	}
    	
    	// 結束時間比對發布時間
    	if (allotDateString != null && expireDateString != null && !allotDateString.isEmpty() && !expireDateString.isEmpty()) {
    	    // 檢查 allotDate 是否大於 expireDate
    	    if (allotDate.equals(expireDate) || allotDate.after(expireDate)) {
    	    	res.getWriter().write("發放日期大於等於使用期限");
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
    	    } 
    	}
		
    	//jedisDAO
    	Integer cpnId = Integer.parseInt(req.getParameter("cpnId"));
    	Integer totalQuantity = Integer.parseInt(req.getParameter("totalQuantity"));
    	Integer remainingQuantity = new Integer(totalQuantity);
//    	
    	Coupon coupon = gs.getByPrimaryKey(Coupon.class, cpnId);
    	HibernateUtil.getSessionFactory().getCurrentSession().evict(coupon);
    	AllotedCoupon allotedCoupon = new AllotedCoupon(coupon,allotDate.getTime(),totalQuantity,remainingQuantity);
    	allotedCouponDao.allot(allotedCoupon);
//    	
		res.getWriter().write("新增發放成功");
		res.setStatus(HttpServletResponse.SC_OK);
		
		return;
		
	}
	
	private String getAllAllotCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<AllotedCoupon> allotedCouponList= allotedCouponDao.getAll();
		req.setAttribute("allotedCouponList", allotedCouponList);
		req.setAttribute("AllotedCouponStatusMap", Mapping.AllotedCouponStatusMap);
		return "/back_end/coupon/listAllAllotedCoupon.jsp";
		
	}
	
	private void stopIssuingCoupon(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer cpnId = Integer.parseInt(req.getParameter("cpnId"));
    	Integer index = Integer.parseInt(req.getParameter("index"));
    	
    	Coupon coupon = gs.getByPrimaryKey(Coupon.class, cpnId);
    	HibernateUtil.getSessionFactory().getCurrentSession().evict(coupon);
    	AllotedCoupon allotedCoupon = new AllotedCoupon();
    	allotedCoupon.setCpnId(cpnId);
    	allotedCoupon.setIndex(index);
    	int status = allotedCouponDao.stopIssuingCoupon(allotedCoupon);
    	
    	if( status == 2) {
    		res.getWriter().write(String.valueOf(status));
			res.setStatus(HttpServletResponse.SC_OK);
    	}else {
    		res.getWriter().write(String.valueOf(status));
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	}
	}
	
}
