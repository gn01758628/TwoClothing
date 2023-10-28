package com.twoclothing.huiwen.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.huiwen.service.WithdrawRequestService;
import com.twoclothing.huiwen.service.WithdrawRequestServiceImpl;
import com.twoclothing.model.withdrawrequest.WithdrawRequest;

@WebServlet("/WithdrawRequest/*")
public class WithdrawRequestServlet extends HttpServlet {
	private WithdrawRequestService withdrawSvc;

	public void init() throws ServletException {
		withdrawSvc = new WithdrawRequestServiceImpl();
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
			String url = "/front_end/withdrawRequest/WRAdd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		// 連去搜尋
		if ("search".equals(choice)) {
			String url = "/front_end/withdrawRequest/WRSearch.jsp";
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
		if ("searchMbrId".equals(choice)) {
			Integer mbrId = Integer.parseInt(req.getParameter("mbrId"));
			List<WithdrawRequest> withdrawRequestList = withdrawSvc.getAllWRByMbrId(mbrId);
			req.setAttribute("WRList", withdrawRequestList);

			String url = "/front_end/withdrawRequest/listAllWithdraw.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 查全部
		if ("getAll".equals(choice)) {
			List<WithdrawRequest> withdrawRequestList = withdrawSvc.getAllWR();
			req.setAttribute("WRList", withdrawRequestList);

			String url = "/front_end/withdrawRequest/listAllWithdraw.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		// 新增虛擬錢包異動資訊
		if ("AddOne".equals(choice)) {
			WithdrawRequest withdrawRequest = new WithdrawRequest();
			if (!req.getParameter("mbrId").isEmpty()) {
				Integer mbrId = Integer.valueOf(req.getParameter("mbrId").trim());
			}
			Integer amount = Integer.valueOf(req.getParameter("amount").trim());

			if (req.getParameter("mbrAccount").length() != 0) {
				String mbrAccount = req.getParameter("mbrAccount");
			}
			Timestamp reqDate = new Timestamp(System.currentTimeMillis());

			// 申請狀態由後台改變
			if (req.getParameter("reqStatus").length() != 0) {
				Integer reqStatus = Integer.valueOf(req.getParameter("reqStatus"));
			}

			Integer empId = null;
			if (req.getParameter("empId").length() != 0) {
				empId = Integer.valueOf(req.getParameter("empId"));
			}

			// 審核日期由後台改變
			Timestamp checkDate = new Timestamp(System.currentTimeMillis());

			String note = null;
			if (req.getParameter("note").length() != 0) {
				note = req.getParameter("note");
			}
			withdrawRequest.setMbrId(1);// 從登入資訊取
			withdrawRequest.setAmount(amount);
			withdrawRequest.setMbrAccount("364849296");// 從登入資訊取
			withdrawRequest.setReqDate(reqDate);
			withdrawRequest.setReqStatus(0);// 後台更改
			withdrawRequest.setEmpId(0);// 後台取得
			withdrawRequest.setCheckDate(null);// 後台時間
			withdrawRequest.setNote(note);

			int withdrawRequestPK = withdrawSvc.addWR(withdrawRequest);
			System.out.println(withdrawRequest);

			req.setAttribute("withdrawRequest", withdrawRequest);
			String url = "/front_end/withdrawRequest/listAllWithdraw.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 後台查詢
		
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
		if ("getStatusList".equals(choice)) {
			System.out.println("拿到getStatusList");
			Integer wrId = Integer.valueOf(req.getParameter("wrId"));
			WithdrawRequest withdrawRequest = withdrawSvc.getWRById(wrId);
			System.out.println("withdrawRequest"+withdrawRequest);
			req.setAttribute("withdrawRequest", withdrawRequest);

			String url = "/back_end/withdrawRequest/WRUpdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("UpdateStatus".equals(choice)) {
			System.out.println("拿到UpdateStatus");
			WithdrawRequest withdrawRequest = new WithdrawRequest();
			System.out.println(req.getParameter("wrId"));
			
			String wrId = req.getParameter("wrId");
			String mbrId = req.getParameter("mbrId");
			String amount = req.getParameter("amount");
			String mbrAccount = req.getParameter("mbrAccount");
			String reqDate = req.getParameter("reqDate");
			String reqStatus = req.getParameter("reqStatus");
			String empId = req.getParameter("empId");
			String note = req.getParameter("note");
			
			
			Timestamp checkDate = new Timestamp(System.currentTimeMillis());
			
			System.out.println(wrId +"/" +mbrId + "/" +amount + "/" +mbrAccount + "/" +reqDate + "/" +reqStatus+ "/" +empId+"/" +checkDate+"/" +note);

			withdrawRequest.setWrId(Integer.valueOf(wrId));
			withdrawRequest.setMbrId(Integer.valueOf(mbrId));// 從登入資訊取
			withdrawRequest.setAmount(Integer.valueOf(amount));
			withdrawRequest.setMbrAccount(mbrAccount);// 從登入資訊取

			//String->.util.Date->Timestamp
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date date = null;
			try {
				date = sdf.parse(reqDate);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			Timestamp timestamp = new Timestamp(date.getTime()); 
			withdrawRequest.setReqDate(timestamp); 
			
			withdrawRequest.setReqStatus(Integer.valueOf(reqStatus));// 後台更改
			
			
			withdrawRequest.setEmpId(Integer.valueOf(empId));// 後台取得
			
			withdrawRequest.setCheckDate(checkDate);// 後台時間
			withdrawRequest.setNote(note);
			
			
			int itemUpdate = withdrawSvc.updateWR(withdrawRequest);
			if (itemUpdate == 1) {
				System.out.println("修改成功");
				
				String url = "/back_end/withdrawRequest/WRSearchBack.jsp";
				RequestDispatcher dispatcher1 = req.getRequestDispatcher(url);
				dispatcher1.forward(req, res);				
			} else {
				System.out.println("修改失敗");

			}


		}

	}
}
