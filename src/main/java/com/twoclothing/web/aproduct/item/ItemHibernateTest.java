package com.twoclothing.web.aproduct.item;

public class ItemHibernateTest {
	
	public static void main(String[] args) throws Exception{
		
		ItemDAO dao = new ItemHibernateDAO();
		//新增
		Item item1 = new Item();
		item1.setItemName("Product11");
		item1.setDetail("11");
		item1.setTagId(1);
		item1.setMbrId(11);
		item1.setPrice(100);
		item1.setItemStatus(0);
		item1.setQuantity(1);
		dao.insert(item1);
		
		
		
		
	}

}
