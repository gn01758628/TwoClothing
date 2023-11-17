package com.twoclothing.gordon.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.members.Members;

import at.favre.lib.crypto.bcrypt.BCrypt;
@WebServlet("/members/Members.do")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  // 2 MB
	    maxFileSize = 1024 * 1024 * 10,       // 10 MB
	    maxRequestSize = 1024 * 1024 * 50     // 50 MB
	)
public class MembersServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        System.out.println("action="+action);
        
        if ("getAll".equals(action)) { 
        	 MembersServiceImpl mbrServiceHibernate = new MembersServiceImpl();
             List<Members> members = mbrServiceHibernate.getAll();

             req.setAttribute("Members", members); 
             String url = "/back_end/members/AllMembers.jsp";
             RequestDispatcher successView = req.getRequestDispatcher(url); 
             successView.forward(req, res);
        	
        }
        
        
        
        //pk查詢
        if ("getOne_For_Display".equals(action)) { 

            Map<String, String> errorMsgs = new LinkedHashMap<>();
            req.setAttribute("errorMsgs", errorMsgs);

            // 1.接收請求參數 - 輸入格式的錯誤處理
            String str = req.getParameter("mbrId");
            if (str == null || (str.trim()).isEmpty()) {
                errorMsgs.put("mbrId", "請輸入會員編號");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back_end/members/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer mbrId = null;
            try {
                mbrId = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.put("mbrId", "會員編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back_end/members/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            // 2.開始查詢資料
            MembersServiceImpl mbrServiceHibernate = new MembersServiceImpl();
            Members members = mbrServiceHibernate.getByPrimaryKey(mbrId);
            if (members == null) {
                errorMsgs.put("mbrId", "查無資料");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back_end/members/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            // 3.查詢完成,準備轉交(Send the Success view)
            req.setAttribute("Members", members); 
            String url = "/back_end/members/listOneMembers.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); 
            successView.forward(req, res);

        }

        // update
        if ("getOne_For_Update".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<>();
            req.setAttribute("errorMsgs", errorMsgs);

            // 1.接收請求參數
            Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));

            // 2.開始查詢資料
            MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
            Members members = membersServiceImpl.getByPrimaryKey(mbrId);

            // 3.查詢完成,準備轉交(Send the Success view)
            String param = "?mbrId=" + members.getMbrId() +
                    "&mbrName=" + members.getMbrName() +
                    "&email=" + members.getEmail() +
                    "&pswdHash=" + members.getPswdHash() +
                    "&mbrStatus=" + members.getMbrStatus() +
                    "&mbrPoint=" + members.getMbrPoint() +
                    "&balance=" + members.getBalance() +
                    "&buyStar=" + members.getBuyStar() +
                    "&buyRating=" + members.getBuyRating() +
                    "&sellStar=" + members.getSellStar() +
                    "&sellRating=" + members.getSellRating() +
                    "&lastLogin=" + members.getLastLogin() +
                    "&sellScore=" + members.getSellScore() +
                    "&buyScore=" + members.getBuyScore();
            String url = "/back_end/members/update_Members_input.jsp" + param;
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        // update
        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            Map<String, String> errorMsgs = new LinkedHashMap<>();
            req.setAttribute("errorMsgs", errorMsgs);
            Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
            
            Integer mbrStatus = null;
            try {
            	mbrStatus = Integer.valueOf(req.getParameter("mbrStatus").trim());
            	if(mbrStatus<0 ||mbrStatus>2){
            		errorMsgs.put("mbrStatus", "賣家分數應該在 0 到 2 之間");
            	}
            } catch (NumberFormatException e) {
            	errorMsgs.put("mbrStatus", "帳號狀態請填數字");
            }
            
            
            
            Integer sellScore = null;
            try {
                sellScore = Integer.valueOf(req.getParameter("sellScore").trim());
                if(sellScore<0 ||sellScore>10){
                	errorMsgs.put("sellScore", "賣家分數應該在 0 到 10 之間");
                }
                
            } catch (NumberFormatException e) {
                errorMsgs.put("sellScore", "賣家分數請填數字");
            }

            Integer buyScore = null;
            try {
                buyScore = Integer.valueOf(req.getParameter("buyScore").trim());
                if(buyScore<0 ||buyScore>10){
                	errorMsgs.put("buyScore", "賣家分數應該在 0 到 10 之間");
                }
            } catch (NumberFormatException e) {
                errorMsgs.put("buyScore", "買家分數請填數字");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back_end/members/update_Members_input.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            // 2.開始修改資料
            MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
            
            Members members = membersServiceImpl.getByPrimaryKey(mbrId);
            members.setMbrStatus(mbrStatus);
            members.setBuyScore(buyScore);
            members.setSellScore(sellScore);
            membersServiceImpl.updateMembers(members);


            // 3.修改完成,準備轉交(Send the Success view)
            req.setAttribute("Members", members); // 資料庫update成功後
            String url = "/back_end/members/listOneMembers.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }

        // 註冊
        if ("register".equals(action)) {
            res.setContentType("application/json; charset=UTF-8");
            res.setCharacterEncoding("UTF-8");
            Map<String, Object> response = new HashMap<>();
            Map<String, String> errors = new HashMap<>();
            PrintWriter out = res.getWriter();
            boolean success = false;
            // 1.接收請求參數 - 輸入格式的錯誤處理
            String email = req.getParameter("email");
            String pswdHash = BCrypt.withDefaults().hashToString(12, req.getParameter("pswdHash").toCharArray()); //加密
            
            
            
            String userInputCode = req.getParameter("VerificationCode");
  
            // 驗證碼圖片的資料
            HttpSession session = req.getSession();
            String sessionCode = (String) session.getAttribute("randStr");


            MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
            // 確認email有沒有重複
            Members members = membersServiceImpl.getByEmail(email);
            if (members == null) {
                if (userInputCode != null && userInputCode.equals(sessionCode)) {

                    // 2.開始新增資料
                    membersServiceImpl.addMembers(email, pswdHash);

                    // 3.修改完成,準備轉交(Send the Success view)
//                    String url = "/members/SendEmailServlet?action=verificationEmail&email=" + email;
//                    RequestDispatcher successView = req.getRequestDispatcher(url);
//                    successView.forward(req, res);
                    success = true;
                } else {
                    errors.put("sessionCode", "驗證碼錯誤");
                }
            } else {
                errors.put("email", "email重複");
            }
            response.put("errors", errors);
            response.put("success", success);

            // 将JSON送回客户端
            out.write(new Gson().toJson(response));
            out.close();
        }

        // 登入
        //TODO
        if ("login".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<>();
            req.setAttribute("errorMsgs", errorMsgs);
            
            //記住我
//            boolean rememberMeCheckboxIsChecked = req.getParameter("rememberMe") != null;
            
            
            // 接收请求参数
            String email = req.getParameter("email2");
            String pswdHash = req.getParameter("pswdHash2");  //加密
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            Map<String, Object> response = new HashMap<>();

            // 取得session
            HttpSession session = req.getSession();
            String location = (String) session.getAttribute("location");

            // 利用email尋找會員
            MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
            Members members = membersServiceImpl.getByEmail(email);


            if (members == null) {
                errorMsgs.put("error", "帳號密碼不正確");
                sendResponse(res, response, errorMsgs, false);
                return;
            }
            BCrypt.Result result = BCrypt.verifyer().verify(pswdHash.toCharArray(), members.getPswdHash());
            if (!result.verified) {
                errorMsgs.put("error", "帳號密碼不正確");
                sendResponse(res, response, errorMsgs, false);
                return;
            }

            // 會員未驗證
            if (members.getMbrStatus() == 0) {
                response.put("mbrStatus", 0);
                errorMsgs.put("error", "帳號未驗證");
                sendResponse(res, response, errorMsgs, false);
                return;
            }
            // 帳號停權
            if (members.getMbrStatus() == 2) {
            	response.put("mbrStatus", 2);
            	errorMsgs.put("error", "帳號停權");
            	sendResponse(res, response, errorMsgs, false);
            	return;
            }

            // 會員已驗證
            session.setAttribute("user", members);
            session.setAttribute("mbrId", members.getMbrId());
            session.setAttribute("mbrStatus", members.getMbrStatus());

            // 儲存上一頁的路徑
            if (location != null) {
                response.put("location", location);
            }

            // 修改最後登入時間
            Timestamp loginDate = new Timestamp(System.currentTimeMillis());
            members.setLastLogin(loginDate);
            membersServiceImpl.updateMembers(members);

            sendResponse(res, response, errorMsgs, true);
 //TODO           
//        	// 在登入成功後處理記住我功能
//            if (rememberMeCheckboxIsChecked) {
//                // 設定長期有效的 cookie，例如過期時間設為一個月
//                Cookie rememberMeCookie = new Cookie("rememberMe", "true");
//                rememberMeCookie.setMaxAge(30 * 24 * 60 * 60); // 一個月的秒數
//                res.addCookie(rememberMeCookie);
//            }
//            
//            response.put("rememberMe", rememberMeCheckboxIsChecked);
//TODO 

        }

        //登出
        if ("logout".equals(action)) {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            session.removeAttribute("location");
            session.removeAttribute("mbrId");
            session.removeAttribute("mbrStatus");
            res.sendRedirect(req.getContextPath() + "/headerTest.html");
        }
        // 會員的個人資訊
        if ("memberProfile".equals(action)) {
        	
        	Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
        	
        	MembersServiceImpl mbrServiceHibernate = new MembersServiceImpl();
            Members members = mbrServiceHibernate.getByPrimaryKey(mbrId);

        	//查詢完成 準備轉交
        	req.setAttribute("Members", members); 
//TODO
            String url = "/front_end/members/memberProfile.jsp";
           
            RequestDispatcher successView = req.getRequestDispatcher(url); 
            successView.forward(req, res);
        
        
        }
        
        // 會員錢包
        if ("walletAndPoints".equals(action)) {
        	
        	Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
        	
        	MembersServiceImpl mbrServiceHibernate = new MembersServiceImpl();
        	Members members = mbrServiceHibernate.getByPrimaryKey(mbrId);
        	
        	//查詢完成 準備轉交
        	req.setAttribute("Members", members); 
        	String url = "/front_end/members/walletAndPoints.jsp";
        	RequestDispatcher successView = req.getRequestDispatcher(url); 
        	successView.forward(req, res);
        	
        	
        }
        //會員評分
        if ("userRating".equals(action)) {
        	
        	Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
        	
        	MembersServiceImpl mbrServiceHibernate = new MembersServiceImpl();
        	Members members = mbrServiceHibernate.getByPrimaryKey(mbrId);
        	
        	//查詢完成 準備轉交
        	req.setAttribute("Members", members); 
        	String url = "/front_end/members/userRating.jsp";
        	RequestDispatcher successView = req.getRequestDispatcher(url); 
        	successView.forward(req, res);
        	
        	
        }
        
        //忘記密碼
        if ("forgotPassword".equals(action)) {
        	
            String email = req.getParameter("email");
//            String pswdHash = PasswordHashing.hashPassword(req.getParameter("pswdHash"));  //加密
            String pswdHash = BCrypt.withDefaults().hashToString(12, req.getParameter("pswdHash").toCharArray());
            System.out.println("email="+email);
        	System.out.println(pswdHash);
        	
      
            
            MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
            Members members = membersServiceImpl.getByEmail(email);
            
            members.setPswdHash(pswdHash);
            membersServiceImpl.updateMembers(members);
            
            String url = "/front_end/members/registerLogin.jsp";
         	RequestDispatcher successView = req.getRequestDispatcher(url); 
         	successView.forward(req, res);
            
        }
        //會員修改資料

        if ("members_UpdateName".equals(action)) {
            // 讀取請求的body部分
        	Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));

            BufferedReader reader = req.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            
            JsonObject json = JsonParser.parseString(sb.toString()).getAsJsonObject();
            
            String field = json.has("field") && !json.get("field").isJsonNull() ? json.get("field").getAsString() : null;
            String newValue = json.has("newValue") && !json.get("newValue").isJsonNull() ? json.get("newValue").getAsString() : null;


            
            MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
            Members members = membersServiceImpl.getByPrimaryKey(mbrId);
            if(newValue != null) {
            	members.setMbrName(newValue);
            
            
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");

            JsonObject result = new JsonObject();
            result.addProperty("status", "success");
            result.addProperty("message", "成功更新 " + field + " 為 " + newValue);

            PrintWriter out = res.getWriter();
            out.print(result.toString());
            out.flush();
            }
        
            
            
        }
        //會員改大頭貼 商場照片01,02
        if ("members_UpdateImage".equals(action)) {
        	Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
        	Part imagePart = null;
        	
        	MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
        	Members members = membersServiceImpl.getByPrimaryKey(mbrId);
        	
        	if(req.getPart("avatar") != null) {
        		imagePart = req.getPart("avatar");
        		byte[] image = readImageData(imagePart);
        		members.setAvatar(image);
        		}
        	
        	if(req.getPart("shopImage01") != null) {
        		imagePart = req.getPart("shopImage01");
        		byte[] image = readImageData(imagePart);
        		members.setShopImg01(image);
        		}
        	
        	if(req.getPart("shopImage02") != null) {
        		imagePart = req.getPart("shopImage02");
        		byte[] image = readImageData(imagePart);
        		members.setShopImg02(image);
        		}
        	
        		membersServiceImpl.updateMembers(members);

       		
        		
        	req.setAttribute("Members", members); 
 //TODO        	 String url = "/members/Members.do?action=memberProfile&mbrId"+mbrId;
       	String url = "/front_end/members/memberProfile.jsp";
        	RequestDispatcher successView = req.getRequestDispatcher(url); 
        	successView.forward(req, res);
        
        
        
        }
        //更改密碼
        //TODO
        if ("members_UpdatePswdHash_1".equals(action)) {
        	Map<String, String> errorMsgs = new LinkedHashMap<>();
            req.setAttribute("errorMsgs", errorMsgs);
            
            // 接收请求参数
            Integer mbrId = Integer.valueOf(req.getParameter("mbrId")) ;
            String pswdHash = req.getParameter("pswdHash");  //加密
            
            System.out.println(mbrId);
            System.out.println(pswdHash);
            
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            Map<String, Object> response = new HashMap<>();

            // 利用email尋找會員
            MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
            Members members = membersServiceImpl.getByPrimaryKey(mbrId);
            

            BCrypt.Result result = BCrypt.verifyer().verify(pswdHash.toCharArray(), members.getPswdHash());
            if (!result.verified) {
            	System.out.println("aaaaa");
                errorMsgs.put("error", "帳號密碼不正確");
                sendResponse(res, response, errorMsgs, false);
                return;
            }
            System.out.println("cccccc");
            sendResponse(res, response, errorMsgs, true);

            
            
        }
        if ("members_UpdatePswdHash_2".equals(action)) {
            Integer mbrId = Integer.valueOf(req.getParameter("mbrId"));
          String pswdHash = BCrypt.withDefaults().hashToString(12, req.getParameter("pswdHash").toCharArray());

      	
    
          
          MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
          Members members = membersServiceImpl.getByPrimaryKey(mbrId);
          
          members.setPswdHash(pswdHash);
          membersServiceImpl.updateMembers(members);
          
          String url = "/members/Members.do?action=memberProfile&mbrId"+mbrId;
       	RequestDispatcher successView = req.getRequestDispatcher(url); 
       	successView.forward(req, res);
          
        	
        }
        
            
        
        	
        
    }
    


	private byte[] readImageData(Part imagePart) throws IOException {
	    InputStream inputStream = imagePart.getInputStream();
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	    byte[] buffer = new byte[8192]; // 8 KB buffer
	    int bytesRead;
	    while ((bytesRead = inputStream.read(buffer)) != -1) {
	        byteArrayOutputStream.write(buffer, 0, bytesRead);
	    }

	    return byteArrayOutputStream.toByteArray();
	}

    private void sendResponse(HttpServletResponse res, Map<String, Object> response, Map<String, String> errorMsgs, boolean success)
            throws IOException {
    	
        response.put("success", success);
        
        response.put("errors", errorMsgs);
        
        PrintWriter out = res.getWriter();
        out.write(new Gson().toJson(response));
        out.close();
    }
}
