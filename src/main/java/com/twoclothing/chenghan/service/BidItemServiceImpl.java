package com.twoclothing.chenghan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.abid.biditemimage.BidItemImageDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImageHibernateDAO;
import com.twoclothing.model.abid.bidorder.BidOrder;
import com.twoclothing.model.abid.bidorder.BidOrderDAO;
import com.twoclothing.model.abid.bidorder.BidOrderHIbernateDAO;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.categorytags.CategoryTagsDAO;
import com.twoclothing.model.categorytags.CategoryTagsHibernateDAO;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.employee.EmployeeDAO;
import com.twoclothing.model.employee.EmployeeHibernateDAO;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.redismodel.bidItemViewHistory.BidItemViewHistory;
import com.twoclothing.redismodel.bidItemViewHistory.BidItemViewHistoryDAO;
import com.twoclothing.redismodel.bidItemViewHistory.BidItemViewHistoryJedisDAO;
import com.twoclothing.redismodel.bidrecord.BidRecord;
import com.twoclothing.redismodel.bidrecord.BidRecordDAO;
import com.twoclothing.redismodel.bidrecord.BidRecordJedisDAO;
import com.twoclothing.redismodel.notice.Notice;
import com.twoclothing.redismodel.notice.NoticeDAO;
import com.twoclothing.redismodel.notice.NoticeJedisDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.util.*;

public class BidItemServiceImpl implements BidItemService {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);

    private final BidItemImageDAO bidItemImageDAO = new BidItemImageHibernateDAO(sessionFactory);

    private final CategoryTagsDAO categoryTagsDAO = new CategoryTagsHibernateDAO(sessionFactory);

    private final EmployeeDAO employeeDAO = new EmployeeHibernateDAO(sessionFactory);

    private final MembersDAO membersDAO = new MembersHibernateDAO(sessionFactory);

    private final BidOrderDAO bidOrderDAO = new BidOrderHIbernateDAO(sessionFactory);

    private final NoticeDAO noticeDAO = new NoticeJedisDAO();

    private final BidRecordDAO bidRecordDAO = new BidRecordJedisDAO();

    private final BidItemViewHistoryDAO bidItemViewHistoryDAO = new BidItemViewHistoryJedisDAO();

    public BidItemServiceImpl() {
    }

    @Override
    public int addBidItem(BidItem bidItem) {
        return bidItemDAO.insert(bidItem);
    }

    @Override
    public void addBidItemImage(BidItemImage bidItemImage) {
        bidItemImageDAO.insert(bidItemImage);
    }

    @Override
    public void addNotice(Notice notice, Integer mbrId) {
        noticeDAO.insert(notice, mbrId);
    }

    @Override
    public void addBidRecord(BidRecord bidRecord, Integer bidItemId, LocalDateTime endTime) {
        bidRecordDAO.insert(bidRecord, bidItemId, endTime);
    }

    @Override
    public void addBidItemViewHistory(BidItemViewHistory bidItemViewHistory) {
        bidItemViewHistoryDAO.insert(bidItemViewHistory);
    }

    @Override
    public void addBidOrder(BidOrder bidOrder) {
        bidOrderDAO.insert(bidOrder);
    }

    @Override
    public BidItem getBidItemByBidItemId(Integer bidItemId) {
        return bidItemDAO.getByPrimaryKey(bidItemId);
    }

    @Override
    public Members getMembersByMbrId(Integer mbrId) {
        return membersDAO.getByPrimaryKey(mbrId);
    }

    @Override
    public Employee getEmployeeByEmpId(Integer empId) {
        return employeeDAO.getByPrimaryKey(empId);
    }

    @Override
    public CategoryTags getCategoryTagsByTagId(Integer tagId) {
        return categoryTagsDAO.getByPrimaryKey(tagId);
    }

    @Override
    public List<BidItem> getAllLegalBidItemByMbrid(Integer mbrId) {
        return bidItemDAO.getAllLegalByMbrId(mbrId);
    }

    @Override
    public List<BidItem> getAllBidItemByCompositeQuery(Map<String, String[]> compositeQuery) {
        Map<String, String[]> processedQuery = new HashMap<>();
        Set<Map.Entry<String, String[]>> entries = compositeQuery.entrySet();
        // 空值的name不放進去
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            // 沒有值的key
            if (value[0] == null || value[0].isEmpty()) continue;
            processedQuery.put(key, value);
        }

        // 收集不重覆的會員Id
        Set<String> mbrIdSet = new HashSet<>();
        // 處理email模糊查詢
        if (processedQuery.containsKey("email")) {
            List<Members> members = membersDAO.getAllByEmail(processedQuery.get("email")[0]);
            processedQuery.remove("email");
            // 收集會員Id
            for (Members member : members) {
                mbrIdSet.add(String.valueOf(member.getMbrId()));
            }
        }
        // 處理mbrName模糊查詢
        if (processedQuery.containsKey("mbrName")) {
            String mbrName = processedQuery.get("mbrName")[0].trim();
            List<Members> members = membersDAO.getAllByMbrName(mbrName);
            processedQuery.remove("mbrName");
            // 收集會員Id
            for (Members member : members) {
                mbrIdSet.add(String.valueOf(member.getMbrId()));
            }
        }
        // 檢查是否有同時輸入會員編號
        if (processedQuery.containsKey("mbrId")) mbrIdSet.add(processedQuery.get("mbrId")[0]);

        // 覆蓋mbrId
        if (!mbrIdSet.isEmpty()) {
            processedQuery.put("mbrId", mbrIdSet.toArray(new String[0]));
        }
        return bidItemDAO.getAllByCompositeQuery(processedQuery);
    }

    @Override
    public List<CategoryTags> getAllCategoryTags() {
        return categoryTagsDAO.getAll();
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDAO.getAll();
    }

    @Override
    public List<Integer> getAllSelectableTagsId() {
        return categoryTagsDAO.getTagIdsWithoutChildren();
    }

    @Override
    public List<BidRecord> getAllBidRecordByBidItemId(Integer bidItemId) {
        return bidRecordDAO.getAll(bidItemId);
    }

    @Override
    public Set<Integer> getAllMbrIdInBidRecord(Integer bidItemId) {
        return bidRecordDAO.getAllMbrIdByKey(bidItemId);
    }

    @Override
    public BidRecord getBidRecordByIndex(Integer bidItemId, int index) {
        return bidRecordDAO.getIndexRecordByKey(bidItemId, index);
    }

    @Override
    public boolean updateBidItem(BidItem bidItem) {
        return bidItemDAO.update(bidItem);
    }

    @Override
    public boolean isDoubleImaged(Integer bidItemId) {
        BidItemImage img = bidItemImageDAO.getPositionImageByBidItemId(bidItemId, 2);
        if (img == null) {
            return false;
        }
        return img.getImage() != null;
    }
}
