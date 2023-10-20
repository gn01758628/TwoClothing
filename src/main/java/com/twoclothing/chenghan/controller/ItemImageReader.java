package com.twoclothing.chenghan.controller;

import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.abid.biditemimage.BidItemImageDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImageHibernateDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/ReadIMG/*")
public class ItemImageReader extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath() + request.getPathInfo();

        if ("/ReadIMG/biditem".equals(servletPath)) {
            readBidItemImage(request, response);
        }

    }

    /**
     * 取得指定編號商品的第n張圖片
     */
    private void readBidItemImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        // bidItemId
        int id = Integer.parseInt(request.getParameter("id"));
        // position = 第幾張圖片
        int position = Integer.parseInt(request.getParameter("position"));

        // 輸出圖片
        ServletOutputStream outputStream = response.getOutputStream();
        BidItemImageDAO bidItemImageDAO = new BidItemImageHibernateDAO(sessionFactory);
        BidItemImage bidItemImage = bidItemImageDAO.getPositionImageByBidItemId(id, position);
        if (bidItemImage != null) {
            byte[] image = bidItemImage.getImage();
            outputStream.write(image);
            outputStream.close();
        } else {
            // 找不到圖片&沒輸入參數 一起處理
            InputStream in = getServletContext().getResourceAsStream("/images/NoIMG.png");
            byte[] bytes = new byte[in.available()];
            outputStream.write(bytes);
            in.close();
            outputStream.close();
        }
    }
}
