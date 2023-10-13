package com.twoclothing.web.aproduct.item;

import java.util.List;


public interface ItemDAO {
	
	 public void insert(Item item);

	 public Item getByPrimaryKey(Integer itemId);

	 public List<Item> getAll();

	 public List<Item> getAllByTadId(Integer tadId);

	 public List<Item> getAllByMbrId(Integer mbrId);
	 
	 public List<Item> getAllByItemStatus(Integer itemStatus);

	 public void update(Integer itemId);

}
