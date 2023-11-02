package com.twoclothing.chenghan.schedule;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.redismodel.notice.NoticeDAO;
import com.twoclothing.redismodel.notice.NoticeJedisDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet(value = "/bidItemLaunchSchedule", loadOnStartup = 1)
public class BidItemLaunchSchedule extends HttpServlet {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);

    private Timer timer;

    @Override
    public void init() throws ServletException {
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Session currentSession = sessionFactory.getCurrentSession();
                try {
                    // 獲取當前時間(設置為12:01),來比對競標開始時間
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime customTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 12, 1, 0);
                    Timestamp customTimestamp = Timestamp.valueOf(customTime);

                    // 沒有經過過濾器,自己控制交易
                    currentSession.beginTransaction();

                    // 列出所有已過審且開始時間小於指定時間的競標商品
                    List<BidItem> bidItemList = bidItemDAO.getAllPassBidItemsByStartTime(customTimestamp);

                    // 迴圈更改商品狀態,並發送通知
                    for (BidItem bidItem : bidItemList) {
                            bidItem.setBidStatus(4);
                            // 發出競標商品上架通知
                            Notice notice = new Notice();
                            notice.setType("競標商品");
                            notice.setHead("競標商品已上架");
                            notice.setContent("您的競標商品：" + bidItem.getBidName() + " 已經上架了");
                            // TODO 設置點擊連結
                            notice.setLink("/xxx/ooo");
                            notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItem.getBidItemId() + "&position=1");
                            NoticeDAO noticeDAO = new NoticeJedisDAO();
                            noticeDAO.insert(notice,bidItem.getMbrId());
                    }
                    // 不主動調用update(),利用Persistent狀態達成批次更新
                    currentSession.getTransaction().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    currentSession.getTransaction().rollback();
                }
            }
        };
        Calendar cal = new GregorianCalendar(2023, Calendar.NOVEMBER, 2, 11, 59);
        // 每天中午11:59執行一次
        timer.scheduleAtFixedRate(timerTask, cal.getTime(), 24 * 60 * 60 * 1000);
    }

    @Override
    public void destroy() {
        timer.cancel();
    }
}
