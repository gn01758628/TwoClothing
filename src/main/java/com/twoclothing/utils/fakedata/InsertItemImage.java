package com.twoclothing.utils.fakedata;

import com.twoclothing.model.aproduct.itemimage.ItemImage;
import com.twoclothing.model.aproduct.itemimage.ItemImageDAO;
import com.twoclothing.model.aproduct.itemimage.ItemImageHibernateDAO;
import com.twoclothing.utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

// 插入有圖片的假資料(執行前先清空資料庫的假資料)
@WebServlet("/insertItemIMG")
public class InsertItemImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ItemImageDAO dao = new ItemImageHibernateDAO(HibernateUtil.getSessionFactory());
        String filepathHead = "/images/clothing/clothing-";
        String filepathTail = ".jpg";
        for (int i = 1; i <= 20; i++) {
            InputStream in = getServletContext().getResourceAsStream(filepathHead + i + filepathTail);
            byte[] bytes = in.readAllBytes();
            ItemImage itemImage = new ItemImage(i, bytes);
            dao.insert(itemImage);
            in.close();
        }
        for (int i = 21; i <= 40; i++) {
            InputStream in = getServletContext().getResourceAsStream(filepathHead + (i - 20) + filepathTail);
            byte[] bytes = in.readAllBytes();
            ItemImage itemImage = new ItemImage(i, bytes);
            dao.insert(itemImage);
            in.close();
        }
        for (int i = 41; i <= 50; i++) {
            InputStream in = getServletContext().getResourceAsStream(filepathHead + (i - 40) + filepathTail);
            byte[] bytes = in.readAllBytes();
            ItemImage itemImage = new ItemImage(i, bytes);
            dao.insert(itemImage);
            in.close();
        }
        String filepathHead2 = "/images/clothing/clothing-";
        String filepathTail2 = "-2.jpg";
        for (int i = 1; i <= 2; i++) {
            InputStream in = getServletContext().getResourceAsStream(filepathHead2 + i + filepathTail2);
            byte[] bytes = in.readAllBytes();
            ItemImage itemImage = new ItemImage(i, bytes);
            dao.insert(itemImage);
            in.close();
        }
    }
}
