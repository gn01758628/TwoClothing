//package com.twoclothing.tonyhsieh.controller;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Date;
//import java.sql.Timestamp;
//import java.util.Collection;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//import com.twoclothing.model.abid.biditemreport.BidItemReport;
//import com.twoclothing.model.department.Department;
//import com.twoclothing.model.employee.Employee;
//import com.twoclothing.tonyhsieh.service.BidItemReportService;
//import com.twoclothing.tonyhsieh.service.BidItemReportServiceImpl;
//import com.twoclothing.tonyhsieh.service.DepartmentService;
//import com.twoclothing.tonyhsieh.service.DepartmentServiceImpl;
//import com.twoclothing.tonyhsieh.service.EmployeeServiceImpl;
//
//
//@WebServlet("/front_end/biditemreport/BidItemReport.do")
//@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize = 5*1024*1024,maxRequestSize =5*5*1024*1024 )
//public class BidItemReportServlet extends HttpServlet {
//	
//	private BidItemReportService bidItemReportService;
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doPost(req, resp);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		
//		if("getAll".equals(action)) {
//	
//				/***************************1.開始查詢資料***************************************/
//				BidItemReportService bidItemReportServiceImpl = new BidItemReportServiceImpl();
//				List<BidItemReport> list = bidItemReportServiceImpl.getAll();
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)***********/								
//				req.setAttribute("list", list);
//				String url = "/front_end/biditemreport/listAllbidItRe.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 查詢成功後,轉交回送出來源網頁
//				successView.forward(req, resp);
//
//			}
//		
//		
//		if ("getCompositeQuery_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			Map<String, String[]> map = req.getParameterMap();
//			if (map != null) {
//				BidItemReportServiceImpl bidItemReportServiceImpl = new BidItemReportServiceImpl();
//				List<BidItemReport> BidItReList = bidItemReportServiceImpl.getBidITReportByCompositeQuery(map);
//				req.setAttribute("BidItReList", BidItReList);
//			} 
//			String url = "/front_end/biditemreport/listCompositeQuerybidItRe.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, resp);
//		
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//				/***************************1.接收請求參數****************************************/
//				Integer reportid = Integer.valueOf(req.getParameter("reportId"));
//				
//				/***************************2.開始查詢資料****************************************/
//				BidItemReportService bidItemReportService = new BidItemReportServiceImpl();
//				BidItemReport bidItemReport = bidItemReportService.getByPrimaryKey(reportid);
//						
//				String empid = String.valueOf(bidItemReport.getEmpId());
//
//				if(empid=="null" ) {
//					empid = "";
//				}
//		
//				
//				String note = bidItemReport.getNote();
//				if(note==null) {
//					note =" ";
//				}
//							
//			
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				String param = "?reportId="  +bidItemReport.getReportId()+
//						       "&bidItemId="  +bidItemReport.getBidItemId()+
//						       "&mbrId="    +bidItemReport.getMbrId()+
//						       "&empId="    +empid+
//						       "&reportDate="+bidItemReport.getReportDate()+
//						       "&description="    +bidItemReport.getBidDescription()+
//						       "&bidStatus="   +bidItemReport.getBidStatus()+
//						       "&auditDate=" +bidItemReport.getAuditDate()+
//								"&result=" +bidItemReport.getResult()+
//								"&note=" +note;
//				String url = "update_bidItRe_input.jsp"+param;
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, resp);
//		}
//				
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//				/***************************1.接收請求參數 **********************/
//			
//			Integer reportid = Integer.valueOf(req.getParameter("reportid").trim());
//			
//			Integer biditemid = Integer.valueOf(req.getParameter("bidItemid").trim());
//				
//			
//			Integer mbrid =Integer.valueOf(req.getParameter("mbrid").trim());
//						
//			
//		
//			Integer empid = Integer.valueOf(req.getParameter("empid").trim());
//			
//			
//			
//			Timestamp reportdate = Timestamp.valueOf(req.getParameter("reportdate"));
//			
//			
//			String biddescription = req.getParameter("description").trim();
//		
//			
//			Integer bidstatus = Integer.valueOf(req.getParameter("bidstatus").trim());
//			
//			
//			
//			Date currentDate = new Date();
//	        Timestamp auditdate = new Timestamp(currentDate.getTime());
//			
////			String dateStr = req.getParameter("auditdate");
////			Timestamp auditdate=null;
////			try {
////			    // 解析日期字符串并将其转换为Timestamp
////			    Date date = Date.valueOf(dateStr);
////			    auditdate = new Timestamp(date.getTime());
////			    // 现在，timestamp 包含了日期的Timestamp表示
////			    // 可以将它用于数据库操作或其他需要Timestamp的操作
////			} catch (IllegalArgumentException e) {
////			    // 处理日期格式错误
////			    e.printStackTrace();
////			}
//						
//			Integer result = Integer.valueOf(req.getParameter("result").trim());
//							
//			String note = req.getParameter("note").trim();
//		
//		   
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/biditemreport/update_bidItRe_input.jsp");
//					failureView.forward(req, resp);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//						
//				BidItemReportServiceImpl bidItemReportServiceImpl = new BidItemReportServiceImpl();
//				BidItemReport bidItemReport = bidItemReportServiceImpl.update(reportid, biditemid, mbrid, empid, reportdate, biddescription, bidstatus, auditdate , result, note);
//								
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("BidItemReport", bidItemReport); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/front_end/biditemreport/listOnebidItRe.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, resp);
//		}
//
//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			
//				/***********************1.接收請求參數 *************************/
//			Integer reportid = Integer.valueOf(req.getParameter("reportid").trim());
//			Integer biditemid = Integer.valueOf(req.getParameter("biditemid").trim());
//			
//	
//			Integer mbrid =Integer.valueOf(req.getParameter("mbrid").trim());
//			
//			Integer empid = null;
//			
//			
//			Date currentDate = new Date();
//	        Timestamp reportdate = new Timestamp(currentDate.getTime());
//		
//	        String biddescription = req.getParameter("description").trim();
//	        
//	        Integer bidstatus = Integer.valueOf(req.getParameter("bidstatus").trim());
//	        	        
//	        Timestamp auditdate = null;
//	        
//			Integer result = Integer.valueOf(req.getParameter("result").trim());
//			
//			String note =null;
//	        
//				
//				/***************************2.開始新增資料***************************************/
//			BidItemReportServiceImpl bidItemReportServiceImpl = new BidItemReportServiceImpl();
//			bidItemReportServiceImpl.addBidItemReport(reportid,biditemid, mbrid, empid, reportdate, biddescription, bidstatus, auditdate, result, note);				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/front_end/biditemreport/select_page.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, resp);				
//		}
//		
//		
//	
//	}
//	
//
//	
//}
