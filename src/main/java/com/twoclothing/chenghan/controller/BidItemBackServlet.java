package com.twoclothing.chenghan.controller;

import com.twoclothing.chenghan.NumberMapping;
import com.twoclothing.chenghan.service.BidItemService;
import com.twoclothing.chenghan.service.BidItemServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/back/biditem/*")
public class BidItemBackServlet extends HttpServlet {

    // 一個Servlet物件對應一個Service物件
    private BidItemService bidItemService;

    @Override
    public void init() throws ServletException {
        bidItemService = new BidItemServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
        TODO 判定員工是否符合權限
        */

        // 獲取servlet path
        String servletPath = request.getServletPath() + request.getPathInfo();
        switch (servletPath) {
            case "/back/biditem/search" -> doSearch(request, response);
            case "/back/biditem/find" -> doFind(request, response);
            case "/back/biditem/vent" -> doVent(request, response);
        }
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
        request.getRequestDispatcher("/back/biditem/search").forward(request, response);
    }

    private void doVent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO 從session取得員工編號 這裡先寫死
        Integer empid = 1;
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
            // TODO 設置點擊前往的連結
            notice.setLink("");
            notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
            bidItemService.addNotice(notice,bidItem.getMbrId());

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
            // TODO 設置點擊前往的連結
            notice.setLink("");
            notice.setImageLink("/ReadItemIMG/biditem?id=" + bidItemId + "&position=1");
            bidItemService.addNotice(notice,bidItem.getMbrId());
        }

        //回傳處理的員工
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(employee.getEmpName());
    }
}
