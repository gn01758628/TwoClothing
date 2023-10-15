package com.twoclothing.model.aproduct.orderratingimage;

import java.util.List;

public interface OrderRatingImageDAO {
	void insert(OrderRatingImage orderRatingImage);
	
	OrderRatingImage getByPrimaryKey(Integer imageId);
	
	List<OrderRatingImage> getAll();
	
	List<OrderRatingImage> getAllByOrderId (Integer orderId);
	
	void update(OrderRatingImage orderRatingImage);
}
