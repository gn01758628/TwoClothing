package com.twoclothing.chenghan.controller;

import com.twoclothing.chenghan.NumberMapping;
import com.twoclothing.chenghan.service.BidItemService;
import com.twoclothing.chenghan.service.BidItemServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.abid.bidorder.BidOrder;
import com.twoclothing.redismodel.bidrecord.BidRecord;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.utils.FormatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// @MultipartConfig
//  fileSizeThreshold = 檔案小於這個值,檔案寫入內存,提高效率
//  maxFileSize = 單個檔案大小限制
//  maxRequestSize = 單個請求全部檔案的加總限制
//  單位是bytes( 1024bytes = 1KB )
//  超過maxFileSize或maxRequestSize都會拋出IllegalStateException


@WebServlet("/front/biditem/personal/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class BidItemFrontPersonalServlet extends HttpServlet {

    // 一個Servlet物件對應一個Service物件
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
            case "/add.check" -> doAdd(request, response);
            case "/save.check" -> doSave(request, response);
            case "/list.check" -> doList(request, response);
            case "/endEarly" -> doEndEarly(request, response);
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/front_end/biditem/BidItemAdd.jsp").forward(request, response);
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        // 創建錯誤訊息List,綁定在request scope裡
        List<String> errorMessages = new ArrayList<>();
        request.setAttribute("errorMessages", errorMessages);
        // 取得前端參數,後端參數驗證
        String bidName = request.getParameter("bidName");
        if (bidName == null || bidName.trim().isEmpty()) errorMessages.add("商品標題不可以空白");

        String grade = request.getParameter("grade");
        if (grade == null || grade.trim().isEmpty() || !grade.matches("^[0-5]$"))
            errorMessages.add("請正確選擇商品新舊程度");

        String size = request.getParameter("size");
        if (size == null || size.trim().isEmpty() || !size.matches("^[0-8]$"))
            errorMessages.add("請正確選擇商品品尺寸");

        String detail = request.getParameter("detail");
        if (detail == null || detail.trim().isEmpty()) {
            errorMessages.add("商品簡述不可以空白");
        } else {
            if (detail.replace("<br>", " ").length() > 250) errorMessages.add("商品簡述不可以超過250個字");
        }

        String tagId = request.getParameter("tagId");
        List<Integer> allSelectableTagsId = bidItemService.getAllSelectableTagsId();
        if (tagId == null || tagId.trim().isEmpty()) {
            errorMessages.add("請正確選擇商品類別標籤");
        } else if (!allSelectableTagsId.contains(Integer.parseInt(tagId))) {
            errorMessages.add("您選擇的類別標籤並非是可選標籤");
        }

        Integer mbrId = (Integer) request.getSession().getAttribute("mbrId");

        String startPrice = request.getParameter("startPrice");
        if (startPrice == null || startPrice.trim().isEmpty() || !startPrice.matches("[1-9][0-9]*"))
            errorMessages.add("起標價格請勿空白,價格請填寫正整數");

        String reserverPrice = request.getParameter("reserverPrice");
        if (reserverPrice == null) {
            errorMessages.add("請正確填寫拍賣底價");
        } else {
            if (!reserverPrice.isEmpty()) {
                if (!reserverPrice.matches("[1-9][0-9]*")) {
                    errorMessages.add("拍賣底價必須是有效的正整数，且大於0");
                } else {
                    int reserverPriceValue = Integer.parseInt(reserverPrice);
                    int startPriceValue = Integer.parseInt(startPrice);
                    if (reserverPriceValue < startPriceValue) {
                        errorMessages.add("拍賣底價必須大於起標價格");
                    }
                }
            }
        }

        String directPrice = request.getParameter("directPrice");
        if (directPrice == null) {
            errorMessages.add("請正確填寫立即結標價");
        } else {
            if (!directPrice.isEmpty()) {
                if (!directPrice.matches("[1-9][0-9]*")) {
                    errorMessages.add("立即結標價必須是有效的正整数，且大於0");
                } else {
                    int startPriceValue = Integer.parseInt(startPrice);
                    int directPriceValue = Integer.parseInt(directPrice);
                    if (!reserverPrice.isEmpty()) {
                        int reserverPriceValue = Integer.parseInt(reserverPrice);
                        if (directPriceValue < reserverPriceValue) errorMessages.add("立即結標價必須大於拍賣底價");
                    } else {
                        if (directPriceValue < startPriceValue) errorMessages.add("立即結標價必須大於起標價格");
                    }
                }
            }
        }

        String startTime = request.getParameter("startTime");
        if (startTime == null) {
            errorMessages.add("請正確選擇競標開始時間");
        } else {
            if (!startTime.isEmpty()) {
                if (!startTime.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    errorMessages.add("你選擇的日期格式有誤");
                } else {
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    try {
                        LocalDate startTimeDate = LocalDate.parse(startTime, formatter);
                        LocalDate earliestDate = currentDate.plusDays(2);
                        LocalDate latestDate = currentDate.plusDays(10);
                        if (startTimeDate.isBefore(earliestDate) || startTimeDate.isAfter(latestDate)) {
                            errorMessages.add("您選擇的日期不在有效範圍內");
                        }
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        // 取的前端傳來的圖片
        Part imagePart1 = null, imagePart2 = null;
        try {
            imagePart1 = request.getPart("image01");
            imagePart2 = request.getPart("image02");
            // 判斷檔案類型是否錯誤
            boolean image01TypeInvalid = imagePart1 != null && imagePart1.getSize() > 0 && !imagePart1.getContentType().startsWith("image/");
            boolean image02TypeInvalid = imagePart2 != null && imagePart2.getSize() > 0 && !imagePart2.getContentType().startsWith("image/");
            if (image01TypeInvalid || image02TypeInvalid) {
                errorMessages.add("圖片檔案類型錯誤(只接受圖檔)");
            }
            // 單張圖片超過大小的例外處理
        } catch (IllegalStateException e) {
            errorMessages.add("單張圖片的大小不能超過2MB");
        }
        // 取得byte[]圖片
        byte[] image01 = null, image02 = null;
        if (imagePart1 != null && imagePart2 != null) {
            InputStream in = imagePart1.getInputStream();
            image01 = in.readAllBytes();
            in = imagePart2.getInputStream();
            image02 = in.readAllBytes();
            in.close();
        }
        if (image01 == null || image01.length == 0) errorMessages.add("每個商品都必須要有主圖片");
        if (image02 == null) errorMessages.add("請正確的選擇商品補充圖片");

        // 如果錯誤訊息不為空則轉發回新增頁面
        if (!errorMessages.isEmpty()) {
            request.getRequestDispatcher("/front/biditem/add").forward(request, response);
            return;
        }

        // 儲存資料
        // 儲存BidItem
        BidItem bidItem = new BidItem();
        bidItem.setBidName(bidName);
        bidItem.setGrade(Integer.parseInt(grade));

        if ("8".equals(size)) {
            bidItem.setSize(null);
        } else {
            bidItem.setSize(Integer.parseInt(size));
        }

        bidItem.setDetail(detail);
        bidItem.setTagId(Integer.parseInt(tagId));
        bidItem.setMbrId(mbrId);
        bidItem.setStartPrice(Integer.parseInt(startPrice));

        if ("".equals(reserverPrice)) {
            bidItem.setReservePrice(null);
        } else {
            bidItem.setReservePrice(Integer.parseInt(reserverPrice));
        }

        if ("".equals(directPrice)) {
            bidItem.setDirectPrice(null);
        } else {
            bidItem.setDirectPrice(Integer.parseInt(directPrice));
        }

        bidItem.setBidStatus(0);

        if ("".equals(startTime)) {
            bidItem.setStartTime(null);
        } else {
            Timestamp timestamp = Timestamp.valueOf(startTime + " " + "12:00:00");
            bidItem.setStartTime(timestamp);
        }

        int bidItemPK = bidItemService.addBidItem(bidItem);

        // 儲存BidItemImage
        BidItemImage bidItemImage01 = new BidItemImage();
        bidItemImage01.setBidItemId(bidItemPK);
        bidItemImage01.setImage(image01);
        bidItemService.addBidItemImage(bidItemImage01);

        if (image02.length != 0) {
            BidItemImage bidItemImage02 = new BidItemImage();
            bidItemImage02.setBidItemId(bidItemPK);
            bidItemImage02.setImage(image02);
            bidItemService.addBidItemImage(bidItemImage02);
        }

        response.sendRedirect(request.getContextPath() + "/front/biditem/personal/list.check");
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer mbrId = (Integer) request.getSession().getAttribute("mbrId");
        List<BidItem> bidItemList = bidItemService.getAllLegalBidItemByMbrid(mbrId);
        Map<Integer, String[]> timeMap = new HashMap<>();
        // 格式化時間
        for (BidItem bidItem : bidItemList) {
            String[] arr = {FormatUtil.timestampNoSecond(bidItem.getStartTime()), FormatUtil.timestampNoSecond(bidItem.getEndTime())};
            timeMap.put(bidItem.getBidItemId(), arr);
        }
        // 狀態名稱
        Map<Integer, String> bidStatusMap = NumberMapping.bidStatusMap;
        // 出價狀況
        Map<Integer, String> currentBidMap = new HashMap<>();
        for (BidItem bidItem : bidItemList) {
            Integer bidItemId = bidItem.getBidItemId();
            BidRecord bidRecord = bidItemService.getBidRecordByIndex(bidItemId, 0);
            if (bidRecord != null) {
                currentBidMap.put(bidItemId, bidRecord.getBidAmount());
            }
        }
        request.setAttribute("bidItemList", bidItemList);
        request.setAttribute("timeMap", timeMap);
        request.setAttribute("bidStatusMap", bidStatusMap);
        request.setAttribute("currentBidMap", currentBidMap);
        request.getRequestDispatcher("/front_end/biditem/BidItemPersonalList.jsp").forward(request, response);
    }

    private void doEndEarly(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Integer bidItemId = Integer.parseInt(request.getParameter("bidItemId"));
        BidItem bidItem = bidItemService.getBidItemByBidItemId(bidItemId);
        List<BidRecord> bidRecordList = bidItemService.getAllBidRecordByBidItemId(bidItemId);
        if ("流標".equals(action)) {
            bidItem.setBidStatus(3);
            bidItem.setEndTime(new Timestamp(System.currentTimeMillis()));
            bidItemService.updateBidItem(bidItem);
            // 發送通知,所有參與投標者
            for (BidRecord bidRecord : bidRecordList) {
                Notice notice = new Notice();
                notice.setType("競標動態");
                notice.setHead("提前流標");
                notice.setContent("您投標的商品：" + bidItem.getBidName() + "，因為賣方提前流標，競標已提前結束。");
                notice.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
                notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
                bidItemService.addNotice(notice, bidRecord.getMbrId());
            }
            return;
        }
        if ("結標".equals(action)) {
            bidItem.setBidStatus(2);
            bidItemService.updateBidItem(bidItem);
            BidRecord highestBidRecord = bidRecordList.get(0);
            // 結標新增訂單
            BidOrder bidOrder = new BidOrder();
            bidOrder.setBidItemId(bidItemId);
            bidOrder.setBuyMbrId(highestBidRecord.getMbrId());
            bidOrder.setSellMbrId(bidItem.getMbrId());
            bidOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
            bidOrder.setAmount(FormatUtil.parseFormattedNumber(highestBidRecord.getBidAmount()));
            bidOrder.setOrderStatus(0);
            bidItemService.addBidOrder(bidOrder);
            // 發送通知
            //  得標者
            Notice notice = new Notice();
            notice.setType("競標動態");
            notice.setHead("提前結標");
            notice.setContent("恭喜！您投標的商品：" + bidItem.getBidName() + "，因為賣方提前結標，已由您得標！請瀏覽您的訂單並繼續後續流程。");
            notice.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
            notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
            bidItemService.addNotice(notice, highestBidRecord.getMbrId());
            //  其餘參與投標者
            for (int i = 1; i < bidRecordList.size(); i++) {
                BidRecord bidRecord = bidRecordList.get(i);
                Notice noticeElse = new Notice();
                noticeElse.setType("競標動態");
                noticeElse.setHead("提前結標");
                noticeElse.setContent("您投標的商品：" + bidItem.getBidName() + "，因為賣方提前結標，已由其他會員得標。");
                noticeElse.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
                noticeElse.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
                bidItemService.addNotice(noticeElse, bidRecord.getMbrId());
            }
            // TODO 發送訂單成立通知(通知買賣雙方)
        }
    }

}
