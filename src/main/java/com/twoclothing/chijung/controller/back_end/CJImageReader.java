package com.twoclothing.chijung.controller.back_end;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.abid.biditemimage.BidItemImageDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImageHibernateDAO;
import com.twoclothing.model.aproduct.itemimage.ItemImage;
import com.twoclothing.model.aproduct.itemimage.ItemImageDAO;
import com.twoclothing.model.aproduct.itemimage.ItemImageHibernateDAO;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.latestnews.LatestNews;
import com.twoclothing.utils.generic.DAOSelector;
import com.twoclothing.utils.generic.GenericDAO;
import com.twoclothing.utils.generic.GenericService;

@WebServlet("/CJImageReader/*")
public class CJImageReader extends HttpServlet {
	
	private GenericService gs;

//	@Override
	public void init() throws ServletException {
		this.gs = gs.getInstance();
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String servletPath = request.getServletPath() + request.getPathInfo();

        if ("/CJImageReader/Employee".equals(servletPath)) {
        	readEmployeeAvatar(request,response);
        } else if ("/CJImageReader/LatestNews".equals(servletPath)) {
        	readLatestNewsCoverImage(request,response);
        }

    }


    private void readEmployeeAvatar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        response.setContentType("image/gif");
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            // 輸出圖片
            Employee emp = gs.getByPrimaryKey(Employee.class, id);
            if (emp != null) {
                byte[] image = emp.getAvatar();
                outputStream.write(image);
                outputStream.close();
            } else {
                // 找不到圖片&沒輸入參數 一起處理
                readNoIMG(outputStream);
            }
        } catch (Exception e) {
            readNoIMG(outputStream);
        }
    }
    
    private void readLatestNewsCoverImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        response.setContentType("image/gif");
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            // 輸出圖片
            LatestNews latestNews = gs.getByPrimaryKey(LatestNews.class, id);
            if (latestNews != null) {
                byte[] image = latestNews.getCoverImage();
                outputStream.write(image);
                outputStream.close();
            } else {
                // 找不到圖片&沒輸入參數 一起處理
                readNoIMG(outputStream);
            }
        } catch (Exception e) {
            readNoIMG(outputStream);
        }
    }

//    private void readItemImage(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("image/gif");
//        ServletOutputStream outputStream = response.getOutputStream();
//        try {
//            int id = Integer.parseInt(request.getParameter("id"));
//            int position = Integer.parseInt(request.getParameter("position"));
//            ItemImageDAO itemImageDAO = new ItemImageHibernateDAO(sessionFactory);
//            ItemImage itemImage = itemImageDAO.getPositionImageByItemId(id, position);
//            if (itemImage != null) {
//                byte[] image = itemImage.getImage();
//                outputStream.write(image);
//                outputStream.close();
//            } else {
//                // 找不到圖片&沒輸入參數 一起處理
//                readNoIMG(outputStream);
//            }
//        } catch (Exception e) {
//            readNoIMG(outputStream);
//        }
//    }


    public void readNoIMG(ServletOutputStream outputStream) throws IOException {
        InputStream in = getServletContext().getResourceAsStream("/images/NoIMG.png");
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        outputStream.write(bytes);
        in.close();
        outputStream.close();
    }
}
