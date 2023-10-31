package com.twoclothing.chenghan.service;

import com.twoclothing.model.abid.biditem.BidItem;
import com.twoclothing.model.abid.biditem.BidItemDAO;
import com.twoclothing.model.abid.biditem.BidItemHibernateDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.abid.biditemimage.BidItemImageDAO;
import com.twoclothing.model.abid.biditemimage.BidItemImageHibernateDAO;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.categorytags.CategoryTagsDAO;
import com.twoclothing.model.categorytags.CategoryTagsHibernateDAO;
import com.twoclothing.model.employee.Employee;
import com.twoclothing.model.employee.EmployeeDAO;
import com.twoclothing.model.employee.EmployeeHibernateDAO;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.*;

public class BidItemServiceImpl implements BidItemService {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final BidItemDAO bidItemDAO = new BidItemHibernateDAO(sessionFactory);

    private final BidItemImageDAO bidItemImageDAO = new BidItemImageHibernateDAO(sessionFactory);

    private final CategoryTagsDAO categoryTagsDAO = new CategoryTagsHibernateDAO(sessionFactory);

    private final EmployeeDAO employeeDAO = new EmployeeHibernateDAO(sessionFactory);

    private final MembersDAO membersDAO = new MembersHibernateDAO(sessionFactory);

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
    public List<BidItem> getAllBidItemByMbrid(Integer mbrId) {
        return bidItemDAO.getAllByMbrId(mbrId);
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
            List<Members> members = membersDAO.getAllByMbrName(processedQuery.get("mbrName")[0]);
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
    public boolean updateBidItemStatus(BidItem bidItem) {
        return true;
    }
}
