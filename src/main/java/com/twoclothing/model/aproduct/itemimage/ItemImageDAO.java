package com.twoclothing.model.aproduct.itemimage;

import java.util.List;

public interface ItemImageDAO {
	
	 int insert(ItemImage imgId);

	 ItemImage getByPrimaryKey(Integer imgId);

	/**
	 * @param position 第幾張圖片
	 */
	ItemImage getPositionImageByItemId(Integer itemId, int position);

	 List<ItemImage> getAll();

	 List<ItemImage> getAllByItemId(Integer itemId);

	 boolean update(ItemImage itemImage);
	 
	 void delete(Integer imgId);
}
