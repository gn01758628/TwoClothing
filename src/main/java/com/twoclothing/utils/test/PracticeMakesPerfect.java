package com.twoclothing.utils.test;

import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.abid.biditem.BiditemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/practice")
public class PracticeMakesPerfect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BiditemDAO biditemDAO = new BidItemHibernateDAO(HibernateUtil.getSessionFactory());
//        Timestamp timestamp1 = Timestamp.valueOf("2023-10-15 12:30:00");
//        Timestamp timestamp2 = Timestamp.valueOf("2023-10-22 12:30:00");
//        BidItem bidItem = new BidItem("七彩霓虹鞋", "有7種顏色喔", 1, 10, 100, 500, 1000, timestamp1, timestamp2, null);
//        System.out.println(biditemDAO.insert(bidItem));
//        System.out.println(biditemDAO.getByPrimaryKey(11));
//        System.out.println(biditemDAO.getByPrimaryKey(12));
        Timestamp timestamp = Timestamp.valueOf("2023-10-01 00:00:00");
        Timestamp timestamp2 = Timestamp.valueOf("2023-10-03 00:00:00");
        Timestamp timestamp3 = Timestamp.valueOf("2023-10-09 00:00:00");
        Timestamp timestamp4 = Timestamp.valueOf("2023-12-01 00:00:00");
        Timestamp timestamp5 = Timestamp.valueOf("2023-12-05 00:00:00");
        Timestamp timestamp6 = Timestamp.valueOf("2023-12-09 00:00:00");

        List<BidItem> allByBidStatus = biditemDAO.getAllByEmpId(303);
        for (BidItem b : allByBidStatus) {
            System.out.println(b);
        }


    }
}
