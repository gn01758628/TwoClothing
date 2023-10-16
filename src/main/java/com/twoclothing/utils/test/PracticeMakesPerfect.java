package com.twoclothing.utils.test;

import com.twoclothing.model.abid.biditemimage.BidItemImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


@WebServlet("/practice")
public class PracticeMakesPerfect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InputStream resourceAsStream = getServletContext().getResourceAsStream("/images/clothing/clothes-01.jpg");
        byte[] bytes1 = new byte[resourceAsStream.available()];
        resourceAsStream.read(bytes1);
        resourceAsStream.close();

        InputStream resourceAsStream2 = getServletContext().getResourceAsStream("/images/clothing/clothes-02.jpg");
        byte[] bytes2 = new byte[resourceAsStream2.available()];
        resourceAsStream2.read(bytes2);
        resourceAsStream2.close();

        InputStream resourceAsStream3 = getServletContext().getResourceAsStream("/images/clothing/pants-01.jpg");
        byte[] bytes3 = new byte[resourceAsStream3.available()];
        resourceAsStream3.read(bytes3);
        resourceAsStream3.close();

        BidItemImage b1 = new BidItemImage(1,10,bytes1);
        BidItemImage b2 = new BidItemImage(1,10,bytes2);
        BidItemImage b3 = new BidItemImage(1,11,bytes3);

    }
}
