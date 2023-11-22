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
import com.twoclothing.model.coupon.CouponJeidsDAO;
import com.twoclothing.model.members.Members;
import com.twoclothing.model.members.MembersDAO;
import com.twoclothing.model.members.MembersHibernateDAO;
import com.twoclothing.model.memberscoupon.MembersCoupon;
import com.twoclothing.model.memberscoupon.MembersCoupon.MembersCouponCompositeDetail;
import com.twoclothing.model.shipsetting.ShipSetting;
import com.twoclothing.model.shipsetting.ShipSettingDAO;
import com.twoclothing.model.shipsetting.ShipSettingHibernateDAO;
import com.twoclothing.utils.HibernateUtil;
import com.twoclothing.utils.generic.*;

public class ItemServiceImpl implements ItemService{
	
	private ItemDAO dao;
	
    private CategoryTagsDAO categoryTagsDAO;
    
    private ItemImageDAO itemImageDAO ;

    private GenericDAO couponDAO;
    
    private GenericDAO MemCouponDAO;
    
    private ShipSettingDAO shipSettingDAO;
    
    private MembersDAO  membersDAO;
    
	public ItemServiceImpl() {
		dao = new ItemHibernateDAO(HibernateUtil.getSessionFactory());
		categoryTagsDAO = new CategoryTagsHibernateDAO(HibernateUtil.getSessionFactory());
		itemImageDAO = new ItemImageHibernateDAO(HibernateUtil.getSessionFactory());
		MemCouponDAO = DAOSelector.getDAO(MembersCoupon.class);
		couponDAO = DAOSelector.getDAO(Coupon.class);
		shipSettingDAO = new ShipSettingHibernateDAO(HibernateUtil.getSessionFactory());
		membersDAO = new MembersHibernateDAO(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public int addItem(Item item) {
		return dao.insert(item);
	}

	@Override
	public int updateItem(Item item) {
		return dao.update(item);
	}

//	@Override
//	public Item updateItem(Item item) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public void deleteItem(Integer itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item getItemByItemId(Integer itemId) {
		return dao.getByPrimaryKey(itemId);
	}

	@Override
	public List<Item> getAllItems(int page) {
		List<Item> list = dao.getAll(page);
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
//		Map<String, String> query = new HashMap<>();
//		// Map.Entry即代表一組key-value
//		Set<Map.Entry<String, String[]>> entry = map.entrySet();
//		
//		for (Map.Entry<String, String[]> row : entry) {
//			String key = row.getKey();
//			// 因為請求參數裡包含了action，做個去除動作
//			if ("choice".equals(key)) {
//				continue;
//			}
//			// 若是value為空即代表沒有查詢條件，做個去除動作
//			String value = row.getValue()[0];
//
//			
//			if ( value == null || value.isEmpty()) {
//				continue;
//			}
//			query.put(key, value);
//		}
//System.out.println(query);
		
		
	    Map<String, String> query = new HashMap<>();
	    
	    Set<Map.Entry<String, String[]>> entrySet = map.entrySet();
	    
	    for (Map.Entry<String, String[]> entry : entrySet) {
	        String key = entry.getKey();
	        String[] values = entry.getValue();
	        
	        if ("choice".equals(key)) {
	            continue;
	        }
		
		
		if (values != null && values.length > 0) {
            String value = values[0];
            if (value != null && !value.isEmpty()) {
                if ("itemQuantity".equals(key)) {
                    switch (value) {
                        case "2":
                            query.put("itemQuantityStart", "0");
                            query.put("itemQuantityEnd", "5");
                            break;
                        case "3":
                            query.put("itemQuantityStart", "6");
                            // 可以不加這行，因為 Map 本身不包含該 key 就代表是 null
                            query.remove("itemQuantityEnd");
                            break;
                        default:
                            break;
                    }
                } else {
                    // 將原有的 key-value 放入 query 中
                    query.put(key, value);
                }
            }
		}
	}
	
	System.out.println("query :" + query);
		return dao.getByCompositeQuery(query, page);
	}


	public int getResultTotalCondition(Map<String, String[]> map) {
//		Map<String, String> query = new HashMap<>();
//		// Map.Entry即代表一組key-value
//		Set<Map.Entry<String, String[]>> entry = map.entrySet();
//		
//		for (Map.Entry<String, String[]> row : entry) {
//			String key = row.getKey();
//			// 因為請求參數裡包含了action，做個去除動作
//			if ("choice".equals(key)) {
//				continue;
//			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
//			String value = row.getValue()[0];
//			if ( value == null || value.isEmpty()) {
//				continue;
//			}
//			query.put(key, value);
			
			
			
			
		    Map<String, String> query = new HashMap<>();
		    
		    Set<Map.Entry<String, String[]>> entrySet = map.entrySet();
		    
		    for (Map.Entry<String, String[]> entry : entrySet) {
		        String key = entry.getKey();
		        String[] values = entry.getValue();
		        
		        if ("choice".equals(key)) {
		            continue;
		        }
			
			
			if (values != null && values.length > 0) {
	            String value = values[0];
	            if (value != null && !value.isEmpty()) {
	                if ("itemQuantity".equals(key)) {
	                    switch (value) {
	                        case "2":
	                            query.put("itemQuantityStart", "0");
	                            query.put("itemQuantityEnd", "5");
	                            break;
	                        case "3":
	                            query.put("itemQuantityStart", "6");
	                            // 可以不加這行，因為 Map 本身不包含該 key 就代表是 null
	                            query.remove("itemQuantityEnd");
	                            break;
	                        default:
	                            // 其他情況，可以根據需要進行處理
	                            break;
	                    }
	                } else {
	                    // 將原有的 key-value 放入 query 中
	                    query.put(key, value);
	                }
	            }
			}
		}
		
		System.out.println("query :" + query);
		
		return dao.getResultTotal(query);
	}
	
    @Override
    public List<CategoryTags> getAllCategoryTags() {
        return categoryTagsDAO.getAll();
    }
	
	@Override
    public List<Integer> getAllSelectableTagsId() {
		System.out.println(categoryTagsDAO.getTagIdsWithoutChildren());
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
	public Integer getSellScoreByMbrId(Integer mbrId) {
		return dao.getSellScoreByMbrId(mbrId);
	}
	@Override
	public Integer getMbrBalanceByMbrId(Integer mbrId) {
		return dao.	getbalanceByMbrId(mbrId);
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

	@Override
	public List<ShipSetting> getSettingByMbrId(Integer mbrId) {
		return shipSettingDAO.getAllByMbrId(mbrId);
	}

	@Override
	public Integer getMbrIdByItemId(Integer itemId) {
		return dao.getMbrIdById(itemId);
	}
	


	@Override
	public List<Item> getItemBymbrIdAndStatus(Integer mbrId) {
		return dao.getItemByMbrIdAndStatus(mbrId);
	}
	
	@Override
	public Members getMembersByPK(Integer mbrId) {
		return membersDAO.getByPrimaryKey(mbrId);
	}

	@Override
	public CategoryTags getByPrimaryKey(Integer tagId) {
		return categoryTagsDAO.getByPrimaryKey(tagId);
	}
	@Override
	public List<Item> getAllByStatus(Integer itemStatus) {
		return dao.getAllByItemStatus(itemStatus);
	}
	@Override
	public List<Integer> getItemByMbrId(Integer mbrId) {
		return dao.getItemByMbrId(mbrId);
	}

}