//package com.twoclothing.model.abid.bidordernotify;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//public class BidOrderNotifyTest {
//	public static void main(String[] args) {
//        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
//
//		BidOrderNotify b1 = new BidOrderNotify(null,101, 1, currentTimestamp, "通知標題1", "通知内容1");
//		BidOrderNotify b2 = new BidOrderNotify(null, 101, 1, Timestamp.valueOf("2023-10-01 09:00:00"), "通知標題1", "通知内容1");
//		BidOrderNotify b3 = new BidOrderNotify(null, 102, 2, Timestamp.valueOf("2023-10-02 10:30:00"), "通知標題2", "通知内容2");
//		BidOrderNotify b4 = new BidOrderNotify(null, 103, 3, Timestamp.valueOf("2023-10-03 11:45:00"), "通知標題3", "通知内容3");
//		BidOrderNotify b5 = new BidOrderNotify(null, 104, 4, Timestamp.valueOf("2023-10-04 13:15:00"), "通知標題4", "通知内容4");
//		BidOrderNotify b6 = new BidOrderNotify(null, 105, 5, Timestamp.valueOf("2023-10-05 15:20:00"), "通知標題5", "通知内容5");
//		BidOrderNotify b7 = new BidOrderNotify(null, 106, 6, Timestamp.valueOf("2023-10-06 16:45:00"), "通知標題6", "通知内容6");
//
//		BidOrderNotifyDAO bidOrderNotifyDAO = new BidOrderNotifyJDBCDAO();
//		
//		BidOrderNotify[] arr2 = {b1, b2, b3, b4, b5};
//		for(BidOrderNotify s : arr2) {
//			bidOrderNotifyDAO.insert(s);
//			}
//		
//		System.out.println("pk會員id查詢=========================================================================================");
//		
//		System.out.println(bidOrderNotifyDAO.getByPrimaryKey(1));
//	
//		System.out.println("全部查詢=========================================================================================");
//		List<BidOrderNotify> list1 = bidOrderNotifyDAO.getAll();
//		for(BidOrderNotify s : list1) {
//			System.out.println(s);
//		}
//				
//		System.out.println("會員id查詢=========================================================================================");
//		List<BidOrderNotify> list2 = bidOrderNotifyDAO.getAllByMbrId(102);
//		for(BidOrderNotify s : list2) {
//			System.out.println(s);
//		}
//		System.out.println("訂單編號查詢=========================================================================================");
//		List<BidOrderNotify> list3 = bidOrderNotifyDAO.getAllByBidOrderId(2);
//		for(BidOrderNotify s : list3) {
//			System.out.println(s);
//		}
//	}
//}
