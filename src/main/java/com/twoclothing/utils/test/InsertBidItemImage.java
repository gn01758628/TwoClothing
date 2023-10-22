package com.twoclothing.utils.test;

import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.abid.biditemimage.BidItemImageDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImageHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

// 插入有圖片的假資料(執行前先清空資料庫的假資料)
@WebServlet("/insertBidIMG")
public class InsertBidItemImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BidItemImageDAO dao = new BidItemImageHibernateDAO(HibernateUtil.getSessionFactory());
        String filepathHead = "/images/clothing/clothing-";
        String filepathTail = ".jpg";
        for (int i = 1; i <= 20; i++) {
            InputStream in = getServletContext().getResourceAsStream(filepathHead + i + filepathTail);
            byte[] bytes = in.readAllBytes();
            BidItemImage bidItemImage = new BidItemImage(i, bytes);
            dao.insert(bidItemImage);
            in.close();
        }
        String filepathHead2 = "/images/clothing/clothing-";
        String filepathTail2 = "-2.jpg";
        for (int i = 1; i <= 2; i++) {
            InputStream in = getServletContext().getResourceAsStream(filepathHead2 + i + filepathTail2);
            byte[] bytes = in.readAllBytes();
            BidItemImage bidItemImage = new BidItemImage(i, bytes);
            dao.insert(bidItemImage);
            in.close();
        }
    }
}
