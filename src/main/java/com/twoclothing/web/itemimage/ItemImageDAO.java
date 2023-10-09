package com.twoclothing.web.itemimage;

import java.util.List;

public interface ItemImageDAO {
	
	 public void insert(ItemImage imgId);

	 public ItemImage getByPrimaryKey(Integer imgId);

	 public List<ItemImage> getAll();

	 public List<ItemImage> getAllByItemId(Integer itemId);

	 public void update(Integer imgId);
	 
	 public void delete(Integer imgId);
}
