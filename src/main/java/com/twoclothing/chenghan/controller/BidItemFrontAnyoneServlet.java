package com.twoclothing.chenghan.controller;

import com.twoclothing.chenghan.service.BidItemService;
import com.twoclothing.chenghan.service.BidItemServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.members.Members;
import com.twoclothing.redismodel.bidrecord.BidRecord;
import com.twoclothing.utils.FormatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // TODO 從session取的會員ID 這裡先寫死
        Integer mbrId = 2;
        String bidItemId = request.getParameter("bidItemId");
        BidItem bidItem = bidItemService.getBidItemByBidItemId(Integer.parseInt(bidItemId));
        LocalDateTime endTime = bidItem.getEndTime().toLocalDateTime();
        String bidAmount = request.getParameter("bidAmount");
        // 先不判斷先儲存
        String now = FormatUtil.timestampDateTime(new Timestamp(System.currentTimeMillis()));
        BidRecord bidRecord = new BidRecord(mbrId, FormatUtil.numberThousandsSeparators(bidAmount), now);
        bidItemService.addBidRecord(bidRecord, Integer.parseInt(bidItemId), bidItem.getEndTime().toLocalDateTime());

    }
}
