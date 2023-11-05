package com.twoclothing.huiwen.service;

import static com.twoclothing.huiwen.Constants.ITEM_PAGE_MAX_RESULT;

import java.util.List;
import java.util.Map;

import java.util.HashMap;
import java.util.Set;

import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.item.ItemDAO;
import com.twoclothing.model.aproduct.item.ItemHibernateDAO;
import com.twoclothing.model.aproduct.itemimage.ItemImage;
import com.twoclothing.model.aproduct.itemimage.ItemImageDAO;
import com.twoclothing.model.aproduct.itemimage.ItemImageHibernateDAO;
import com.twoclothing.model.aproduct.itemtracking.ItemTracking;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.categorytags.CategoryTagsDAO;
import com.twoclothing.model.categorytags.CategoryTagsHibernateDAO;
import com.twoclothing.model.coupon.Coupon;
import com.twoclothing.model.coupon.CouponDAO;
import com.twoclothing.model.memberscoupon.MembersCoupon;
import com.twoclothing.model.memberscoupon.MembersCoupon.MembersCouponCompositeDetail;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.generic.*;

public class ItemServiceImpl implements ItemService{
	
	private ItemDAO dao;
	
    private CategoryTagsDAO categoryTagsDAO;
    
    private ItemImageDAO itemImageDAO ;

    private GenericDAO couponDAO;
    
    private GenericDAO MemCouponDAO;
    
	public ItemServiceImpl() {
		dao = new ItemHibernateDAO(HibernateUtil.getSessionFactory());
		categoryTagsDAO = new CategoryTagsHibernateDAO(HibernateUtil.getSessionFactory());
		itemImageDAO = new ItemImageHibernateDAO(HibernateUtil.getSessionFactory());
		MemCouponDAO = DAOSelector.getDAO(MembersCoupon.class);
		couponDAO = DAOSelector.getDAO(Coupon.class);
	}
	
	@Override
	public int addItem(Item item) {
		return dao.insert(item);
	}

	@Override
	public int updateItem(Integer itemId, String itemName, Integer grade, Integer size, String detail, Integer price, Integer quantity) {
		Item item = dao.getByPrimaryKey(itemId);
		item.setItemName(itemName);
		item.setGrade(grade);
		item.setSize(size);
		item.setDetail(detail);
		item.setPrice(price);
		item.setQuantity(quantity);
		return dao.update(item);
	}

	@Override
	public Item updateItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteItem(Integer itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item getItemByItemId(Integer itemId) {
		Item item1 = dao.getByPrimaryKey(itemId);
		System.out.println("item1:"+item1);
		
		return item1;
	}

	@Override
	public List<Item> getAllItems(int page) {
		List<Item> list = dao.getAll(page);
		System.out.println("2222"+list);

		return list;
	}
	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		//計算5筆為一頁的話共有幾頁
		int pageQty = (int)(total % ITEM_PAGE_MAX_RESULT == 0 ? (total / ITEM_PAGE_MAX_RESULT) : (total / ITEM_PAGE_MAX_RESULT + 1));
		return pageQty;
	}


	@Override
	public List<Item> getItemByCompositeQuery(Map<String, String[]> map, int page) {
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0];

//			System.out.println("keyValue:"+key+":"+value);
			
			if ( value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}

		return dao.getByCompositeQuery(query, page);
	}


	public int getResultTotalCondition(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0];
			if ( value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}
		
		System.out.println("query :" + query);
		
		return dao.getResultTotal(query);
	}
	
    @Override
    public List<CategoryTags> getAllCategoryTags() {
    	System.out.println("!!!!"+categoryTagsDAO.getAll());
        return categoryTagsDAO.getAll();
    }
	
	@Override
    public List<Integer> getAllSelectableTagsId() {
System.out.println("::"+categoryTagsDAO.getTagIdsWithoutChildren());
        return categoryTagsDAO.getTagIdsWithoutChildren();
    }

	@Override
	public void addItemImage(ItemImage itemImage) {
		itemImageDAO.insert(itemImage);
		
	}

	@Override
	public Integer getMbrPointByMbrId(Integer mbrId) {
		return dao.getPointByMbrId(mbrId);
	}
	
	@Override
	public List<MembersCoupon> getMemCouponByMbrId(String mbrId, Integer value) {
		return MemCouponDAO.getBy("mbrId", value);
	}

	@Override
	public Coupon getCouponByPK(Integer cpnId) {
		return (Coupon)couponDAO.getByPrimaryKey(cpnId);
	}

	@Override
	public List<Coupon> getAllCoupon() {
		return couponDAO.getAll();
	
	}


}
//MembersCoupon.MembersCouponCompositeDetail compositeKey = new MembersCoupon.MembersCouponCompositeDetail(mbrId, cpnId);
//return (MembersCoupon)MemCouponDAO.getByPrimaryKey(compositeKey);
