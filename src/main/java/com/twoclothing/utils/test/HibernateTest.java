package com.twoclothing.utils.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.twoclothing.model.department.Department;

public class HibernateTest {
	public static void main(String[] args) {

		// 載入 hibernate.cfg.xml 檔案
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		// 利用註冊檔,建立SessionFactory
		// 		SessionFactory封裝了Hibernate的執行環境
		//			1.重量級：產生SessionFactory需要讀取hibernate.cfg.xml，非常花費執行資源
		//			2.不可變：一旦建立了SessionFactory後，就無法再對其組態檔配置再做改變，除非再創建一個新的物件
		//			3.執行緒安全：SessionFactory可以在多執行緒環境裡給執行緒共用，沒有同步問題
		//			因為1.2.3所有有HibernateUtil
		SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		// 從SessionFactory取得Session
		// 		org.hibernate.Session是Hibernate應用程式使用的主要介面
		// 		Hibernate在對資料庫進行操作之前，必須先取得Session的物件，類似於JDBC取得Connection物件
		// 		Session是輕量的，在創建與關閉時不會消耗太多資源
		Session session = factory.openSession();

		// 利用Session物件啟動一個transaction
		// 		Hibernate利用org.hibernate.Transaction介面實作交易
		// 		由Session物件控制，一個Session 可以開啟多個transaction物件
		// 		同一時間裡，只能有一個尚未commit/rollback的transaction物件
		// 		not thread-safe的物件
		Transaction tx = session.beginTransaction();

		/// 從交易區間開始進行對資料庫的資料操作 ///

		Department department = new Department();
		department.setDeptId(4);
		department.setDeptName("測試部");
		
		session.save(department);

		/// 區間結束 ///

		tx.commit();

		// 依順序釋放資源
		session.close();
		factory.close();
	}
}
