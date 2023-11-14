package com.twoclothing.huiwen.service;

import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.twoclothing.model.abid.biditemimage.BidItemImage;
import com.twoclothing.model.aproduct.item.Item;
import com.twoclothing.model.aproduct.itemimage.ItemImage;
import com.twoclothing.model.categorytags.CategoryTags;
import com.twoclothing.model.coupon.Coupon;
import com.twoclothing.model.memberscoupon.MembersCoupon;
import com.twoclothing.model.shipsetting.ShipSetting;

public interface ItemService {
	
	int addItem(Item item);
	
	Item updateItem(Item item);
	
	void deleteItem(Integer itemId);
	
	Item getItemByItemId(Integer itemId);
	
	List<Item> getItemByCompositeQuery(Map<String, String[]> map, int page);
	
	List<Item> getAllItems(int page);
	
	int getPageTotal();

	int getResultTotalCondition(Map<String, String[]> map);

	int updateItem(Integer itemId, String itemName, Integer grade, Integer size, String detail, Integer price, Integer quantity);

	List<Integer> getAllSelectableTagsId();

	List<CategoryTags> getAllCategoryTags();
	
    void addItemImage(ItemImage itemImage);
    
    Integer getMbrPointByMbrId(Integer mbrId);

//    public MembersCoupon getMemCouponByPK(Integer mbrId, Integer cpnId);
    
    public Coupon getCouponByPK(Integer cpnId);
    
    List<Coupon> getAllCoupon();
    
    public List<MembersCoupon> getMemCouponByMbrId(String mbrId, Integer value);
    
    List<ShipSetting> getSettingByMbrId(Integer mbrId);
    
    Integer getMbrIdByItemId(Integer itemId);

}
