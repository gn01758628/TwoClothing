//package com.twoclothing.model.abid.bidorder;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//public class BidOrderTest {
//	public static void main(String[] args) {
//		BidOrder b1 = new BidOrder(null, 301, 401, 5, null, null, null, null, Timestamp.valueOf("2023-09-28 10:00:00"), null, null, 1200, 3, null, null, null, null);
//		BidOrder b2 = new BidOrder(null, 302, 402, 4, 2, "优质交易", 5, "快速交货", Timestamp.valueOf("2023-09-29 11:30:00"), 1, "支付宝", 800, 2, "台中市456號", "買家2", "0987654321", "特殊要求2");
//		BidOrder b3 = new BidOrder(null, 303, 403, 3, 3, "满意的交易", 4, "正常交货", Timestamp.valueOf("2023-09-30 14:45:00"), 2, "微信支付", 1500, 1, "高雄市789號", "買家3", "0911122333", "特殊要求3");
//		BidOrder b4 = new BidOrder(null, 304, 404, 4, 4, "出色的交易", 5, "快速交货", Timestamp.valueOf("2023-10-01 15:20:00"), 1, "支付宝", 2000, 3, "新北市101號", "買家4", "0912345678", "特殊要求4");
//		BidOrder b5 = new BidOrder(null, 305, 405, 5, 2, "满意的交易", 4, "正常交货", Timestamp.valueOf("2023-10-02 16:10:00"), 2, "微信支付", 1800, 2, "台中市789號", "買家5", "0987654321", "特殊要求5");
//		BidOrder b6 = new BidOrder(1, 305, 405, 5, 5, "满意的交易LALALA", 5, "LALALA正常交货", Timestamp.valueOf("2023-10-02 16:10:00"), 2, "微信支付", 1800, 4, "台中市789號", "買家5", "0987654321", "特殊要求5");
//
//		BidOrderDAO bidOrderDAO = new BidOrderJDBCDAO();
//		
//		BidOrder[] arr2 = {b1, b2, b3, b4, b5};
//		for(BidOrder b : arr2) {
//			bidOrderDAO.insert(b);
//		}
//		System.out.println("pk會員id查詢=========================================================================================");
//
//		System.out.println(bidOrderDAO.getByPrimaryKey(2));
//		
//		System.out.println("全部查詢查詢=========================================================================================");
//
//		List<BidOrder> list1 = bidOrderDAO.getAll();
//		for (BidOrder s : list1) {
//			System.out.println(s);
//		}
//		
//		System.out.println("buyMbrId查詢=========================================================================================");
//		
//		List<BidOrder> list2 = bidOrderDAO.getAllByBuyMbrId(402);
//		for (BidOrder s : list2) {
//			System.out.println(s);
//		}
//		
//		System.out.println("sellMbrId查詢=========================================================================================");
//	
//		List<BidOrder> list3 = bidOrderDAO.getAllBySellMbrId(4);
//		for (BidOrder s : list3) {
//			System.out.println(s);
//		}
//		
//		System.out.println("ORDERSTATUS查詢=========================================================================================");
//	
//		List<BidOrder> list4 = bidOrderDAO.getAllByOrderStatus(3);
//		for (BidOrder s : list4) {
//			System.out.println(s);
//		}
//		
//		System.out.println("修改測試=====================================================================================================================================");
//
//		bidOrderDAO.update(b6);
//	}
//}
