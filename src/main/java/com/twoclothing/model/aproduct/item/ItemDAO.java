package com.twoclothing.model.aproduct.item;

import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;


public interface ItemDAO {
	
	 public int insert(Item item);

	 public Item getByPrimaryKey(Integer itemId);

//	 public List<Item> getAll();
	 
	 public List<Item> getAll(int page);

	 public List<Item> getAllByTagId(Integer tagId);

	 public List<Item> getAllByMbrId(Integer mbrId);
	 
	 public List<Item> getAllByItemStatus(Integer itemStatus);
	 
	 public List<Item> getAllSubByTagId(Integer tagId);

	 public int update(Item item);

	 public long getTotal();

	 List<Item> getByCompositeQuery(Map<String, String> map, int page);

	 public int getResultTotal(Map<String, String> map);
	
	 Integer getPointByMbrId(Integer mbrId);
	 
	 Integer getSellScoreByMbrId(Integer mbrId);
	 
	 Integer getbalanceByMbrId(Integer mbrId);
	 
	 Integer getMbrIdById(Integer itemId);
	 
	 List<Item> getItemByMbrIdAndStatus(Integer mbrId);
	 
	 List<Integer> getItemByMbrId(Integer mbrId);

}
