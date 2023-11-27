package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.chenghan.dto.BidItemDTO;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.categorytags.CategoryTagsDAO;
import com.twoclothing.model.categorytags.CategoryTagsHibernateDAO;
import com.twoclothing.redismodel.bidrecord.BidRecord;
import com.twoclothing.redismodel.bidrecord.BidRecordDAO;
import com.twoclothing.redismodel.bidrecord.BidRecordJedisDAO;
import com.twoclothing.utils.FormatUtil;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/front/biditemBrowse/*")
public class BidItemBrowseServlet extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);

    private final CategoryTagsDAO categoryTagsDAO = new CategoryTagsHibernateDAO(sessionFactory);

    private final static BidRecordDAO bidRecordDAO = new BidRecordJedisDAO();

    private final Gson gson = new Gson();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("/categoryTags".equals(request.getPathInfo())) {
            doTags(request, response);
            return;
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Integer tagId = Integer.parseInt(request.getParameter("tagId"));
        Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
        Integer pageMaxResult = Integer.parseInt(request.getParameter("pageMaxResult"));
        Map<String, String> data = new HashMap<>();
        List<BidItemDTO> bidItemDTOList;
        List<BidItem> bidItemList;
        int resultCount, quotient, totalPage;
        if (1 == tagId) {
            // 計算結果總數,計算總頁數
            resultCount = bidItemDAO.countByActive();
            quotient = resultCount / pageMaxResult;
            totalPage = resultCount % pageMaxResult == 0 ? quotient : quotient + 1;
            // 根據currentPage,pageMaxResult查詢資料
            bidItemList = bidItemDAO.getAllLimit(currentPage, pageMaxResult);
        } else {
            // 有指定類別時
            // 計算結果總數,計算總頁數
            resultCount = bidItemDAO.countByTags(tagId);
            quotient = resultCount / pageMaxResult;
            totalPage = resultCount % pageMaxResult == 0 ? quotient : quotient + 1;
            // 根據currentPage,pageMaxResult查詢資料
            bidItemList = bidItemDAO.getAllByTagsLimit(currentPage, pageMaxResult, tagId);
        }
        bidItemDTOList = getDTOList(bidItemList);
        // 傳送資料
        data.put("totalPage", String.valueOf(totalPage));
        data.put("bidItemDTOList", gson.toJson(bidItemDTOList));
        out.write(gson.toJson(data));
    }

    private void doTags(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        List<CategoryTags> tagsList = (List<CategoryTags>) getServletContext().getAttribute("categoryTagsSortedList");
        out.write(gson.toJson(tagsList));
    }

    private static List<BidItemDTO> getDTOList(List<BidItem> bidItemList) {
        List<BidItemDTO> bidItemDTOList = new ArrayList<>();
        if (bidItemList != null && !bidItemList.isEmpty()) {
            for (BidItem bidItem : bidItemList) {
                BidItemDTO dto = new BidItemDTO();
                Integer bidItemId = bidItem.getBidItemId();
                dto.setBidItemId(bidItemId);
                Timestamp endTime = bidItem.getEndTime();
                if (endTime == null) {
                    dto.setEndTime(System.currentTimeMillis());
                } else {
                    dto.setEndTime(endTime.getTime());
                }
                dto.setBidName(bidItem.getBidName());

                BidRecord record = bidRecordDAO.getIndexRecordByKey(bidItemId, 0);
                if (record != null) {
                    Integer currentBid = FormatUtil.parseFormattedNumber(record.getBidAmount());
                    dto.setBidAmountType("目前出價：");
                    dto.setCurrentBid(currentBid);
                } else {
                    dto.setBidAmountType("起標金額：");
                    dto.setCurrentBid(bidItem.getStartPrice());
                }
                bidItemDTOList.add(dto);
            }
        }
        return bidItemDTOList;
    }
}
