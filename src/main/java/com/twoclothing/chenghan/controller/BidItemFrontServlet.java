package com.twoclothing.chenghan.controller;

import com.twoclothing.chenghan.service.BidItemFrontService;
import com.twoclothing.chenghan.service.BidItemFrontServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.categorytags.CategoryTags;

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
import java.util.*;


// @MultipartConfig
//  fileSizeThreshold = 檔案小於這個值,檔案寫入內存,提高效率
//  maxFileSize = 單個檔案大小限制
//  maxRequestSize = 單個請求全部檔案的加總限制
//  單位是bytes( 1024bytes = 1KB )
//  超過maxFileSize或maxRequestSize都會拋出IllegalStateException


@SuppressWarnings("DataFlowIssue")
@WebServlet("/front/biditem/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 2 * 1024 * 1024)
public class BidItemFrontServlet extends HttpServlet {

    /*
        TODO 判定是否是會員,不是會員的不應該進入此servlet
     */

    // 一個Servlet物件對應一個Service物件
    private BidItemFrontService bidItemFrontService;

    @Override
    public void init() throws ServletException {
        bidItemFrontService = new BidItemFrontServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 獲取servlet path
        String servletPath = request.getServletPath() + request.getPathInfo();
        if ("/front/biditem/add".equals(servletPath)) {
            doAdd(request, response);
        } else if ("/front/biditem/save".equals(servletPath)) {
            doSave(request, response);
        } else if ("/front/biditem/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/front/biditem/detail".equals(servletPath)) {
            doDetail(request, response);
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryTags> allCategoryTags = bidItemFrontService.getAllCategoryTags();
        request.setAttribute("categoryTags", allCategoryTags);
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
        List<Integer> allSelectableTagsId = bidItemFrontService.getAllSelectableTagsId();
        if (tagId == null || tagId.trim().isEmpty()) {
            errorMessages.add("請正確選擇商品類別標籤");
        } else if (!allSelectableTagsId.contains(Integer.parseInt(tagId))) {
            errorMessages.add("您選擇的類別標籤並非是可選標籤");
        }

        // TODO 會員編號先寫死,之後要從session取
        Integer mbrId = 1;

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
                    int reserverPriceValue = Integer.parseInt(reserverPrice);
                    int directPriceValue = Integer.parseInt(directPrice);
                    if (reserverPriceValue != 0) {
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
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                if ("image01".equals(part.getName())) imagePart1 = part;
                if ("image02".equals(part.getName())) imagePart2 = part;
            }
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

        int bidItemPK = bidItemFrontService.addBidItem(bidItem);

        // 儲存BidItemImage
        BidItemImage bidItemImage01 = new BidItemImage();
        bidItemImage01.setBidItemId(bidItemPK);
        bidItemImage01.setImage(image01);
        bidItemFrontService.addBidItemImage(bidItemImage01);

        if (image02.length != 0) {
            BidItemImage bidItemImage02 = new BidItemImage();
            bidItemImage02.setBidItemId(bidItemPK);
            bidItemImage02.setImage(image02);
            bidItemFrontService.addBidItemImage(bidItemImage02);
        }

        response.sendRedirect(request.getContextPath() + "/front/biditem/list");
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // TODO 會員編號先寫死,之後要從session取
        Integer mbrId = 1;

        List<BidItem> allBidItemByMbrid = bidItemFrontService.getAllBidItemByMbrid(mbrId);
        request.setAttribute("allBidItemByMbrid", allBidItemByMbrid);
        Map<Integer, String> statusMap = new HashMap<>();
        statusMap.put(0, "待審核");
        statusMap.put(1, "已過審");
        statusMap.put(2, "得標");
        statusMap.put(3, "流標");
        request.setAttribute("statusMap", statusMap);
        request.getRequestDispatcher("/front_end/biditem/BidItemPersonalList.jsp").forward(request, response);
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bidItemId = request.getParameter("bidItemId");
        BidItem bidItem = bidItemFrontService.getBidItemByBidItemId(Integer.parseInt(bidItemId));
        request.setAttribute("bidItem", bidItem);
        request.getRequestDispatcher("/front_end/biditem/BidItemDetail.jsp").forward(request, response);

    }
}
