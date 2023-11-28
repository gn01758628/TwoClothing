package com.twoclothing.chijung.controller.front_end;

import java.io.IOException;
import java.util.Date;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
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
import com.twoclothing.chijung.service.MembersCouponService;
import com.twoclothing.chijung.service.MembersCouponServiceImpl;
import com.twoclothing.model.coupon.Coupon;
import com.twoclothing.model.dto.MembersCouponDTO;
import com.twoclothing.model.memberscoupon.MembersCoupon;
import com.twoclothing.model.memberscoupon.MembersCoupon.MembersCouponCompositeDetail;
import com.twoclothing.redismodel.allotedCoupon.AllotedCoupon;
import com.twoclothing.redismodel.allotedCoupon.AllotedCouponRedisDAO;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.generic.GenericService;

@WebServlet("/MemberCouponServlet.check")
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
public class MemberCouponServlet extends HttpServlet{

	private GenericService gs;
	AllotedCouponRedisDAO allotedCouponDao;
	MembersCouponService mcs;
	private static Map<Integer, SessionData> sessionDataMap = new ConcurrentHashMap<>();

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
		this.allotedCouponDao = new AllotedCouponRedisDAO();
		this.mcs = new MembersCouponServiceImpl();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}
	
	private static class SessionData {
		private HttpSession session;
	    private long lastExecutionTime;

	    public SessionData( HttpSession session ,long lastExecutionTime ) {
	        this.lastExecutionTime = lastExecutionTime;
	        this.session = session;
	    }
	    
	    public HttpSession getSession() {
	    	return session;
	    }
	    
	    public void setSession(HttpSession session) {
	    	this.session = session;
	    }
	    
	    public long getLastExecutionTime() {
	        return lastExecutionTime;
	    }
	    
	    public void setLastExecutionTime(long lastExecutionTime) {
	    	this.lastExecutionTime = lastExecutionTime;
	    }
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		
		HttpSession session = req.getSession();
		
		Integer mdrId = (Integer)session.getAttribute("mbrId");
		
		
		switch (action) {
			case "get_All_Coupon":
				forwardPath = getAllCoupon(req,res,mdrId);
				break;
			case "get_Member_Coupon":
				forwardPath = getMemberCoupon(req,res,mdrId);
				break;
			case "receive_Coupon":
				receiveCoupon(req,res,session,mdrId);
				break;
				
				
				
		}
		if(!forwardPath.isEmpty()) {
			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}
	
	
	private String getAllCoupon(HttpServletRequest req, HttpServletResponse res,Integer mdrId) throws ServletException, IOException {
		
		List<AllotedCoupon> allotedCouponList= allotedCouponDao.getAll();
		List<MembersCoupon> membersCouponList = gs.getBy(MembersCoupon.class, "compositeKey.memberId", mdrId);
		for( MembersCoupon mc : membersCouponList) {
			HibernateUtil.getSessionFactory().getCurrentSession().evict(mc);
		}
		//留下狀態 未發放 發放中 已發放完畢 同張優惠券同時存在三種狀態 則留下狀態小的 未發放->發放中->已發放完畢
		allotedCouponList = allotedCouponList.stream()
			    .filter(coupon -> coupon.getStatus() == 0 || coupon.getStatus() == 1 || coupon.getStatus() == 3)
			    .collect(Collectors.toMap(
        				AllotedCoupon::getCpnId,  // key mapper
        				Function.identity(), // value mapper (AllotedCoupon itself)
        				(existing, replacement) -> existing.getStatus() < replacement.getStatus() ? existing : replacement
        				)).values().stream()
        		.collect(Collectors.toList());
        
        // 將 membersCouponList 轉換為 Set，以加快查找效能
        Set<Integer> couponIdSet = membersCouponList.stream()
        		.map(membersCoupon -> membersCoupon.getCompositeKey().getCouponId())
        		.collect(Collectors.toSet());
        
        //代表該會員已領取過
        allotedCouponList.forEach(allotedCoupon -> {
            if (couponIdSet.contains(allotedCoupon.getCpnId())) {
                allotedCoupon.setStatus(9);
            }
        });
        //
        allotedCouponList = allotedCouponList.stream()
        	    .sorted(
        	        Comparator.comparingInt(coupon -> {
        	            int status = ((AllotedCoupon) coupon).getStatus();
        	            return status == 1 ? -1 : status;
        	        })
        	        .thenComparingLong(coupon -> ((AllotedCoupon) coupon).getAllotDate().longValue())
        	    )
        	    .collect(Collectors.toList());

		req.setAttribute("allotedCouponList", allotedCouponList);
		req.setAttribute("AllotedCouponStatusMap", Mapping.AllotedCouponStatusMap);
		req.setAttribute("membersCouponList", membersCouponList);
		
		return "/front_end/coupon/receiveCoupon.jsp";
	}
	
	private String getMemberCoupon(HttpServletRequest req, HttpServletResponse res,Integer mdrId) throws ServletException, IOException {
		
		Date currentDate = new Date();
		List<MembersCouponDTO> membersCouponDTOList = mcs.getAllMembersCouponDTOByMemberId(mdrId);
		
		
		MembersCoupon mc;
        for (MembersCouponDTO membersCouponDTO : membersCouponDTOList) {
            if (membersCouponDTO.getExpireDate() != null) {
                if (currentDate.after(membersCouponDTO.getExpireDate()) && membersCouponDTO.getCouponStatus() == 0) {
                	MembersCouponCompositeDetail mcd = new MembersCouponCompositeDetail(mdrId,membersCouponDTO.getCouponId());
                	mc = gs.getByPrimaryKey(MembersCoupon.class, mcd);
                    mc.setCouponStatus(2);
                    gs.update(mc);
                    membersCouponDTO.setCouponStatus(2);
                }
            }
        }
		
		req.setAttribute("membersCouponDTOList", membersCouponDTOList);
		req.setAttribute("couponDisTypeMap", Mapping.couponDisTypeMap);
		
		return "/front_end/coupon/memberCoupon.jsp";
	}
	
	private void receiveCoupon(HttpServletRequest req, HttpServletResponse res,HttpSession session,Integer mdrId) throws ServletException, IOException {
//		Integer recordResult = recordExecution(mdrId,session);
//		if( recordResult != null ) {
//			res.getWriter().write(String.valueOf(recordResult));
//			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}
		
		List<MembersCoupon> membersCouponList = gs.getBy(MembersCoupon.class, "compositeKey.memberId", mdrId);
		for( MembersCoupon mc : membersCouponList) {
			HibernateUtil.getSessionFactory().getCurrentSession().evict(mc);
		}
		
		Integer cpnId = Integer.parseInt(req.getParameter("cpnId"));
    	Integer index = Integer.parseInt(req.getParameter("index"));
    	
    	Coupon coupon = gs.getByPrimaryKey(Coupon.class, cpnId);
//    	HibernateUtil.getSessionFactory().getCurrentSession().evict(coupon);
    	AllotedCoupon allotedCoupon = new AllotedCoupon();
    	allotedCoupon.setCpnId(cpnId);
    	allotedCoupon.setIndex(index);
		int result = allotedCouponDao.receiveCoupon(allotedCoupon);
		
		if( result >= 0 ) {
			MembersCouponCompositeDetail mcd = new MembersCouponCompositeDetail(mdrId,cpnId);
			MembersCoupon mc = new MembersCoupon(mcd,null,0);
			gs.insert(mc);
			membersCouponList.add(mc);
			session.setAttribute("membersCouponList", membersCouponList);
			res.setStatus(HttpServletResponse.SC_OK);
    	}else {
    		res.getWriter().write("該優惠券已停止發放");
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    	}
		return;
	}
	
//	
//	// 1 同一瀏覽器1秒內  2不同瀏覽器5秒內
//	public static Integer recordExecution(Integer mbrId, HttpSession currentSession) {
//	    // 獲取現在的時間
//	    long currentTime = System.currentTimeMillis();
//
//	    // 檢查是否存在這個 mbrId
//	    if (sessionDataMap.containsKey(mbrId)) {
//	        // 如果存在，取出上次執行的時間和對應的 Session
//	        SessionData sessionData = sessionDataMap.get(mbrId);
//	        long lastExecutionTime = sessionData.getLastExecutionTime();
//	        HttpSession storedSession = sessionData.getSession();
//
//	        // 檢查當前 Session 是否和儲存的 Session 為同一個
//	        if (currentSession.equals(storedSession)) {
//	        	if (currentTime - lastExecutionTime < 1000) {
//	        		sessionData.setLastExecutionTime(currentTime);
//	    	        sessionDataMap.put(mbrId, sessionData);
//	                return 2000;
//	            }
//	        }else {
//	        	if (currentTime - lastExecutionTime < 5000) {
//	        		sessionData.setLastExecutionTime(currentTime);
//	    	        sessionDataMap.put(mbrId, sessionData);
//	                return 5000;
//	            }
//	        }
//	        sessionData.setLastExecutionTime(currentTime);
//	        sessionDataMap.put(mbrId, sessionData);
//	    }else {
//	    	// 更新或新增 SessionData
//	    	SessionData newSessionData = new SessionData( currentSession ,currentTime);
//	    	sessionDataMap.put(mbrId, newSessionData);
//	    }
//	    
//	    return null;
//	}

	
}



