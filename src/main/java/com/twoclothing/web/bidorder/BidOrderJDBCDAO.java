package com.twoclothing.web.bidorder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.twoclothing.utils.JDBCUtils;

public class BidOrderJDBCDAO implements BidOrderDAO {
	public static final String INSERT = "INSERT INTO bidOrder(bidItemId, buyMbrId, sellMbrId, buyStar, buyerRatingDesc, sellStar, sellerRatingDesc, orderDate, payType, payInfo, amount, orderStatus, receiveAddress, receiveName, receivePhone, remarks) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_BY_PK = "SELECT * FROM bidOrder WHERE bidOrderId = ? ";
	public static final String GET_ALL = "SELECT * FROM bidOrder ORDER BY bidOrderId";
	public static final String GET_ALL_BUYMBRID = "SELECT * FROM bidOrder WHERE buyMbrId = ? ORDER BY orderDate";
	public static final String GET_ALL_SELLMBRID = "SELECT * FROM bidOrder WHERE sellMbrId = ? ORDER BY orderDate";
	public static final String GET_ALL_ORDERSTATUS = "SELECT * FROM bidOrder WHERE orderStatus = ? ORDER BY orderDate";
	public static final String UPDATE = "UPDATE bidOrder set buyStar = ?, buyerRatingDesc = ?, sellStar = ?, sellerRatingDesc = ?, orderStatus = ? WHERE bidOrderId = ?";

	public void insert(BidOrder bidorder) {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;

		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(INSERT);
			ps.setInt(1, bidorder.getBidItemId());
			ps.setInt(2, bidorder.getBuyMbrId());
			ps.setInt(3, bidorder.getSellMbrId());
			ps.setObject(4, bidorder.getBuyStar(), Types.INTEGER);
			ps.setObject(5, bidorder.getBuyerRatingDesc(), Types.VARCHAR);
			ps.setObject(6, bidorder.getSellStar(), Types.INTEGER);
			ps.setObject(7, bidorder.getSellerRatingDesc(), Types.VARCHAR);
			ps.setTimestamp(8, bidorder.getOrderDate());
			ps.setObject(9, bidorder.getPayType(), Types.INTEGER);
			ps.setObject(10, bidorder.getPayInfo(), Types.VARCHAR);
			ps.setInt(11, bidorder.getAmount());
			ps.setInt(12, bidorder.getOrderStatus());
			ps.setObject(13, bidorder.getReceiveAddress(), Types.VARCHAR);
			ps.setObject(14, bidorder.getReceiveName(), Types.VARCHAR);
			ps.setObject(15, bidorder.getReceivePhone(), Types.VARCHAR);
			ps.setObject(16, bidorder.getRemarks(), Types.VARCHAR);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	public BidOrder getByPrimaryKey(Integer bidOrderId) {
		BidOrder bidOrder = new BidOrder();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(GET_BY_PK);
			ps.setInt(1, bidOrderId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				bidOrder = setBidOrder(rs);
			}else {
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(conn, ps, rs);
		}

		return bidOrder;
	}

	@Override
	public List<BidOrder> getAll() {
		return getAllBy(GET_ALL);
	}


	public List<BidOrder> getAllByBuyMbrId(Integer buyMbrId) {
		return getAllBy(GET_ALL_BUYMBRID, buyMbrId);
	}

	public List<BidOrder> getAllBySellMbrId(Integer sellMbrId) {
		return getAllBy(GET_ALL_SELLMBRID, sellMbrId);
		
	}

	@Override
	public List<BidOrder> getAllByOrderStatus(Integer orderStatus) {
		return getAllBy(GET_ALL_ORDERSTATUS, orderStatus);
	}

	@Override
	public void update(BidOrder bidorder) {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0 ;
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(UPDATE);
			ps.setObject(1, bidorder.getBuyStar(), Types.INTEGER);
			ps.setObject(2, bidorder.getBuyerRatingDesc(), Types.VARCHAR);
			ps.setObject(3, bidorder.getSellStar(), Types.INTEGER);
			ps.setObject(4, bidorder.getSellerRatingDesc(), Types.VARCHAR);
			ps.setInt(5, bidorder.getOrderStatus());
			ps.setInt(6, bidorder.getBidOrderId());
			
			
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

	private BidOrder setBidOrder(ResultSet rs) {
		BidOrder bidOrder = new BidOrder();
		
		try {
			bidOrder.setBidOrderId(rs.getInt("bidOrderId"));
			bidOrder.setBidItemId(rs.getInt("biditemid"));
			bidOrder.setBuyMbrId(rs.getInt("buyMbrId"));
			bidOrder.setSellMbrId(rs.getInt("sellMbrId"));
			bidOrder.setBuyStar(rs.getObject("buyStar", Integer.class));
			bidOrder.setBuyerRatingDesc(rs.getObject("buyerRatingDesc", String.class));
			bidOrder.setSellStar(rs.getObject("sellStar", Integer.class));
			bidOrder.setSellerRatingDesc(rs.getObject("sellerRatingDesc", String.class));
			bidOrder.setOrderDate(rs.getTimestamp("orderDate"));
			bidOrder.setPayType(rs.getObject("payType", Integer.class));
			bidOrder.setPayInfo(rs.getObject("payInfo", String.class));
			bidOrder.setAmount(rs.getInt("amount"));
			bidOrder.setOrderStatus(rs.getInt("orderStatus"));
			bidOrder.setReceiveAddress(rs.getObject("receiveAddress", String.class));
			bidOrder.setReceiveName(rs.getObject("receiveName", String.class));
			bidOrder.setReceivePhone(rs.getObject("receivePhone", String.class));
			bidOrder.setRemarks(rs.getObject("remarks", String.class));

				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bidOrder;
	}
	
	private List<BidOrder> getAllBy(String by, Integer... byid) {
		if(byid.length > 1 ) return null;
		List<BidOrder> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(by);
			if(byid.length == 1) ps.setInt(1, byid[0]);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(setBidOrder(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
            JDBCUtils.close(conn, ps, rs);
        }
        
        if(list.isEmpty()) list.add(null);
        

		
		return list;
	}
}
