package com.twoclothing.web.cartdetail;

import java.util.List;

public interface CartDetailDAO {
	
	 public void insert(CartDetail cartId);

	 public CartDetail getByPrimaryKey(Integer cartId);

	 public List<CartDetail> getAll();

	 public List<CartDetail> getAllByMbrId(Integer mbrId);

	 public void update(CartDetail cartdetail);
	 
	 public void delete(Integer cartId);

}
