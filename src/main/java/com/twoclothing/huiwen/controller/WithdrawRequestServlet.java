package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twoclothing.gordon.service.MembersService;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.huiwen.service.WithdrawRequestService;
import com.twoclothing.huiwen.service.WithdrawRequestServiceImpl;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.withdrawrequest.WithdrawRequest;

@WebServlet("/WithdrawRequest/*")
public class WithdrawRequestServlet extends HttpServlet {
	private WithdrawRequestService withdrawSvc;
	private MembersService memSvc;
	
	public void init() throws ServletException {
		withdrawSvc = new WithdrawRequestServiceImpl();
		memSvc = new MembersServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String choice = req.getParameter("choice");
		System.out.println("choice:" + choice);
		// 連去新增
		if ("add".equals(choice)) {
			//會員編號從登入取
			Integer balance = withdrawSvc.getBalanceByMbrId(1);
			req.setAttribute("balance", balance);
			String url = "/front_end/withdrawRequest/WRAdd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		// 連去搜尋，直接顯示查全部(會員用)
		if ("search".equals(choice)) {			
			HttpSession session = req.getSession();
			Integer mbrId = (Integer) session.getAttribute("mbrId");
			List<WithdrawRequest> withdrawRequestList = withdrawSvc.getAllWRByMbrId(mbrId);
			req.setAttribute("WRList", withdrawRequestList);
			
			String url = "/front_end/withdrawRequest/WRMbrSearchList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// PK搜尋
		if ("searchPK".equals(choice)) {
			Integer wrId = Integer.parseInt(req.getParameter("wrId"));
			WithdrawRequest withdrawRequest = withdrawSvc.getWRById(wrId);// 用主鍵獲取物件
			req.setAttribute("withdrawRequest", withdrawRequest);

			String url = "/front_end/withdrawRequest/listAllWithdraw.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		// 會員id搜尋
//		if ("searchMbrId".equals(choice)) {
//			Integer mbrId = Integer.parseInt(req.getParameter("mbrId"));
//			List<WithdrawRequest> withdrawRequestList = withdrawSvc.getAllWRByMbrId(mbrId);
//			req.setAttribute("WRList", withdrawRequestList);
//
//			String url = "/front_end/withdrawRequest/listAllWithdraw.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}

		// 新增虛擬錢包異動資訊
		if ("AddOne".equals(choice)) {
			WithdrawRequest withdrawRequest = new WithdrawRequest();
			//從登入取會員帳號
			String amountStr = req.getParameter("amount");
			Integer amount = 0;
			if(amountStr.length() != 0){
				amount = Integer.valueOf(amountStr.trim());				
			}else {
				res.sendRedirect(req.getContextPath()+"/front_end/withdrawRequest/WRAdd.jsp");
	            return;
			}
			
			String mbrAccount = "";
			if (req.getParameter("mbrAccount").length() != 0) {
				mbrAccount = req.getParameter("mbrAccount");
			}else {
	            res.sendRedirect(req.getContextPath()+"/front_end/withdrawRequest/WRAdd.jsp");
	            return;
			}
			Timestamp reqDate = new Timestamp(System.currentTimeMillis());

			String note = null;
			if (req.getParameter("note").length() != 0) {
				note = req.getParameter("note");
			}

			withdrawRequest.setMbrId(1);// 從登入資訊取
			withdrawRequest.setAmount(amount);
			withdrawRequest.setMbrAccount(mbrAccount);
			withdrawRequest.setReqDate(reqDate);
			withdrawRequest.setReqStatus(0);// 後台更改
			withdrawRequest.setEmpId(null);// 後台取得
			withdrawRequest.setCheckDate(null);// 後台時間
			withdrawRequest.setNote(note);

			int withdrawRequestPK = withdrawSvc.addWR(withdrawRequest);
			System.out.println(withdrawRequest);

			req.setAttribute("withdrawRequest", withdrawRequest);
		    res.setContentType("application/json;charset=UTF-8");

		    PrintWriter out = res.getWriter();
    	    
    	    String rsp = "";
    	    
    		res.setStatus(HttpServletResponse.SC_OK); // 設置響應狀態碼為200
    		
    		//validation
    	    if (true) {
        		rsp = "{\"message\": \"ok\"}";
    	    } else {
        		rsp = "{\"message\": \"out_of_e_wallet\"}";
    	    }
            out.print(rsp);
            out.flush();
		    return;
		}

		// 後台查詢
		
		// 查全部ByStatus=0(後台員工用)
		if ("getAll".equals(choice)) {
			List<WithdrawRequest> withdrawRequestList = withdrawSvc.getByStatus();
			req.setAttribute("WRList", withdrawRequestList);

			String url = "/back_end/withdrawRequest/listWithdrawBack.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		
		//查申請時間 有空再做這功能
		if ("searchReqDate".equals(choice)) {
//			Integer reqDate = Integer.parseInt(req.getParameter("reqDate"));
//			List<WithdrawRequest> withdrawRequestList = withdrawSvc.get....(reqDate);
//			req.setAttribute("WRList", withdrawRequestList);
//			
//			String url = "/front_end/withdrawRequest/listAllWithdraw.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		}

		// 查申請狀態
		if ("searchReqStatus".equals(choice)) {
			Integer reqStatus = Integer.parseInt(req.getParameter("reqStatus"));
			List<WithdrawRequest> withdrawRequestList = withdrawSvc.getAllWRByReqStatus(reqStatus);
			req.setAttribute("WRList", withdrawRequestList);

			String url = "/back_end/withdrawRequest/listWithdrawBack.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		// 修改狀態
//		if ("getStatusList".equals(choice)) {
//			System.out.println("拿到getStatusList");
//			Integer wrId = Integer.valueOf(req.getParameter("wrId"));
//			WithdrawRequest withdrawRequest = withdrawSvc.getWRById(wrId);
//			System.out.println("withdrawRequest"+withdrawRequest);
//			req.setAttribute("withdrawRequest", withdrawRequest);
//
//			String url = "/back_end/withdrawRequest/WRUpdate.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//
//		}
		// 修改狀態
		if ("UpdateStatus".equals(choice)) {
			
			int index = 0;

			List<WithdrawRequest> withdrawRequests = new ArrayList<>();

			while (true) {
			String wrIdStr = req.getParameter("wrId_" + index);
			String reqStatusStr = req.getParameter("reqStatus_" + index);

			if (wrIdStr == null || reqStatusStr == null) {
				break;
			}

			try {
				Integer wrId = Integer.valueOf(wrIdStr);

	            Integer reqStatus = Integer.valueOf(reqStatusStr);
	            WithdrawRequest withdrawRequest = withdrawSvc.getWRById(wrId);

				
				//修改會員balance金額，申請通過的才改
	            if(reqStatus == 1) {
	            	Integer withdrawAmount = withdrawRequest.getAmount();
	            	Integer balance = withdrawSvc.getBalanceByMbrId(1);
	            	Integer newAmount = balance - withdrawAmount;
	            	
	            	Members mem=memSvc.getByPrimaryKey(1);
	            	mem.setBalance(newAmount);
	            	memSvc.updateMembers(mem);
	            	System.out.println(mem);
	            	System.out.println(newAmount);
	            	
	            }
	            
	            //更新申請狀態
	            withdrawRequest.setWrId(wrId);
	            withdrawRequest.setReqStatus(reqStatus);
	            withdrawRequest.setEmpId(Integer.valueOf(1));// 後台取得
	            withdrawRequest.setCheckDate(new Timestamp(System.currentTimeMillis()));// 後台時間
	
	            withdrawRequests.add(withdrawRequest);
			} catch (NumberFormatException e) {    
				e.printStackTrace();
			}
		        index++;
		    }

		    if (!withdrawRequests.isEmpty()) {
		        // 批量更新
		    	int updatedItems = withdrawSvc.updateWR(withdrawRequests);
		    }
		    
		    //準備回應
			res.setContentType("application/json; charset=UTF-8");
    	    PrintWriter out = res.getWriter();
    	    
    	    String rsp = "";
    	    
    		res.setStatus(HttpServletResponse.SC_OK); // 設置響應狀態碼為200
    		
    		//validation
    	    if (true) {
        		rsp = "{\"message\": \"ok\"}";
    	    } else {
        		rsp = "{\"message\": \"out_of_e_wallet\"}";
    	    }
            out.print(rsp);
            out.flush();
        	return;
		}
	}
}
