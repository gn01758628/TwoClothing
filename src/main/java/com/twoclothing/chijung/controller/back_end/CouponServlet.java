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

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
		
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
				List<Coupon> couponList = gs.getAll(Coupon.class);
				
				req.setAttribute("couponList", couponList);
				req.setAttribute("couponDisTypeMap", Mapping.couponDisTypeMap);
				forwardPath = "/back_end/coupon/listAllCoupon.jsp";
				break;
			case "turn_To_Add_Coupon":
				req.setAttribute("couponDisTypeMap", Mapping.couponDisTypeMap);
				forwardPath = "/back_end/coupon/addCoupon.jsp";
				break;
			case "insert_Coupon":
				
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				String cpnname = req.getParameter("cpnName");
				String cpnNameReg = "^[（\\u4e00-\\u9fa5a-zA-Z0-9_）]{1,20}$";
				if (cpnname == null || cpnname.trim().length() == 0) {
					errorMsgs.put("cpnname","優惠券名稱: 請勿空白");
				} else if(!cpnname.trim().matches(cpnNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("cpnname","優惠券名稱: 只能是中、英文字母、數字和_ , 且長度必需在20字以內");
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
            	    if (!expireDate.after(currentDate)) {
            	    	errorMsgs.put("expireDate", "結束時間不得小於或等於使用時間");
            	    } 
            	}
		        
		        int distype = Integer.parseInt(req.getParameter("distype"));
		        int disvalue = Integer.parseInt(req.getParameter("disvalue"));
		        
		        
		        if (disvalue < 0) {
		        	errorMsgs.put("disvalue", "折扣額度不能為負數");
		        } else {
		            if (distype == 1 && (disvalue > 100) ) {
		            	errorMsgs.put("disvalue", "折扣類型為折%數時，折扣額度必須在0到100之間");
		            }
		        }
		        
		        int minamount = Integer.parseInt(req.getParameter("minamount"));
		        
		        
		        if (minamount < 0) {
		        	errorMsgs.put("minamount", "最低金額條件不能為負數");
		        }
				
		        if (!errorMsgs.isEmpty()) {
					req.setAttribute("cpnName",cpnname);
			        req.setAttribute("createDate",createDateString);
			        req.setAttribute("expireDate",expireDateString);
			        req.setAttribute("distype",distype);
		        	req.setAttribute("disvalue",disvalue);
		        	req.setAttribute("minamount",minamount);
					req.setAttribute("couponDisTypeMap", Mapping.couponDisTypeMap);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/coupon/addCoupon.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
		        
		        Coupon coupon = new Coupon();
	        	coupon.setCpnName(cpnname);
	        	coupon.setCreateDate(new Timestamp(createDate.getTime()));
	        	coupon.setExpireDate(new Timestamp(expireDate.getTime()));
	        	coupon.setDisType(distype);
	        	coupon.setDisValue(disvalue);
	        	coupon.setMinAmount(minamount);
	        	coupon.setEmpId((Integer)req.getSession().getAttribute("empId"));
	        	gs.insert(coupon);
	        	req.setAttribute("success","新增成功");
	        	forwardPath = "/back_end/coupon/addCoupon.jsp";
				break;

			case "allot_Coupon":
				currentDate = new Date();
				Date allotDate = null;
				expireDate = null;
				String allotDateString = req.getParameter("allotDate");
		        expireDateString = req.getParameter("expireDate");
            	
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
            	        System.out.println(1);
            	        
            	    } catch (java.text.ParseException e) {
            	    	System.out.println(2);
            	    }
            	}
            	System.out.println(allotDateString);
            	System.out.println(expireDate);
            	
            	// 結束時間比對發布時間
            	if (allotDateString != null && expireDateString != null && !allotDateString.isEmpty() && !expireDateString.isEmpty()) {
            	    // 檢查 allotDate 是否大於 expireDate
            	    if (allotDate.after(expireDate)) {
            	    	res.getWriter().write("發放日期已超過使用期限");
    					res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    					return;
            	    } 
            	}
				
            	//jedisDAO
            	Integer cpnId = Integer.parseInt(req.getParameter("cpnId"));
            	Integer totalQuantity = Integer.parseInt(req.getParameter("totalQuantity"));
            	Integer remainingQuantity = new Integer(totalQuantity);
//            	
            	coupon = gs.getByPrimaryKey(Coupon.class, cpnId);
            	HibernateUtil.getSessionFactory().getCurrentSession().evict(coupon);
            	AllotedCouponRedisDAO allotedCouponDao = new AllotedCouponRedisDAO();
            	AllotedCoupon allotedCoupon = new AllotedCoupon(coupon,new Timestamp(allotDate.getTime()),totalQuantity,remainingQuantity);
            	allotedCouponDao.allot(allotedCoupon);
//            	
				res.getWriter().write("新增發放成功");
				res.setStatus(HttpServletResponse.SC_OK);
				break;
			case "turn_To_Allot_Coupon":
				break;
			case "get_All_Allot_Coupon":
				try (Jedis jedis = JedisPoolUtil.getJedisPool().getResource()) {
		            jedis.select(7);
		        }
				
				
				
				break;
			case "stop_Issuing_Coupon":
				
				
				
				
				
				break;
			case "receive_Coupon":
				try (Jedis jedis = JedisPoolUtil.getJedisPool().getResource()) {
		            jedis.select(7);
		         // Lua腳本
		            String luaScript = "local key = KEYS[1]\n" +
		                    "local latestData = redis.call('LINDEX', key, -1)\n" +
		                    "local data = cjson.decode(latestData)\n" +
		                    "if data.status == 1 then\n" +
		                    "    return -1\n" +
		                    "end\n" +
		                    "local remainingQuantity = tonumber(data.remainingQuantity)\n" +
		                    "if remainingQuantity > 0 then\n" +
		                    "    remainingQuantity = remainingQuantity - 1\n" +
		                    "    data.remainingQuantity = remainingQuantity\n" +
		                    "    local updatedData = cjson.encode(data)\n" +
		                    "    redis.call('LSET', key, -1, updatedData)\n" +
		                    "    return remainingQuantity\n" +
		                    "else\n" +
		                    "    return 0\n" +
		                    "end";

		            // 設定KEYS和ARGV
		            String key = "coupon123";
		            String[] keys = {key};

		            // 執行Lua腳本
		            Object result = jedis.eval(luaScript, 1, keys);

		            // 輸出結果
		            System.out.println("Remaining Quantity: " + result);
		        }
				
				
				break;
				
		}
		if(!forwardPath.isEmpty()) {
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
		
	}
	
}
