package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.chenghan.dto.BidItemDTO;
import com.twoclothing.chenghan.dto.ItemDTO;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.item.ItemDAO;
import com.twoclothing.model.aproduct.item.ItemHibernateDAO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/keywordResult")
public class KeywordResultServlet extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final ItemDAO itemDAO = new ItemHibernateDAO(sessionFactory);

    private final BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);

    private final BidRecordDAO bidRecordDAO = new BidRecordJedisDAO();

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name = (String) request.getSession().getAttribute("navSearch");
        List<ItemDTO> itemDTOList = new ArrayList<>();
        List<BidItemDTO> bidItemDTOList = new ArrayList<>();
        // 查詢資料
        List<Item> itemList = itemDAO.getAllActiveByItemName(name);
        List<BidItem> bidItemList = bidItemDAO.getAllActiveByBidItemName(name);
        // 包裝資料
        for (Item item : itemList) {
            ItemDTO dto = new ItemDTO();
            dto.setItemId(item.getItemId());
            dto.setItemName(item.getItemName());
            dto.setPrice(item.getPrice());
            itemDTOList.add(dto);
        }

        for (BidItem item : bidItemList) {
            BidItemDTO dto = new BidItemDTO();
            Integer bidItemId = item.getBidItemId();
            dto.setBidItemId(bidItemId);
            dto.setEndTime(item.getEndTime().getTime());
            dto.setBidName(item.getBidName());

            BidRecord record = bidRecordDAO.getIndexRecordByKey(bidItemId, 0);
            if (record != null) {
                Integer currentBid = FormatUtil.parseFormattedNumber(record.getBidAmount());
                dto.setBidAmountType("目前出價：");
                dto.setCurrentBid(currentBid);
            } else {
                dto.setBidAmountType("起標金額：");
                dto.setCurrentBid(item.getStartPrice());
            }
            bidItemDTOList.add(dto);
        }
        // 傳送資料
        Map<String,String> data = new HashMap<>();
        data.put("itemList",gson.toJson(itemDTOList));
        data.put("bidItemList",gson.toJson(bidItemDTOList));
        out.write(gson.toJson(data));
    }
}
