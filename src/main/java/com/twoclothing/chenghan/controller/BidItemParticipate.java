package com.twoclothing.chenghan.controller;

import com.google.gson.Gson;
import com.twoclothing.chenghan.dto.BidItemDTO;
import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
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
import java.util.List;

@WebServlet("/bidItemParticipate.check")
public class BidItemParticipate extends HttpServlet {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);
    private final BidRecordDAO bidRecordDAO = new BidRecordJedisDAO();

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Integer mbrId = (Integer) request.getSession().getAttribute("mbrId");
        List<Integer> bitItemIdList = bidRecordDAO.getAllBitItemIdByMbrId(mbrId);
        List<BidItemDTO> bidItemDTOList = new ArrayList<>();
        for (Integer bidItemId : bitItemIdList) {
            BidItem bidItem = bidItemDAO.getByPrimaryKey(bidItemId);
            BidItemDTO dto = new BidItemDTO();
            dto.setBidItemId(bidItemId);
            dto.setEndTime(bidItem.getEndTime().getTime());
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
        String[] msgArr = {gson.toJson(bidItemDTOList),gson.toJson(mbrId)};
        out.write(gson.toJson(msgArr));
    }
}
