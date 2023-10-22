package com.twoclothing.huiwen.service;

import java.util.List;
import java.util.Map;

import com.twoclothing.model.aproduct.item.Item;

public interface ItemService {
	
	Item addItem(String itemName, Integer grade, Integer size, String detail, Integer tagId, Integer mbrId, Integer price, Integer itemStatus, Integer quantity);
	
	Item updateItem(Item item);
	
	void deleteItem(Integer itemId);
	
	Item getItemByItemId(Integer itemId);
	
	List<Item> getItemByCompositeQuery(Map<String, String[]> map, int pageNow);
	
	List<Item> getAllItems(int page);
	
	int getPageTotal();

	List<Item> getAllItems();
	
	
}
