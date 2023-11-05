package com.twoclothing.gordon.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.twoclothing.gordon.service.*;
@MultipartConfig
@WebServlet("/bidorderratingimage/BidOrderRatingImage.do")
public class BidOrderRatingImageServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		BidOrderRatingImageServiceImpl bidOrderRatingImageServiceImpl = new BidOrderRatingImageServiceImpl();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		/***********************新增*************************/
		/***********************新增*************************/
		/***********************新增*************************/
		/***********************新增*************************/
		/***********************新增*************************/

		if ("insert".equals(action)) { // 來自select_page.jsp的請求
			
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer bidOrderId = Integer.valueOf(req.getParameter("bidOrderId"));
	        
	        // 獲取上傳的圖片
	        Part imagePart = req.getPart("image");
	        
	        byte[] image = readImageData(imagePart);
		
			/*************************** 2.開始查詢資料 *****************************************/
	        bidOrderRatingImageServiceImpl.addBidOrderRatingImage(bidOrderId, image);
			
			
		}
		
		
		
		
		
		
	}

	private byte[] readImageData(Part imagePart) throws IOException {
        InputStream inputStream = imagePart.getInputStream();
        byte[] imageData = new byte[(int) imagePart.getSize()];
        inputStream.read(imageData);
        return imageData;
    }
	

}
