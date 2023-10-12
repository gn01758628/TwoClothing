package com.twoclothing.web.aproduct.item;


public class ItemHibernateTest {
	
	public static void main(String[] args) throws Exception {
		
		ItemDAO dao = new ItemHibernateDAO();
		
		//新增
		Item item1 = new Item();
		item1.setName("商品11");
		item1.setDetail("這是商品11的描述");
		item1.setTagId(3);
		item1.setMbrId(111);
		item1.setPrice(100);
		item1.setItemStatus(0);
		item1.setQuantity(1);
		
		dao.insert(item1);
		
		System.out.println(item1);
		

	}


}
