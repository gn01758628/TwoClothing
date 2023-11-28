package com.twoclothing.model.aproduct.itemorder;

import java.util.List;

public interface ItemOrderDAO {
	void insert(ItemOrder itemOrder);

	ItemOrder getByPrimaryKey(Integer orderId);
	
	List<ItemOrder> getAll();
	
	List<ItemOrder> getAllByBuyMbrId(Integer buyMbrId);
	
	List<ItemOrder> getAllBySellMbrId(Integer sellMbrId);
	
	List<ItemOrder> getAllByOrderStatus(Integer orderStatus);

	void update(ItemOrder itemOrder);
}
