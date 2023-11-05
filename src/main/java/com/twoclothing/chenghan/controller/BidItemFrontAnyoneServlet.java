package com.twoclothing.chenghan.controller;

import com.twoclothing.chenghan.NumberMapping;
import com.twoclothing.chenghan.service.BidItemService;
import com.twoclothing.chenghan.service.BidItemServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.members.Members;
import com.twoclothing.redismodel.bidrecord.BidRecord;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.utils.FormatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/front/biditem/anyone/*")
public class BidItemFrontAnyoneServlet extends HttpServlet {
    private BidItemService bidItemService;

    @Override
    public void init() throws ServletException {
        bidItemService = new BidItemServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 獲取pathInfo
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/detail" -> doDetail(request, response);
            case "/bid" -> doBid(request, response);
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 競標商品基本資料
        String bidItemId = request.getParameter("bidItemId");
        BidItem bidItem = bidItemService.getBidItemByBidItemId(Integer.parseInt(bidItemId));
        String categoryName = bidItemService.getCategoryTagsByTagId(bidItem.getTagId()).getCategoryName();
        String grade = NumberMapping.gradeMap.get(bidItem.getGrade());
        String size = NumberMapping.sizeMap.get(bidItem.getSize());
        String bidStatus = NumberMapping.bidStatusMap.get(bidItem.getBidStatus());
        String[] timeArr = {FormatUtil.timestampNoSecond(bidItem.getStartTime()), FormatUtil.timestampNoSecond(bidItem.getEndTime())};
        request.setAttribute("timeArr", timeArr);
        request.setAttribute("bidItem", bidItem);
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("grade", grade);
        request.setAttribute("size", size);
        request.setAttribute("bidStatus", bidStatus);

        // 競標商品出價資訊
        List<BidRecord> bidRecordList = bidItemService.getAllBidRecordByBidItemId(Integer.parseInt(bidItemId));
        // 綁定出價會員
        Map<Integer, String> mbrMap = new HashMap<>();
        for (BidRecord record : bidRecordList) {
            Members mbr = bidItemService.getMembersByMbrId(record.getMbrId());
            String mbrName = mbr.getMbrName();
            mbrMap.put(record.getMbrId(), mbrName);
        }
        request.setAttribute("mbrMap", mbrMap);
        request.setAttribute("bidRecordList", bidRecordList);
        request.getRequestDispatcher("/front_end/biditem/BidItemDetail.jsp").forward(request, response);
    }

    private void doBid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // TODO 從session取的會員ID 這裡先寫死
        Integer mbrId = 2;
        String bidItemId = request.getParameter("bidItemId");
        BidItem bidItem = bidItemService.getBidItemByBidItemId(Integer.parseInt(bidItemId));
        LocalDateTime endTime = bidItem.getEndTime().toLocalDateTime();
        String bidAmount = request.getParameter("bidAmount");
        String currentBid = request.getParameter("currentBid");
        String bidType = request.getParameter("bidType");
        // 判斷金額是否正確
        if (bidAmount == null || bidAmount.trim().isEmpty()) {
            out.print("0");
            return;
        }
        if (!"0".equals(currentBid)) {
            if (Integer.parseInt(bidAmount) <= Integer.parseInt(currentBid)) {
                out.print("0");
                return;
            }
        }
        if (bidItem.getDirectPrice() != null) {
            if (Integer.parseInt(bidAmount) > bidItem.getDirectPrice()) {
                out.print("0");
                return;
            }
        }
        // 判斷出價型態
        if ("直接出價".equals(bidType)) {
            // 添加出價紀錄
            String now = FormatUtil.timestampDateTime(new Timestamp(System.currentTimeMillis()));
            BidRecord bidRecord = new BidRecord(mbrId, FormatUtil.numberThousandsSeparators(bidAmount), now);
            bidItemService.addBidRecord(bidRecord, Integer.parseInt(bidItemId), bidItem.getEndTime().toLocalDateTime());
            // 發送通知給其他所有參與競標的會員(只需要給上一個)
            BidRecord previousbidRecord = bidItemService.getBidRecordByIndex(Integer.parseInt(bidItemId), 1);
            if (previousbidRecord != null) {
                Integer previousMbrId = previousbidRecord.getMbrId();
                Notice notice = new Notice();
                notice.setType("競標動態");
                notice.setHead("出價更新提醒");
                notice.setContent("您投標的商品：" + bidItem.getBidName() + "，出價價格已被超越");
                notice.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
                notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
                bidItemService.addNotice(notice,previousMbrId);
            }
            out.print("1");
            return;
        }

        if ("立即結標".equals(bidType)) {
            // 改變商品狀態
            bidItem.setBidStatus(2);
            // 添加出價紀錄
            String now = FormatUtil.timestampDateTime(new Timestamp(System.currentTimeMillis()));
            BidRecord bidRecord = new BidRecord(mbrId, FormatUtil.numberThousandsSeparators(bidAmount), now);
            bidItemService.addBidRecord(bidRecord, Integer.parseInt(bidItemId), bidItem.getEndTime().toLocalDateTime());
            // 發送通知給其他所有參與競標的會員
            Set<Integer> mbrIdSet = bidItemService.getAllMbrIdInBidRecord(Integer.parseInt(bidItemId));
            Notice notice = new Notice();
            for (Integer id : mbrIdSet) {
                if (!id.equals(mbrId)) {
                    notice.setType("競標動態");
                    notice.setHead("出價更新提醒");
                    notice.setContent("您投標的商品：" + bidItem.getBidName() + "，已由其他會員以直購方式結標");
                    notice.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
                    notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
                    bidItemService.addNotice(notice, id);
                }
            }
            // TODO 跑訂單

            out.print("2");
        }
    }
}
