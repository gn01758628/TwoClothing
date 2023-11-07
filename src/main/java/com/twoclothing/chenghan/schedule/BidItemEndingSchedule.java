package com.twoclothing.chenghan.schedule;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.redismodel.bidrecord.BidRecord;
import com.twoclothing.redismodel.bidrecord.BidRecordDAO;
import com.twoclothing.redismodel.bidrecord.BidRecordJedisDAO;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.redismodel.notice.NoticeDAO;
import com.twoclothing.redismodel.notice.NoticeJedisDAO;
import com.twoclothing.utils.FormatUtil;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebServlet(value = "/bidItemEndingSchedule", loadOnStartup = 2)
public class BidItemEndingSchedule extends HttpServlet {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);

    private final BidRecordDAO bidRecordDAO = new BidRecordJedisDAO();

    private final NoticeDAO noticeDAO = new NoticeJedisDAO();

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
                    LocalDateTime customTime = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 12, 6, 0);
                    Timestamp customTimestamp = Timestamp.valueOf(customTime);

                    // 沒有經過過濾器,自己控制交易
                    currentSession.beginTransaction();

                    // 列出所有上架中且結束時間小於指定時間的競標商品
                    List<BidItem> bidItemList = bidItemDAO.getAllActiveBidItemsByEndTime(customTimestamp);
                    if (bidItemList == null || bidItemList.isEmpty()) return;

                    // 迴圈判斷最高出價,更改商品狀態,發送通知,生成訂單
                    for (BidItem bidItem : bidItemList) {
                        // 列出每個競標商品的最高出價紀錄
                        BidRecord record = bidRecordDAO.getIndexRecordByKey(bidItem.getBidItemId(), 0);
                        String bidItemName = bidItem.getBidName();
                        Integer mbrId = bidItem.getMbrId();
                        Integer bidItemId = bidItem.getBidItemId();
                        if (record != null && (bidItem.getReservePrice() == null || FormatUtil.parseFormattedNumber(record.getBidAmount()) >= bidItem.getReservePrice())) {
                            // 競標成功-結標
                            bidItem.setBidStatus(2);
                            String successReason = "恭喜！您的競標商品：" + bidItemName + "，已結標。請瀏覽您的訂單並繼續後續流程。";
                            sendNotice("您的競標商品已結標", successReason, bidItemId, mbrId);
                            // TODO 結標跑訂單
                        } else {
                            // 競標失敗-流標
                            bidItem.setBidStatus(3);
                            String failReason1 = record == null ? "因為無人出價，" : "因為出價沒有超過您設置的底價，";
                            String failReason2 = "您的競標商品：" + bidItemName + "，" + failReason1 + "已流標";
                            sendNotice("您的競標商品已流標", failReason2, bidItemId, mbrId);
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

        // 服務器啟動時的時間
        LocalDateTime tomcatNow = LocalDateTime.now();
        // 默認的開始時間為當天的12:05
        LocalDateTime startTime = LocalDateTime.of(tomcatNow.getYear(), tomcatNow.getMonthValue(), tomcatNow.getDayOfMonth(), 12, 5, 0);
        // 如果服務器啟動時已超過12:05,startTime+1天
        if (tomcatNow.isAfter(startTime)) startTime = startTime.plusDays(1);
        // 每天中午12:05執行一次
        timer.scheduleAtFixedRate(timerTask, Timestamp.valueOf(startTime), 24 * 60 * 60 * 1000);
    }

    @Override
    public void destroy() {
        timer.cancel();
    }

    private void sendNotice(String head, String content, Integer bidItemId, Integer mbrId) {
        Notice notice = new Notice();
        notice.setType("競標商品");
        notice.setHead(head);
        notice.setContent(content);
        notice.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
        notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
        noticeDAO.insert(notice, mbrId);
    }
}
