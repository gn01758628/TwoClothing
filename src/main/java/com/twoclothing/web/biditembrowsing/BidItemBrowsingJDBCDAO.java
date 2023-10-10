package com.twoclothing.web.biditembrowsing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.twoclothing.utils.JDBCUtils;
import com.twoclothing.web.biditemreport.BidItemReport;


public class BidItemBrowsingJDBCDAO implements BidItemBrowsingDAO{

	private static final String INSERT = "INSERT INTO biditembrowsing (mbrid,biditemid,browsingtime) VALUES(?,?,?)";
	
	private static final String GET_BY_CPK ="SELECT * FROM biditembrowsing WHERE mbrid=? AND biditemid =?";
	
	private static final String GET_ALL ="SELECT * FROM biditembrowsing";
	
	private static final String GET_BY_MBRID ="SELECT * FROM biditembrowsing WHERE mbrid=?";
	
	private static final String GET_BY_BIDITEMID ="SELECT * FROM biditembrowsing WHERE biditemid=?";
	
	public static final String UPDATE = "UPDATE biditembrowsing SET browsingtime = ? WHERE mbrid=? AND biditemid=?";
	
	@Override
	public void insert(BidItemBrowsing bidItemBrowsing) {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			conn = JDBCUtils.getConnection();
			ps =conn.prepareStatement(INSERT);
			ps.setInt(1,bidItemBrowsing.getMbrId());
			ps.setInt(2,bidItemBrowsing.getBidItemId());
			ps.setTimestamp(3,bidItemBrowsing.getBrowsingTime());
			count=ps.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, ps, null);
		}
		   if (count == 1) {
	            // 編寫新增成功的執行代碼
	            System.out.println("新增成功");
	        } else {
	            // 編寫新增失敗的執行代碼
	            System.out.println("新增失敗");
	        }
		
		
		
	}

	@Override
	public BidItemBrowsing getByCompositeKey(Integer mbrId, Integer bidItemId) {
		// TODO Auto-generated method stub
		BidItemBrowsing bidItemBrowsing = new BidItemBrowsing();
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		
		try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(GET_BY_CPK);
            ps.setInt(1,mbrId);
            ps.setInt(2,bidItemId);
            rs = ps.executeQuery();
            if (rs.next()) {
            	bidItemBrowsing = setBidItemBrowsing(rs);
             } else {
                System.out.println("未找到資料。mbrId=" + mbrId + ", bidItemId=" + bidItemId);
                }
        	} catch (SQLException e) {
            e.printStackTrace();
        	} finally {
            JDBCUtils.close(conn, ps, rs);
        	}
        	return bidItemBrowsing;
		}

	

	

	@Override
	public void update(Timestamp browsingTime, Integer mbrId, Integer bidItemId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0 ;
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(UPDATE);
			ps.setTimestamp(1, currentTimestamp);
			ps.setInt(2, mbrId);
			ps.setInt(3, bidItemId);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            JDBCUtils.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
	}

	private BidItemBrowsing setBidItemBrowsing(ResultSet rs) {
		// TODO Auto-generated method stub
		BidItemBrowsing bidItemBrowsing = new BidItemBrowsing();
		
		try {
			bidItemBrowsing.setMbrId(rs.getInt("mbrId"));
			bidItemBrowsing.setBidItemId(rs.getInt("bidItemId"));
			bidItemBrowsing.setBrowsingTime(rs.getTimestamp("browsingTime"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bidItemBrowsing;
	
	}

	@Override
	public List<BidItemBrowsing> getAll() {
		return getAllBy(GET_ALL);
	  }
	
	

	@Override
	public List<BidItemBrowsing> getAllByBidItemId(Integer bidItemId) {
		// TODO Auto-generated method stub
		return getAllBy(GET_BY_BIDITEMID,bidItemId);
	}

	@Override
	public List<BidItemBrowsing> getAllByMbrId(Integer mbrId) {
		// TODO Auto-generated method stub
		return getAllBy(GET_BY_MBRID,mbrId);
	}
	
	private List<BidItemBrowsing> getAllBy(String by, Integer... byid) {
        if (byid.length > 1) return null;
        List<BidItemBrowsing> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(by);
            if (byid.length == 1) ps.setInt(1, byid[0]);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setBidItemBrowsing(rs));
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
