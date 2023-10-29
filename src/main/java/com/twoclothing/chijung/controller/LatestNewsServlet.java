package com.twoclothing.chijung.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/LatestNewsServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50,       // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class LatestNewsServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
	    Part filePart = req.getPart("context");
	    String fileName = filePart.getSubmittedFileName();
	    
	    // 读取上传文件的内容
	    InputStream fileContent = filePart.getInputStream();
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    int bytesRead;
	    byte[] data = new byte[1024];
	    while ((bytesRead = fileContent.read(data, 0, data.length)) != -1) {
	        buffer.write(data, 0, bytesRead);
	    }
	    buffer.flush();
	    byte[] fileBytes = buffer.toByteArray();
	    
	    // 输出文件内容到控制台
	    System.out.println("Uploaded File Content:");
	    System.out.println(new String(fileBytes, "UTF-8")); // 假设文件内容是UTF-8编码的
	    
	    // 在这里可以对文件内容进行处理，比如保存到服务器上的特定位置
	    // ...
	    
	    // 关闭输入流
	    fileContent.close();
	    
	    // 返回客户端响应
	    PrintWriter out = res.getWriter();
	    out.println(new String(fileBytes, "UTF-8"));
	}


    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req,res);
    }

   
    
}
