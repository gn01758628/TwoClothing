package com.twoclothing.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
//引用的JavaAPI
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

//測試用的各類別VO
import com.twoclothing.utils.GenericDAO;
import com.twoclothing.web.balancehistory.BalanceHistory;
import com.twoclothing.web.categorytags.CategoryTags;
import com.twoclothing.web.categorytags.CategoryTagsDAO;
import com.twoclothing.web.categorytags.CategoryTagsJDBCDAO;
import com.twoclothing.web.employee.Employee;

public class GenericTest {

	public static void main(String[] args) {

		GenericDAO<Employee> employeeDAO = new GenericDAO<>(Employee.class);
		GenericDAO<BalanceHistory> balanceHistoryDAO = new GenericDAO<>(BalanceHistory.class);
		GenericDAO<CategoryTags> categorytagsDAO = new GenericDAO<>(CategoryTags.class);

//		========================= insert =========================

//		List<Employee> employees = new ArrayList<>();
//
//		for (int i = 0; i < 5; i++) {
//			Integer deptId = i + 1;
//			String empName = "Employee " + (i + 1);
//			String phone = "123456789" + (i + 1);
//			String address = "Address " + (i + 1);
//			String email = "employee" + (i + 1) + "@example.com";
//			String pswdHash = "passwordHash" + (i + 1);
//			Integer empStatus = i % 2; 
//			String path = "images/"+(i+1)+".jpg";
//			System.out.println(path);
//			byte[] avatar = null;
//			try {
//				avatar = getPictureByteArray(path);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			
//			Employee employee = new Employee(deptId, empName, phone, address, email, pswdHash, empStatus, avatar);
//			employees.add(employee);
//		}
//		for (Employee b : employees) {
//			try {
//				employeeDAO.insert(b);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		LocalDateTime currentDateTime = LocalDateTime.now();
//        Timestamp[] arr1 = new Timestamp[5];
//        for (int i = 0; i < arr1.length; i++) {
//            Random random = new Random();
//            arr1[i] = Timestamp.valueOf(currentDateTime.minusSeconds(Math.abs(random.nextInt() % 10000000)));
//        }
//		BalanceHistory b1 = new BalanceHistory(null, 5, null, null, 10, arr1[0], -5000);
//        BalanceHistory b2 = new BalanceHistory(null, 6, 105, null, null, arr1[1], 1200);
//        BalanceHistory b3 = new BalanceHistory(null, 5, null, 210, null, arr1[2], -520);
//        BalanceHistory b4 = new BalanceHistory(null, 5, null, null, 20, arr1[3], 800);
//        BalanceHistory b5 = new BalanceHistory(null, 6, 106, null, null, arr1[4], -100);
//        BalanceHistory[] arr2 = {b1, b2, b3, b4, b5};
//        for (BalanceHistory b : arr2) {
//            try {
//				balanceHistoryDAO.insert(b);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//		CategoryTags c0 = new CategoryTags(null, null, "所有種類", 101);
//        CategoryTags c1 = new CategoryTags(null, 1, "上衣", 101);
//        CategoryTags c2 = new CategoryTags(null, 1, "褲子", 205);
//        CategoryTags c3 = new CategoryTags(null, 1, "飾品", 205);
//        CategoryTags c4 = new CategoryTags(null, 2, "短袖", 205);
//        CategoryTags c5 = new CategoryTags(null, 2, "長袖", 101);
//        CategoryTags c6 = new CategoryTags(null, 3, "短褲", 101);
//        CategoryTags c7 = new CategoryTags(null, 3, "長褲", 101);
//        CategoryTags c8 = new CategoryTags(null, 5, "男短袖", 101);
//        CategoryTags c9 = new CategoryTags(null, 5, "女短袖", 101);
//        CategoryTags c10 = new CategoryTags(7, 3, "七分褲", 208);
//        CategoryTags c11 = new CategoryTags(2, 15, "亂填FK", 209);
//
//
//        CategoryTags[] arr = {c0, c1, c2, c3, c4, c5, c6, c7, c8, c9};
//        for (CategoryTags c : arr) {
//        	try {
//				categorytagsDAO.insert(c);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
		
//		========================= update =========================
//		Map<String, Object> map2 = new LinkedHashMap<>();
//		map2.put("empId", 13);
//		List<Employee> list = employeeDAO.getBy(map2);
//		Employee emp = new Employee();
//		Map<String, Object> map1 = new LinkedHashMap<>();
//		map1.put("address", "台北市大安區456號");
//		emp.setAvatar(list.get(0).getAvatar());
//		employeeDAO.update(emp, map1);
		
		
//		========================= delete =========================
		
//		Map<String, Object> map1 = new LinkedHashMap<>();
//		map1.put("address", "台北市中正區123號");
//		map1.put("phone", "0912345678");
//		employeeDAO.delete(map1);
		
//		========================= getAll =========================
		List<Employee> list = employeeDAO.getAll();
		for (Employee x : list) {
			System.out.println(x);
		}
		System.out.println("=============================");
//
//		List<BalanceHistory> list1 = balanceHistoryDAO.getAll();
//		for (BalanceHistory x : list1) {
//			System.out.println(x);
//		}
//		System.out.println("=============================");
//
//		List<CategoryTags> list2 = categorytagsDAO.getAll();
//		for (CategoryTags x : list2) {
//			System.out.println(x);
//		}

//		========================= getBy 使用時需傳入 存放 ("變數名稱",搜尋數值) 的Map =========================
//		Map<String, Object> map1 = new LinkedHashMap<>();
//		map1.put("address", "%1%");
//		map1.put("phone", "0923456789");
//		map1.put("deptId", 2);
//		for (Employee x : employeeDAO.getBy(map1)) {
//			System.out.println(x);
//		}
//		
//		Map<String, Object> map2 = new LinkedHashMap<>();
//		map2.put("changeDate", "2023-10-09 00:00:00.0");
//		map2.put("orderId", 106);
//		map2.put("changeValue", 10);
//		for (BalanceHistory x : balanceHistoryDAO.getBy(map2)) {
//			System.out.println(x);
//		}
//		
//		Map<String, Object> map3 = new LinkedHashMap<>();
//		map3.put("superTagId", 5);
//		for (CategoryTags x : categorytagsDAO.getBy(map3)) {
//			System.out.println(x);
//		}
//
	}
//	
//	public static byte[] getPictureByteArray(String path) throws IOException {
//        FileInputStream fis = new FileInputStream(path);
//        byte[] pictureArr = fis.readAllBytes();
//        fis.close();
//        return pictureArr;
//    }
	
}
