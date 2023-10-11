package com.twoclothing.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.twoclothing.web.department.Department;

public class HibernateTest {
	public static void main(String[] args) {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		
		SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		/// 從交易區間開始進行對資料庫的資料操作 ///

		Department department = new Department();
		department.setDeptId(4);
		department.setDeptName("測試部");
		
		session.save(department);

		/// 區間結束 ///
		tx.commit();
		session.close();
		factory.close();
	}
}
