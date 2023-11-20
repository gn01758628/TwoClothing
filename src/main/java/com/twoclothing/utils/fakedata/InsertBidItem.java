package com.twoclothing.utils.fakedata;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.categorytags.CategoryTagsDAO;
import com.twoclothing.model.categorytags.CategoryTagsHibernateDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@WebServlet("/insertBidItem")
public class InsertBidItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);
        CategoryTagsDAO categoryTagsDAO = new CategoryTagsHibernateDAO(sessionFactory);
        List<Integer> tagIdList = categoryTagsDAO.getTagIdsWithoutChildren();
        Random random = new Random();
        for (int i = 1; i <= 50; i++) {
            BidItem bidItem = new BidItem();
            String name = String.format("競標商品%02d", i);
            bidItem.setBidName(name);
            int grade = random.nextInt(5);
            bidItem.setGrade(grade);
            int size = random.nextInt(8);
            bidItem.setSize(size);
            String detail = String.format("競標%02d的描述", i);
            bidItem.setDetail(detail);
            int index = random.nextInt(tagIdList.size());
            bidItem.setTagId(tagIdList.get(index));
            int mbrId = random.nextInt(2) + 1;
            bidItem.setMbrId(mbrId);
            // 價格
            int randomNum = (random.nextInt(200) + 1) * 50;
            int randomNum2 = (random.nextInt(50) + 1) * 50;
            int randomNum3 = (random.nextInt(10) + 1) * 1000;
            boolean randomExists = random.nextBoolean();
            boolean randomExists2 = random.nextBoolean();
            int startPrice = randomNum;
            bidItem.setStartPrice(startPrice);
            Integer reservePrice = randomExists ? startPrice + randomNum2 : null;
            bidItem.setReservePrice(reservePrice);
            Integer directPrice = reservePrice == null ? startPrice + randomNum3 : reservePrice + randomNum3;
            if (!randomExists2) directPrice = null;
            bidItem.setDirectPrice(directPrice);
            // 隨機開始時間(全部未審核)
//            bidItem.setBidStatus(0);
//            boolean randomExists3 = random.nextBoolean();
//            int randomDay = random.nextInt(2, 11);
//            LocalDateTime now = LocalDateTime.now();
//            LocalDateTime randomDateTime = now.plusDays(randomDay).withHour(12).withMinute(0).withSecond(0).withNano(0);
//            Timestamp timestamp = randomExists3 ? Timestamp.valueOf(randomDateTime) : null;
//            bidItem.setStartTime(timestamp);
            // 指定開始時間與結束時間(測試用)
            bidItem.setBidStatus(4);
            bidItem.setStartTime(Timestamp.valueOf("2023-11-19 12:00:00"));
            bidItem.setEndTime(Timestamp.valueOf("2023-11-26 12:05:00"));
            bidItem.setEmpId(1);
            bidItemDAO.insert(bidItem);
        }
    }
}
