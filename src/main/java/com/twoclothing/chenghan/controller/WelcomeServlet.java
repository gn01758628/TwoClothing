package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.chenghan.dto.BidIItemDTO;
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
import java.util.Collections;
import java.util.List;

@WebServlet("/welcomeHelper/*")
public class WelcomeServlet extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final Gson gson = new Gson();

    private final ItemDAO itemDAO = new ItemHibernateDAO(sessionFactory);

    private final BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);

    private final BidRecordDAO bidRecordDAO = new BidRecordJedisDAO();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/randomItem" -> doItem(request, response);
            case "/randomBidItem" -> doBidItem(request, response);
        }

    }

    private void doItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String tagId = request.getParameter("tagId");
        List<Item> itemList;
        List<Item> randomSubList;
        List<ItemDTO> itemDTOList = new ArrayList<>();

        if (tagId == null) {
            itemList = itemDAO.getAllByItemStatus(0);
            randomSubList = getRandomSubList(itemList, 10);
            for (Item item : randomSubList) {
                ItemDTO dto = new ItemDTO();
                dto.setItemId(item.getItemId());
                dto.setItemName(item.getItemName());
                dto.setPrice(item.getPrice());
                itemDTOList.add(dto);
            }
        } else {
            itemList = itemDAO.getAllSubByTagId(Integer.parseInt(tagId));
            randomSubList = getRandomSubList(itemList, 10);
            for (Item item : randomSubList) {
                ItemDTO dto = new ItemDTO();
                dto.setItemId(item.getItemId());
                dto.setItemName(item.getItemName());
                dto.setPrice(item.getPrice());
                itemDTOList.add(dto);
            }
        }
        out.write(gson.toJson(itemDTOList));
    }


    private void doBidItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String tagId = request.getParameter("tagId");
        List<BidItem> bidItemList;
        List<BidItem> randomSubList;
        List<BidIItemDTO> bidIItemDTOList = new ArrayList<>();

        if (tagId == null) {
            bidItemList = bidItemDAO.getAllByBidStatus(4);
            randomSubList = getRandomSubList(bidItemList, 10);
            for (BidItem item : randomSubList) {
                BidIItemDTO dto = new BidIItemDTO();
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
                bidIItemDTOList.add(dto);

            }
        } else {
            bidItemList = bidItemDAO.getAllSubByTagId(Integer.parseInt(tagId));
            randomSubList = getRandomSubList(bidItemList, 10);
            for (BidItem item : randomSubList) {
                BidIItemDTO dto = new BidIItemDTO();
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
                bidIItemDTOList.add(dto);
            }
        }

        out.write(gson.toJson(bidIItemDTOList));
    }

    static <T> List<T> getRandomSubList(List<T> sourceList, int itemsToSelect) {
        List<T> randomSubset = new ArrayList<>(sourceList);
        Collections.shuffle(randomSubset);
        if (itemsToSelect >= sourceList.size()) return randomSubset;
        return randomSubset.subList(0, itemsToSelect);
    }
}
