package com.twoclothing.model.aproduct.cartdetail;

import java.util.List;

public class CartDetailTest {
	public static void main(String[] args) {
		
		CartDetail cd1 = new CartDetail(null,111,12,1);
		CartDetail cd2 = new CartDetail(11,112,13,1);
		CartDetail cd3 = new CartDetail(113,14,1,11);
		
		CartDetailDAO cartDetailDAO = new CartDetailJDBCDAO();
		
		CartDetail[] arr = {cd2};
		for (CartDetail c : arr) {
			cartDetailDAO.insert(c);
		}
		
		System.out.println("=====================================================================================================================================");
		
		CartDetail byPrimaryKey = cartDetailDAO.getByPrimaryKey(1);
		CartDetail byPrimaryKey1 = cartDetailDAO.getByPrimaryKey(6);
        System.out.println(byPrimaryKey);
        System.out.println(byPrimaryKey1);
        
        System.out.println("=====================================================================================================================================");
        
        List<CartDetail> list = cartDetailDAO.getAll();
        for (CartDetail c : list) {
            System.out.println(c);
        }
        
        List<CartDetail> list2 = cartDetailDAO.getAllByMbrId(10);
        for (CartDetail c : list2) {
            System.out.println(c);
        }
        
        System.out.println("=====================================================================================================================================");
        cartDetailDAO.update(cd3);
        
        System.out.println("=====================================================================================================================================");
        
//        cartDetailDAO.delete(10);
	}

}
