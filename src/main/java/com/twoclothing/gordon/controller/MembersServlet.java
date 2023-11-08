package com.twoclothing.gordon.controller;

import com.google.gson.Gson;
import com.twoclothing.gordon.service.MembersServiceImpl;
import com.twoclothing.model.members.Members;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/members/Members.do")
public class MembersServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
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
            Integer sellScore = null;
            try {
                sellScore = Integer.valueOf(req.getParameter("sellScore").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("sellScore", "賣家分數請填數字");
            }

            Integer buyScore = null;
            try {
                buyScore = Integer.valueOf(req.getParameter("buyScore").trim());
            } catch (NumberFormatException e) {
                errorMsgs.put("buyScore", "賣家分數請填數字");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/back_end/members/update_Members_input.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            // 2.開始修改資料
            MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
            Members members = membersServiceImpl.updateMembers(mbrId, sellScore, buyScore);

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
            String pswdHash = req.getParameter("pswdHash");
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
                    String url = "/members/SendEmailServlet?action=verificationEmail&email=" + email;
                    RequestDispatcher successView = req.getRequestDispatcher(url);
                    successView.forward(req, res);
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
        if ("login".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<>();
            req.setAttribute("errorMsgs", errorMsgs);

            // 接收请求参数
            String email = req.getParameter("email2");
            String pswdHash = req.getParameter("pswdHash2");

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

            if (!members.getPswdHash().equals(pswdHash)) {
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

            // 會員已驗證
            session.setAttribute("user", members);
            session.setAttribute("mbrId", members.getMbrId());
            session.setAttribute("mbrStatus", members.getMbrStatus());

            // 儲存上一頁的路徑
            if (location != null) {
                session.removeAttribute("location");
                response.put("location", location);
            }

            // 修改最後登入時間
            Timestamp loginDate = new Timestamp(System.currentTimeMillis());
            members.setLastLogin(loginDate);
            membersServiceImpl.updateMembers(members);

            sendResponse(res, response, errorMsgs, true);
        }

        //登出
        if ("logout".equals(action)) {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            session.removeAttribute("location");
            session.removeAttribute("mbrId");
            session.removeAttribute("mbrStatus");
            res.sendRedirect(req.getContextPath() + "/index.jsp");
        }
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
