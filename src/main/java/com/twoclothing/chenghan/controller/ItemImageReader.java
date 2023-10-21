package com.twoclothing.chenghan.controller;

import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.abid.biditemimage.BidItemImageDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImageHibernateDAO;
import com.twoclothing.model.aproduct.itemimage.ItemImage;
import com.twoclothing.model.aproduct.itemimage.ItemImageDAO;
import com.twoclothing.model.aproduct.itemimage.ItemImageHibernateDAO;
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

@WebServlet("/ReadItemIMG/*")
public class ItemImageReader extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath() + request.getPathInfo();

        if ("/ReadItemIMG/biditem".equals(servletPath)) {
            readBidItemImage(request, response);
        } else if ("/ReadItemIMG/item".equals(servletPath)) {
            readItemImage(request, response);
        }

    }


    /**
     * 取得指定編號商品的第n張圖片
     */
    private void readBidItemImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            // bidItemId
            int id = Integer.parseInt(request.getParameter("id"));
            // position = 第幾張圖片
            int position = Integer.parseInt(request.getParameter("position"));

            // 輸出圖片
            BidItemImageDAO bidItemImageDAO = new BidItemImageHibernateDAO(sessionFactory);
            BidItemImage bidItemImage = bidItemImageDAO.getPositionImageByBidItemId(id, position);
            if (bidItemImage != null) {
                byte[] image = bidItemImage.getImage();
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

    private void readItemImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int position = Integer.parseInt(request.getParameter("position"));
            ItemImageDAO itemImageDAO = new ItemImageHibernateDAO(sessionFactory);
            ItemImage itemImage = itemImageDAO.getPositionImageByItemId(id, position);
            if (itemImage != null) {
                byte[] image = itemImage.getImage();
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


    public void readNoIMG(ServletOutputStream outputStream) throws IOException {
        InputStream in = getServletContext().getResourceAsStream("/images/NoIMG.png");
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        outputStream.write(bytes);
        in.close();
        outputStream.close();
    }
}
