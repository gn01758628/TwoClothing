package com.twoclothing.chijung.controller.back_end;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.twoclothing.model.latestnews.LatestNews;
import com.twoclothing.utils.generic.GenericService;


@WebServlet("/back_end/LatestNewsServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50,       // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class LatestNewsServlet extends HttpServlet {
	
	private GenericService gs;

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    String action = req.getParameter("action");
	    
	    
	    if (action != null) {
	        switch (action) {
	            case "add":
	            	
	            	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
	            	
	    			//標題
	            	String title = req.getParameter("title");
	            	if (title == null || title.isEmpty()) {
	            		errorMsgs.put("title", "標題不得為空");
	            	}else {
	            		
	            	}
	            	
	            	Date currentDate = new Date();
	            	
	            	//發布時間
	            	Date startDate = null;
	            	String startDateString = req.getParameter("startDate");
	            	
	            	if (startDateString != null && !startDateString.isEmpty()) {
	            	    try {
	            	        // 將 startDateString 轉換為日期對象
	            	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	            	        startDate = dateFormat.parse(startDateString);

	            	        // 檢查 startDate 是否大於當前時間
	            	        if (!startDate.after(currentDate)) {
	            	        	errorMsgs.put("startDate", "發布時間不得小於或等於當前時間");
	            	        }
	            	        
	            	    } catch (java.text.ParseException e) {
	            	        errorMsgs.put("startDate", "日期格式無效");
	            	    }
	            	} 
	            	
	            	
	            	//結束時間
	            	Date endDate = null;
	            	String endDateString = req.getParameter("endDate");
	            	
	            	if (endDateString != null && !endDateString.isEmpty()) {
	            	    try {
	            	        // 將 startDateString 轉換為日期對象
	            	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	            	        endDate = dateFormat.parse(endDateString);

	            	        // 檢查 startDate 是否大於當前時間
	            	        if (!endDate.after(currentDate)) {
	            	        	errorMsgs.put("endDate", "結束時間不得小於或等於當前時間");
	            	        }
	            	        
	            	    } catch (java.text.ParseException e) {
	            	        errorMsgs.put("endDate", "日期格式無效");
	            	    }
	            	}
	            	
	            	// 結束時間比對發布時間
	            	if (startDateString != null && endDateString != null && !startDateString.isEmpty() && !endDateString.isEmpty()) {
	            	    // 檢查 endDate 是否大於 startDate
	            	    if (!endDate.after(startDate)) {
	            	    	errorMsgs.put("endDate", "結束時間不得小於或等於發布時間");
	            	    } 
	            	}
	            	
	            	// 圖片
	            	Part filePart = req.getPart("coverImage");

	            	if (filePart != null && filePart.getSize() > 0) {
	            	    String contentType = filePart.getContentType();

	            	    if (contentType != null && contentType.startsWith("image")) {
	            	        try {
	            	            ImageInfo imageInfo = null;
	            	            try {
	            	                imageInfo = Imaging.getImageInfo(filePart.getInputStream(), "file.jpg");
	            	            } catch (ImageReadException e) {
	            	                e.printStackTrace();
	            	            }
	            	            if (imageInfo != null && imageInfo.getFormat() != null) {
	            	                // 文件是有效的圖片文件
	            	            } else {
	            	                errorMsgs.put("coverImage", "損毀或無效的圖片內容");
	            	            }
	            	        } catch (IOException e) {
	            	            // 發生異常，文件無法正確讀取
	            	            e.printStackTrace();
	            	        }
	            	    } else {
	            	        errorMsgs.put("coverImage", "請上傳正確的圖片格式檔案");
	            	    }
	            	} 
	            	

	            	//內容
	            	String content = req.getParameter("content");
	            	if (content == null || content.isEmpty()) {
	            		errorMsgs.put("content", "內容不得為空");
	            	}

	            	if (!errorMsgs.isEmpty()) {
	            		res.setContentType("application/json; charset=UTF-8");
	            	    PrintWriter out = res.getWriter();
	            		res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 設置響應狀態碼為400
	            		String errorMessage = new Gson().toJson(errorMsgs);
	                    out.print(errorMessage);
	                    out.flush();
	                    return;
					}
	            	
	            	//生成要新增的物件
	            	LatestNews latesNews = new LatestNews();
	            	
	            	latesNews.setTitle(title);
	            	
	            	if(startDate!=null) {
	            		latesNews.setStartDate(new Timestamp(startDate.getTime()));
	            	}
	            	if(endDate!=null) {
	            		latesNews.setEndDate(new Timestamp(endDate.getTime()));
	            	}
	            	
	            	if ( filePart.getSize() > 0) {
	            		try (InputStream inputStream = filePart.getInputStream()) {
	            			byte[] coverImage = null;
		            	    // 讀取輸入流中的數據為字節數組
		            	    coverImage = inputStream.readAllBytes();
		            	    latesNews.setCoverImage(coverImage);
		            	} catch (IOException e) {
		            	    e.printStackTrace();
		            	}
	            	}
	            	
	            	latesNews.setContent(content);
	            	latesNews.setEmpId(3);
	            	
	            	//新增
	            	gs.insert(latesNews);
	            	
	            	
	            	
	                // 處理新增操作的邏輯
	            	res.setContentType("text/html; charset=UTF-8");
            	    PrintWriter out = res.getWriter();
	                out.println("OK");
	                break;
	            case "update":
	                // 處理更新操作的邏輯
	                // ...
	                break;
	            case "delete":
	                // 處理刪除操作的邏輯
	                // ...
	                break;
	            default:
	                // 處理未知的action值，例如回傳錯誤訊息
	                PrintWriter errorOut = res.getWriter();
	                errorOut.println("Unknown action: " + action);
	                break;
	        }
	    } else {
	        // 如果action為null，可以回傳錯誤訊息或者其他處理方式
	        PrintWriter errorOut = res.getWriter();
	        errorOut.println("Action is missing in the request.");
	    }
	    
	    
	}


    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req,res);
    }

   
    
}
