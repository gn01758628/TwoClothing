package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.chenghan.NumberMapping;
import com.twoclothing.chenghan.service.BidItemService;
import com.twoclothing.chenghan.service.BidItemServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.members.Members;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@WebServlet("/back_end/servlet/biditem/*")
public class BidItemBackServlet extends HttpServlet {

    // 一個Servlet物件對應一個Service物件
    private BidItemService bidItemService;

    private final Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        bidItemService = new BidItemServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 獲取servlet path
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/search" -> doSearch(request, response);
            case "/find" -> doFind(request, response);
            case "/vent" -> doVent(request, response);
            case "/findGiven" -> doFindGiven(request, response);
        }
    }

    private void doFindGiven(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Integer bidItemId = Integer.parseInt(request.getParameter("bidItemId"));
        BidItem bidItem = bidItemService.getBidItemByBidItemId(bidItemId);
        CategoryTags tag = bidItemService.getCategoryTagsByTagId(bidItem.getTagId());
        Integer mbrId = bidItem.getMbrId();
        Members member = bidItemService.getMembersByMbrId(mbrId);
        boolean isDoubleIMG = bidItemService.isDoubleImaged(bidItemId);
        Map<String, String> messages = new HashMap<>();


        Timestamp startTime = bidItem.getStartTime();
        String startTimeStr = startTime != null ? String.valueOf(startTime.getTime()) : "0";
        Timestamp endTime = bidItem.getEndTime();
        String endTimeStr = endTime != null ? String.valueOf(endTime.getTime()) : "0";
        messages.put("bidName",bidItem.getBidName());
        messages.put("isDoubleIMG", String.valueOf(isDoubleIMG));
        messages.put("mbrId", String.valueOf(mbrId));
        messages.put("mbrEmail", member.getEmail());
        messages.put("mbrName", member.getMbrName());
        messages.put("grade", NumberMapping.gradeMap.get(bidItem.getGrade()));
        messages.put("size", NumberMapping.sizeMap.get(bidItem.getSize()));
        messages.put("tag",tag.getCategoryName());
        messages.put("startPrice", String.valueOf(bidItem.getStartPrice()));
        String reservePrice = bidItem.getReservePrice() == null ? "0" : String.valueOf(bidItem.getReservePrice());
        String directPrice = bidItem.getDirectPrice() == null ? "0" : String.valueOf(bidItem.getDirectPrice());
        messages.put("reservePrice", reservePrice);
        messages.put("directPrice", directPrice);
        messages.put("startTime", startTimeStr);
        messages.put("endTime", endTimeStr);

        out.write(gson.toJson(messages));
    }

    private void doSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> employeeList = bidItemService.getAllEmployee();
        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("/back_end/biditem/BidItemManage.jsp").forward(request, response);
    }

    private void doFind(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 綁定集合
        List<BidItem> bidItemList = new ArrayList<>();
        request.setAttribute("bidItemList", bidItemList);
        // 綁定狀態
        Map<Integer, String> bidStatusMap = NumberMapping.bidStatusMap;
        request.setAttribute("bidStatusMap", bidStatusMap);

        Map<String, String[]> parameterMap = request.getParameterMap();
        // 有競標商品編號,其他條件作廢
        String[] bidItemIds = parameterMap.get("bidItemId");
        if (!bidItemIds[0].isEmpty()) {
            BidItem bidItem = bidItemService.getBidItemByBidItemId(Integer.parseInt(bidItemIds[0]));
            if (bidItem != null) {
                bidItemList.add(bidItem);
            }
        } else {
            // 複合條件查詢
            List<BidItem> allBidItemByCompositeQuery = bidItemService.getAllBidItemByCompositeQuery(parameterMap);
            if (allBidItemByCompositeQuery != null) {
                bidItemList.addAll(allBidItemByCompositeQuery);
            }
        }

        if (!bidItemList.contains(null)) {
            // 綁定會員
            Map<Integer, Members> membersMap = new HashMap<>();
            // 綁定員工
            Map<Integer, Employee> employeeMap = new HashMap<>();
            for (BidItem bidItem : bidItemList) {
                Members members = bidItemService.getMembersByMbrId(bidItem.getMbrId());

                if (bidItem.getEmpId() != null) {
                    Employee employee = bidItemService.getEmployeeByEmpId(bidItem.getEmpId());
                    employeeMap.put(bidItem.getBidItemId(), employee);
                }

                membersMap.put(bidItem.getBidItemId(), members);
            }
            request.setAttribute("membersMap", membersMap);
            request.setAttribute("employeeMap", employeeMap);
        }
        request.getRequestDispatcher("/back_end/servlet/biditem/search").forward(request, response);
    }

    private void doVent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer empid = (Integer) request.getSession().getAttribute("empId");
        String bidItemId = request.getParameter("id");
        String message = request.getParameter("message");
        Employee employee = bidItemService.getEmployeeByEmpId(empid);
        BidItem bidItem = bidItemService.getBidItemByBidItemId(Integer.parseInt(bidItemId));
        HibernateUtil.getSessionFactory().getCurrentSession().evict(bidItem);

        // 審核通過
        if ("agree".equals(request.getParameter("result"))) {
            bidItem.setBidStatus(1);
            bidItem.setEmpId(empid);

            // 審核通過後設定競標商品的開始與結束時間
            LocalDateTime currentDateTime = LocalDateTime.now();
            int currentYear = currentDateTime.getYear();
            int currentMonth = currentDateTime.getMonthValue();
            int currentDay = currentDateTime.getDayOfMonth();
            int currentHour = currentDateTime.getHour();
            LocalDateTime startTime, endTime;
            if (bidItem.getStartTime() != null) {
                // 有開始時間(有預約上架時間)
                startTime = bidItem.getStartTime().toLocalDateTime();
                // 判斷審核通過時是否已超過預約時間
                if (startTime.isBefore(currentDateTime)) {
                    startTime = LocalDateTime.of(currentYear, currentMonth, currentDay + 1, 12, 0, 0);
                }
            } else {
                // 沒有開始時間(設定通過後立即上架)
                // 判斷審核通過時是否已超過當天12點
                if (currentHour < 12) {
                    startTime = LocalDateTime.of(currentYear, currentMonth, currentDay, 12, 0, 0);
                } else {
                    startTime = LocalDateTime.of(currentYear, currentMonth, currentDay + 1, 12, 0, 0);
                }
            }
            endTime = startTime.plusDays(7).plusMinutes(5);
            bidItem.setStartTime(Timestamp.valueOf(startTime));
            bidItem.setEndTime(Timestamp.valueOf(endTime));
            bidItemService.updateBidItem(bidItem);

            // 發送通知
            Notice notice = new Notice();
            notice.setType("競標審核");
            notice.setHead("您的競標案審核已通過");
            notice.setContent("您的競標申請已通過，將在" + bidItem.getStartTime() + "開始上架");
            notice.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
            notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
            bidItemService.addNotice(notice, bidItem.getMbrId());

        }

        //審核不通過
        if ("reject".equals(request.getParameter("result"))) {
            bidItem.setBidStatus(6);
            bidItem.setEmpId(empid);
            bidItemService.updateBidItem(bidItem);
            // 發送通知
            Notice notice = new Notice();
            notice.setType("競標審核");
            notice.setHead("對不起，您的競標案審核因某些原因無法通過");
            notice.setContent(message);
            notice.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
            notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
            bidItemService.addNotice(notice, bidItem.getMbrId());
        }

        //強制下架
        if ("rejectEnforce".equals(request.getParameter("result"))) {
            bidItem.setBidStatus(6);
            bidItem.setEmpId(empid);
            bidItemService.updateBidItem(bidItem);
            // 發送通知跟競標商品擁有者
            Notice notice = new Notice();
            notice.setType("競標審核");
            notice.setHead("對不起，您上架中的競標商品已被強制下架");
            notice.setContent(message);
            notice.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
            notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
            bidItemService.addNotice(notice, bidItem.getMbrId());
            // 發送通知給競標商品出價者
            Set<Integer> mbrIdSet = bidItemService.getAllMbrIdInBidRecord(Integer.parseInt(bidItemId));
            Notice notice2 = new Notice();
            notice2.setType("競標動態");
            notice2.setHead("參與中的競標商品已被強制下架");
            notice2.setContent("您投標的商品：" + bidItem.getBidName() + "，因商品違規而被強制下架");
            notice2.setLink("/front/biditem/anyone/detail?bidItemId=" + bidItemId);
            notice2.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
            for (Integer mbrId : mbrIdSet) {
                bidItemService.addNotice(notice2, mbrId);
            }
        }

        //回傳處理的員工
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(employee.getEmpName());
    }
}
