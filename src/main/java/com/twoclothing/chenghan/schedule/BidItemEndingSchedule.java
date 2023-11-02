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

@WebServlet(value = "/bidItemEndingSchedule", loadOnStartup = 2)
public class BidItemEndingSchedule extends HttpServlet {

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
                    // 獲取當前時間(設置為12:06),來比對競標結束時間
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime customTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 11, 1, 0);
                    Timestamp customTimestamp = Timestamp.valueOf(customTime);

                    // 沒有經過過濾器,自己控制交易
                    currentSession.beginTransaction();

                    // 列出所有上架中且結束時間小於指定時間的競標商品
                    List<BidItem> bidItemList = bidItemDAO.getAllActiveBidItemsByEndTime(customTimestamp);

                    // 迴圈判斷最高出價,更改商品狀態,發送通知,生成訂單
                    for (BidItem bidItem : bidItemList) {
                        // TODO 透過redis取得最高出價者的金額與會員ID
                        // 先測試無人出價的狀況
                        int i = 1;
                        if (i == 2) {

                        } else {
                            // 沒有人出價(流標)
                            bidItem.setBidStatus(3);
                            // 發送競標流標通知
                            Notice notice = new Notice();
                            notice.setType("競標商品");
                            notice.setHead("競標商品已流標");
                            notice.setContent("您的競標商品：" + bidItem.getBidName() + " 因為無人出價，已流標");
                            // TODO 設置點擊連結
                            notice.setLink("/xxx/ooo");
                            notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItem.getBidItemId() + "&position=1");
                            NoticeDAO noticeDAO = new NoticeJedisDAO();
                            noticeDAO.insert(notice,bidItem.getMbrId());
                        }
                    }
                    // 不主動調用update(),利用Persistent狀態達成批次更新
                    currentSession.getTransaction().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    currentSession.getTransaction().rollback();
                }
            }
        };
        Calendar cal = new GregorianCalendar(2023, Calendar.NOVEMBER, 2, 9, 46);
        // 每天中午12:05執行一次
        timer.scheduleAtFixedRate(timerTask, cal.getTime(), 24 * 60 * 60 * 1000);
    }

    @Override
    public void destroy() {
        timer.cancel();
    }
}
