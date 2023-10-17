package com.twoclothing.model.aproduct.itemimage;

import java.util.List;

public interface ItemImageDAO {
	
	 public int insert(ItemImage imgId);

	 public ItemImage getByPrimaryKey(Integer imgId);

	 public List<ItemImage> getAll();

	 public List<ItemImage> getAllByItemId(Integer itemId);

	 public boolean update(ItemImage itemImage);
	 
	 public void delete(Integer imgId);
}
