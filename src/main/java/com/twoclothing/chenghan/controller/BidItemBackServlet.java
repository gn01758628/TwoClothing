package com.twoclothing.chenghan.controller;

import com.twoclothing.chenghan.service.BidItemService;
import com.twoclothing.chenghan.service.BidItemServiceImpl;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.members.Members;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        Map<Integer, String> bidStatusMap = new HashMap<>();
        bidStatusMap.put(0, "待審核");
        bidStatusMap.put(1, "已過審");
        bidStatusMap.put(2, "得標");
        bidStatusMap.put(3, "流標");
        bidStatusMap.put(4, "刪除");
        bidStatusMap.put(5, "下架");
        request.setAttribute("bidStatusMap", bidStatusMap);

        Map<String, String[]> parameterMap = request.getParameterMap();
        // 有競標商品編號,其他條件作廢
        String[] bidItemIds = parameterMap.get("bidItemId");
        if (!bidItemIds[0].isEmpty()) {
            BidItem bidItem = bidItemService.getBidItemByBidItemId(Integer.parseInt(bidItemIds[0]));
            bidItemList.add(bidItem);
            System.out.println(bidItem);
        } else {

        }
        // 綁定會員
        Map<Integer,Members> membersMap = new HashMap<>();
        for (BidItem bidItem : bidItemList) {
            Members members = bidItemService.getMembersByMbrId(bidItem.getMbrId());
            membersMap.put(bidItem.getBidItemId(),members);
        }
        request.setAttribute("membersMap", membersMap);
        request.getRequestDispatcher("/back/biditem/search").forward(request, response);
    }
}
