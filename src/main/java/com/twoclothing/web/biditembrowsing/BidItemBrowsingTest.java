package com.twoclothing.web.biditembrowsing;

import java.sql.Timestamp;
import java.util.List;

public class BidItemBrowsingTest {
	
	//產生隨機Timestamp的方法
	private static Timestamp getRandomTimestamp() {
		// TODO Auto-generated method stub
		long offset = Timestamp.valueOf("2023-01-01 00:00:00").getTime();
	    long end = Timestamp.valueOf("2023-12-31 23:59:59").getTime();
	    long diff = end - offset + 1;
	    long randomTime = offset + (long)(Math.random() * diff);
	    return new Timestamp(randomTime);
	}
	
	public static void main(String[] args) {
		Timestamp timestamp1 = getRandomTimestamp();
        Timestamp timestamp2 = getRandomTimestamp();
        BidItemBrowsing b1 = new BidItemBrowsing(1011,10,timestamp1);
        BidItemBrowsing b2 = new BidItemBrowsing(1012,11,timestamp2);
        
        //BidItemBrowsing Test
        BidItemBrowsingDAO bidItemBrowsingDAO =new BidItemBrowsingJDBCDAO();
        
        //insert Test
        bidItemBrowsingDAO.insert(b1);
        bidItemBrowsingDAO.insert(b2);
        System.out.println("========================================================");  
       
        // 查詢測試
        // 單筆查詢
        BidItemBrowsing browsing = bidItemBrowsingDAO.getByCompositeKey(1,1);
        BidItemBrowsing browsing2 = bidItemBrowsingDAO.getByCompositeKey(105,7);
        System.out.println(browsing);
        System.out.println(browsing2);
       
        System.out.println("========================================================");
        
     // 全部查詢
        List<BidItemBrowsing> list = bidItemBrowsingDAO.getAll();
        for (BidItemBrowsing f : list) {
            System.out.println(f);
        }

        System.out.println("========================================================");
        
        // 根據競標商品ID查詢
        
        List<BidItemBrowsing> list2 = bidItemBrowsingDAO.getAllByBidItemId(5);
        for (BidItemBrowsing f : list2) {
            System.out.println(f);
        }
        
        System.out.println("========================================================");
        
        // 根據會員ID查詢
        
        List<BidItemBrowsing> list3 = bidItemBrowsingDAO.getAllByMbrId(105);
        for (BidItemBrowsing f : list3) {
            System.out.println(f);
        }
        System.out.println("========================================================");
        //更新瀏覽時間
        bidItemBrowsingDAO.update(timestamp2, 105, 7);
        
	}

	
}
