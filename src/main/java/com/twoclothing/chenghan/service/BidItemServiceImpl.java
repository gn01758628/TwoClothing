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

import java.util.List;

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
    public List<BidItem> getAllBidItemByMbrid(Integer mbrId) {
        return bidItemDAO.getAllByMbrId(mbrId);
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
}
