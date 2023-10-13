package com.twoclothing.web.members;

import java.util.List;

public class MembersHibernateTest {

	public static void main(String[] args) {
		MembersHibernateDAO membersHibernateDAO = new MembersHibernateDAO();
		// 新增
		Members members1 = new Members();
		members1.setEmail("emailX@XXX.XXX");
		members1.setPswdHash("123345");
		membersHibernateDAO.insert(members1);

		// 查詢單筆會員ID
		Members members2 = membersHibernateDAO.getByPrimaryKey(1);
		System.out.println("查詢單筆---------------------");
		System.out.println(members2);
		System.out.println("---------------------");
		
		// 查詢多筆
		System.out.println("查詢多筆---------------------");
		List<Members> list = membersHibernateDAO.getAll();
		for (Members s : list) {
			System.out.println(s);
		}
		System.out.println("---------------------");
		
		// 會員名稱模糊查詢
		System.out.println("會員名稱模糊查詢---------------------");
		List<Members> list2 = membersHibernateDAO.getAllByMbrName("e");
		for (Members s : list2) {
			System.out.println(s);
		}
		System.out.println("---------------------");
	}
}
