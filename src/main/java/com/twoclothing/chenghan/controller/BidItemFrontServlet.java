package com.twoclothing.chenghan.controller;

import com.twoclothing.chenghan.service.BidItemFrontService;
import com.twoclothing.chenghan.service.BidItemFrontServiceImpl;
import com.twoclothing.model.categorytags.CategoryTags;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


// @MultipartConfig
//  fileSizeThreshold = 檔案小於這個值,檔案寫入內存,提高效率
//  maxFileSize = 單個檔案大小限制
//  maxRequestSize = 單個請求全部檔案的加總限制
//  單位是bytes( 1024bytes = 1KB )
//  超過maxFileSize或maxRequestSize都會拋出IegalStateException

@WebServlet("/front/biditem/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class BidItemFrontServlet extends HttpServlet {

    // 一個Servlet物件對應一個Service物件
    private BidItemFrontService bidItemFrontService;

    @Override
    public void init() throws ServletException {
        bidItemFrontService = new BidItemFrontServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 獲取servlet path
        String servletPath = request.getServletPath() + request.getPathInfo();
        // 裡用路徑來判斷執行哪個方法
        // 設計思路等同 HttpServlet 裡的 service(Http..)方法
        if ("/front/biditem/add".equals(servletPath)) {
            doAdd(request, response);
        } else if ("/front/biditem/save".equals(servletPath)) {
            doSave(request,response);
        }


    }


    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        List<CategoryTags> allCategoryTags = bidItemFrontService.getAllCategoryTags();
        request.setAttribute("categoryTags",allCategoryTags);
        request.getRequestDispatcher("/front_end/biditem/addBid.jsp").forward(request,response);
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);

    }
}
