package com.twoclothing.model.aproduct.ordernotify;

import java.util.List;

public interface OrderNotifyDAO {
	void insert(OrderNotify orderNotify);
	
	OrderNotify getByPrimaryKey(Integer notifyId);
	
	List<OrderNotify> getAll();
	
	List<OrderNotify> getAllByMbrId(Integer mbrId);
	
	List<OrderNotify> getAllByOrderId(Integer orderId);
	
	void update(OrderNotify orderNotify);
}
