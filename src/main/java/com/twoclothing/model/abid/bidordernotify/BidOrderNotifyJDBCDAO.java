package com.twoclothing.model.abid.bidordernotify;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.twoclothing.utils.JDBCUtil;

public class BidOrderNotifyJDBCDAO implements BidOrderNotifyDAO{
	
	
	public static final String INSERT = "INSERT INTO bidordernotify (mbrId, bidOrderId, notifyDate, title, content) VALUES (?, ?, ?, ?, ?)";
	public static final String GET_BY_PK = "SELECT * FROM bidordernotify WHERE notifyid = ? ";
	public static final String GET_ALL = "SELECT * FROM bidordernotify ORDER BY notifyid";
	public static final String GET_ALL_BY_MBRID = "SELECT * FROM bidordernotify WHERE mbrId = ? ORDER BY notifyDate";
	public static final String GET_ALL_BY_BIDORDERID = "SELECT * FROM bidordernotify WHERE bidorderid = ? ORDER BY notifyDate";
	//public static final String UPDATE = "UPDATE bidItemReport set  WHERE notifyid = ?";

	
	@Override
	public void insert(BidOrderNotify bidOrderNotif) {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(INSERT);
			ps.setInt(1, bidOrderNotif.getMbrId());
			ps.setInt(2, bidOrderNotif.getBidOrderId());
			ps.setTimestamp(3, bidOrderNotif.getNotifyDate());
			ps.setString(4, bidOrderNotif.getTitle());
			ps.setString(5, bidOrderNotif.getContent());
			count = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps, null);
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
	public BidOrderNotify getByPrimaryKey(Integer notifyId) {
		BidOrderNotify bidOrderNotify = new BidOrderNotify();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(GET_BY_PK);
			ps.setInt(1, notifyId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bidOrderNotify = setBidOrderNotify(rs);
			}else {
				return null;
			}
		 }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps, rs);
		}

		return bidOrderNotify;
	

	}
	@Override
	public List<BidOrderNotify> getAll() {
		return getAllBy(GET_ALL);
	}


	@Override
	public List<BidOrderNotify> getAllByMbrId(Integer mbrId) {
		return getAllBy(GET_ALL_BY_MBRID,mbrId);

	}

	@Override
	public List<BidOrderNotify> getAllByBidOrderId(Integer bidOrderId) {
		return getAllBy(GET_ALL_BY_BIDORDERID,bidOrderId);

	}

	
//	public void update(BidOrderNotify bidOrderNotify) {
//		// TODO Auto-generated method stub
//		
//	}

	private BidOrderNotify setBidOrderNotify(ResultSet rs) {
		BidOrderNotify bidOrderNotify =new BidOrderNotify();
		
		try {
			bidOrderNotify.setNotifyId(rs.getInt("notifyId"));
			bidOrderNotify.setMbrId(rs.getInt("mbrId"));
			bidOrderNotify.setBidOrderId(rs.getInt("bidOrderId"));
			bidOrderNotify.setNotifyDate(rs.getTimestamp("notifyDate"));
			bidOrderNotify.setTitle(rs.getString("title"));
			bidOrderNotify.setContent(rs.getString("content"));
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bidOrderNotify;
	}
	private List<BidOrderNotify> getAllBy(String by , Integer... byid) {
		if (byid.length > 1) return null;
        List<BidOrderNotify> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(by);
			if (byid.length ==1) ps.setInt(1, byid[0]);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(setBidOrderNotify(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            JDBCUtil.close(conn, ps, rs);
        }
        
        if(list.isEmpty()) list.add(null);
        
        
		return list;
	}
}
