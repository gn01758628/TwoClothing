package com.twoclothing.web.aproduct.orderdetails;

import java.util.List;

public interface OrderDetailsDAO {
	void insert(OrderDetails orderDetails);
	
	OrderDetails getByCompositeKey(Integer orderId, Integer itemId);
	
	List<OrderDetails> getAll();
	
	List<OrderDetails> getAllByOrderId(Integer orderId);
}
