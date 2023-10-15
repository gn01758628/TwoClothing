package com.twoclothing.model.aproduct.item;

import java.util.List;


public interface ItemDAO {
	
	 public int insert(Item item);

	 public Item getByPrimaryKey(Integer itemId);

	 public List<Item> getAll();

	 public List<Item> getAllByTagId(Integer tagId);

	 public List<Item> getAllByMbrId(Integer mbrId);
	 
	 public List<Item> getAllByItemStatus(Integer itemStatus);

	 public int update(Item item);

}
