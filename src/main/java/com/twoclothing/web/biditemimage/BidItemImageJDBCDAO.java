package com.twoclothing.web.biditemimage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.twoclothing.utils.JDBCUtils;


public class BidItemImageJDBCDAO implements BidItemImageDAO{

	private static final String INSERT = "INSERT INTO BidItemImage (imageId, bidItemId, image) VALUES (?, ?, ?)";

	private static final String GET_BY_PK ="SELECT * FROM biditemimage WHERE imageid=?";
	
	private static final String GET_ALL ="SELECT * FROM biditemimage";
	
	private static final String GET_BY_BIDITEMID ="SELECT * FROM biditemimage WHERE bidItemId=?";
	
	@Override
	public void insert(BidItemImage bidItemImage) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			conn=JDBCUtils.getConnection();
			ps = conn.prepareStatement(INSERT);
            ps.setInt(1, bidItemImage.getImageId());
            ps.setInt(2, bidItemImage.getBidItemId());
            ps.setBytes(3, bidItemImage.getImage());

            count = ps.executeUpdate();

            if (count > 0) {
                System.out.println("成功插入");
            } else {
                System.out.println("插入失敗。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(conn, ps, null);
          }
        }
    
	

	@Override
	public BidItemImage getByPrimaryKey(Integer imageId) {
		// TODO Auto-generated method stub
		BidItemImage bidItemImage = new BidItemImage();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		
		try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_BY_PK);
            ps.setInt(1, imageId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	bidItemImage = setBidItemImage(rs);
             } else {
                return null;
                }
        	} catch (SQLException e) {
            e.printStackTrace();
        	} finally {
            JDBCUtils.close(conn, ps, rs);
        	}
        	return bidItemImage;
		}

	

	@Override
	public List<BidItemImage> getAll() {
		// TODO Auto-generated method stub
		return getAllBy(GET_ALL);
	}

	@Override
	public List<BidItemImage> getAllByBidItemId(Integer bidItemId) {
		// TODO Auto-generated method stub
		return getAllBy(GET_BY_BIDITEMID,bidItemId);
	}
	
	
	
	
	 private BidItemImage setBidItemImage(ResultSet rs) {

		 BidItemImage bidItemImage = new BidItemImage();

	        try {
	        	bidItemImage.setImageId(rs.getInt("imageId"));
	        	bidItemImage.setBidItemId(rs.getInt("bidItemId"));
	        	bidItemImage.setImage(rs.getBytes("image"));
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return bidItemImage;
	    }

	    private List<BidItemImage> getAllBy(String by, Object... byid) {
	        if (byid.length > 1) return null;
	        List<BidItemImage> list = new ArrayList<>();
	        Connection conn = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            conn = JDBCUtils.getConnection();
	            ps = conn.prepareStatement(by);
	            if (byid.length == 1) ps.setObject(1, byid[0]);
	            rs = ps.executeQuery();

	            while (rs.next()) {
	                list.add(setBidItemImage(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtils.close(conn, ps, rs);
	        }

	        if (list.isEmpty()) list.add(null);
	        return list;
	    }
	
	
	
	
	
	
}
