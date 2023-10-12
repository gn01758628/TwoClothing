package com.twoclothing.web.abid.biditemimage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


public class BidItemImageTest {
	
	  // 圖片讀取
    public static byte[] getPictureByteArray(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] pictureArr = fis.readAllBytes();
        fis.close();
        return pictureArr;
    }
	
	
	public static void main(String[] args) {
		byte[] test01 = null;
		try {
			test01 = getPictureByteArray("images\\Test1.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BidItemImage b1 = new BidItemImage(11,10,null);
		BidItemImage b2 = new BidItemImage(12,11,test01);
	        
	        //BidItemImageTest Test
	     BidItemImageDAO bidItemImageDAO =new BidItemImageJDBCDAO();
	        
	        //insert Test
	     bidItemImageDAO.insert(b1);
	     bidItemImageDAO.insert(b2);
	        System.out.println("========================================================");
	        
	        // 查詢測試
	        // 單筆查詢
	        BidItemImage im1 =bidItemImageDAO.getByPrimaryKey(1);
	        System.out.println(im1);
	        
	       
	   System.out.println("========================================================");
	        
	// 全部查詢
       List<BidItemImage> list = bidItemImageDAO.getAll();
       for (BidItemImage f : list) {
           System.out.println(f);
       }

            
       System.out.println("========================================================");
       
       // 根據競標商品ID查詢
       
       List<BidItemImage> list2 = bidItemImageDAO.getAllByBidItemId(5);
       for (BidItemImage f : list2) {
           System.out.println(f);
       }
       
       
	}
}
