package com.twoclothing.chengHan.controller;

import com.twoclothing.chengHan.service.BidItemFrontService;
import com.twoclothing.chengHan.service.BidItemFrontServiceImpl;
import com.twoclothing.model.abid.biditembrowsing.BidItemBrowsing;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.utils.test.generic.GenerciHibernateDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;


// @MultipartConfig
//  fileSizeThreshold = 檔案小於這個值,檔案寫入內存,提高效率
//  maxFileSize = 單個檔案大小限制
//  maxRequestSize = 單個請求全部檔案的加總限制
//  單位是bytes( 1024bytes = 1KB )
//  超過maxFileSize或maxRequestSize都會拋出IegalStateException

@WebServlet("/servlet/front/biditem/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BidItemFrontServlet extends HttpServlet {

    // 一個Servlet物件對應一個Service物件
    private BidItemFrontService bidItemFrontService;
    GenerciHibernateDAO<BidItemBrowsing> employeeHDAO = new GenerciHibernateDAO<>(BidItemBrowsing.class);

    @Override
    public void init() throws ServletException {
        bidItemFrontService = new BidItemFrontServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<h1>");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            out.print(name + " = " + Arrays.toString(request.getParameterValues(name)) + "<br>");
        }

        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            String fileName = part.getSubmittedFileName();
            if(fileName != null && !fileName.isEmpty() && part.getContentType()!=null) {
                InputStream inputStream = part.getInputStream();
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();
                out.print(buffer + "<br>");
            }
        }


        out.print("</h1>");
    }
}
