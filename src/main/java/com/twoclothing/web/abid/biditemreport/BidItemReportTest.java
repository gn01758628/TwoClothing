package com.twoclothing.web.abid.biditemreport;

import java.sql.Timestamp;
import java.util.List;

public class BidItemReportTest {
	public static void main(String[] args) {		
				
		BidItemReport b1 = new BidItemReport(null, 1, 1001, null, Timestamp.valueOf("2023-09-15 12:00:00"), "這個商品有問題", 0,
				null, 0, null);
		BidItemReport b2 = new BidItemReport(null, 2, 1002, 102, Timestamp.valueOf("2023-09-28 09:45:00"), "賣家行為不當", 0,
				Timestamp.valueOf("2023-09-28 10:00:00"), 1, "無");
		BidItemReport b3 = new BidItemReport(null, 3, 1003, 103, Timestamp.valueOf("2023-09-25 16:20:00"), "商品描述不正確", 0,
				Timestamp.valueOf("2023-09-25 16:35:00"), 0, "無");
		BidItemReport b4 = new BidItemReport(null, 4, 1004, 104, Timestamp.valueOf("2023-09-20 10:15:00"), "虛假廣告", 0,
				Timestamp.valueOf("2023-09-20 10:30:00"), 1, "無");
		BidItemReport b5 = new BidItemReport(null, 5, 1005, 105, Timestamp.valueOf("2023-09-15 12:00:00"), "詐騙行為", 0,
				Timestamp.valueOf("2023-09-15 12:15:00"), 0, "無");
		BidItemReport b6 = new BidItemReport(1, 5, 1005, 20010999, Timestamp.valueOf("2023-09-15 12:00:00"), "詐騙行為", 1,
				Timestamp.valueOf("2023-09-15 12:15:00"), 1, "詐騙");
		
		BidItemReportDAO bidItemReportDAO = new BidItemReportJDBCDAO();
		// 插入測試
		BidItemReport[] arr2 = { b1, b2, b3, b4, b5 };
		for (BidItemReport b : arr2) {
			bidItemReportDAO.insert(b);
		}
		
//		BidItemReportDAO bidItemReportDAO = new BidItemReportJDBCDAO();
//		BidItemReport bidItemReport = new BidItemReport(null, 1, 1001, 101, Timestamp.valueOf("2023-09-15 12:00:00"), "這個商品有問題", 0, Timestamp.valueOf("2023-09-30 14:45:00"), 0, "無");
//		bidItemReportDAO.insert(bidItemReport);
		
		System.out.println("pk會員id查詢=========================================================================================");
		// 查詢測試
        // 單筆查詢
		BidItemReport byPrimaryKey = bidItemReportDAO.getByPrimaryKey(1);
		BidItemReport byPrimaryKey5 = bidItemReportDAO.getByPrimaryKey(2);
		System.out.println(byPrimaryKey);
		System.out.println(byPrimaryKey5);
		System.out.println("全部查詢=========================================================================================");
		// 全部查詢
		List<BidItemReport> list = bidItemReportDAO.getAll();
		for (BidItemReport s : list) {
			System.out.println(s);
			}
		System.out.println("員工id查詢=========================================================================================");
		//以員工id查詢
		List<BidItemReport> list2 = bidItemReportDAO.getAllByEmpId(101);
		for (BidItemReport s : list2) {
			System.out.println(s);
		}
		System.out.println("商品編號查詢=========================================================================================");
		List<BidItemReport> list3 = bidItemReportDAO.getAllByBidItemId(1);
		for(BidItemReport s :list3 ) {
			System.out.println(s);
		}
		System.out.println("審核狀態查詢=========================================================================================");
		List<BidItemReport> list4 = bidItemReportDAO.getAllByBidStatus(1);
		for(BidItemReport s :list4 ) {
			System.out.println(s);
		}
		System.out.println("審核結果查詢=========================================================================================");
		List<BidItemReport> list5 = bidItemReportDAO.getAllByResult(1);
		for(BidItemReport s :list5 ) {
			System.out.println(s);
		}
		System.out.println("修改測試=====================================================================================================================================");
	    // 修改測試
		bidItemReportDAO.update(b6);
	}
}
