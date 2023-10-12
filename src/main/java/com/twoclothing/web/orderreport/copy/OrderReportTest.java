package com.twoclothing.web.orderreport.copy;

import java.sql.Timestamp;
import java.util.List;

public class OrderReportTest {
	public static void main(String[] args) {
		OrderReport or1 = new OrderReport(1, 1, null, Timestamp.valueOf("2023-09-15 12:00:00"), "商品有問題", 0, null, null,
				null);
		OrderReport or2 = new OrderReport(2, 2, 1, Timestamp.valueOf("2023-09-28 09:45:00"), "賣家行為不當", 1,
				Timestamp.valueOf("2023-09-28 10:00:00"), 0, "無");
		OrderReport or3 = new OrderReport(3, 3, 2, Timestamp.valueOf("2023-09-25 16:20:00"), "收到爛商品", 1,
				Timestamp.valueOf("2023-09-25 16:35:00"), 0, "無");
		OrderReport or4 = new OrderReport(4, 4, 3, Timestamp.valueOf("2023-09-15 10:15:00"), "虛假廣告", 1,
				Timestamp.valueOf("2023-09-20 10:30:00"), 1, "不符合事實");
		OrderReport or5 = new OrderReport(4, 5, 4, Timestamp.valueOf("2023-09-17 12:00:00"), "詐騙行為", 1,
				Timestamp.valueOf("2023-09-17 12:15:00"), 1, "詐騙");

		OrderReportDAO orderReportDAO = new OrderReportJDBCDAO();

		// insert測試
		OrderReport[] arr = { or1, or2, or3, or4 };
		for (OrderReport o : arr) {
			orderReportDAO.insert(o);
		}

		System.out.println("=====================================================================================================================================");

		// getByPrimaryKey測試
		OrderReport byPK = orderReportDAO.getByPrimaryKey(4);
		System.out.println(byPK);

		System.out.println("=====================================================================================================================================");

		// getAll測試
		List<OrderReport> list = orderReportDAO.getAll();
		for (OrderReport o : list) {
			System.out.println(o);
		}

		System.out.println("=====================================================================================================================================");

		// getAllByOrderId測試
		List<OrderReport> list1 = orderReportDAO.getAllByOrderId(1);
		for (OrderReport o : list1) {
			System.out.println(o);
		}

		System.out.println("=====================================================================================================================================");

		// getAllByEmpId測試
		List<OrderReport> list2 = orderReportDAO.getAllByEmpId(3);
		for (OrderReport o : list2) {
			System.out.println(o);
		}

		System.out.println("=====================================================================================================================================");

		// getAllByRStatus測試
		List<OrderReport> list3 = orderReportDAO.getAllByRStatus(0);
		for (OrderReport o : list3) {
			System.out.println(o);
		}

		System.out.println("=====================================================================================================================================");

		// getAllByResult測試
		List<OrderReport> list4 = orderReportDAO.getAllByResult(1);
		for (OrderReport o : list4) {
			System.out.println(o);
		}

		System.out.println("=====================================================================================================================================");

		// update測試
		orderReportDAO.update(or5);
	}
}
