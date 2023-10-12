package com.twoclothing.web.aproduct.cartdetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.twoclothing.utils.JDBCUtil;


public class CartDetailJDBCDAO implements CartDetailDAO {
	
	 private static final String INSERT = "INSERT INTO cartdetail (cartid, mbrid, itemid, quantity) VALUES (?,?,?,?)";

	 private static final String GET_BY_PK = "SELECT * FROM cartdetail WHERE cartid = ?";

	 private static final String GET_ALL = "SELECT * FROM cartdetail";

	 private static final String GET_ALL_BY_MBRID = "SELECT * FROM cartdetail WHERE mbrid = ? ORDER BY cartid";

	 private static final String UPDATE = "UPDATE cartdetail SET mbrid = ?, itemid = ?, quantity = ? WHERE cartid = ?";

	 private static final String DELETE = "DELETE FROM cartdetail WHERE cartid = ?";

	@Override
	public void insert(CartDetail cartdetail) {
		Connection conn = null;
	    PreparedStatement ps = null;
	    int count = 0;
		
	    try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(INSERT);
			ps.setInt(1, cartdetail.getCartId());
	        ps.setInt(2, cartdetail.getMbrId());
	        ps.setInt(3, cartdetail.getItemId());
	        ps.setInt(4, cartdetail.getQuantity());
	        count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	public CartDetail getByPrimaryKey(Integer cartId) {
		CartDetail cartDetail = new CartDetail();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(GET_BY_PK);
            ps.setInt(1, cartId);
            rs = ps.executeQuery();

            if (rs.next()) {
                cartDetail = setCartDetail(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return cartDetail;
	}

	private CartDetail setCartDetail(ResultSet rs) {
		CartDetail cartDetail = new CartDetail();
        try {
        	cartDetail.setCartId(rs.getInt("cartId"));
        	cartDetail.setMbrId(rs.getInt("mbrId"));
        	cartDetail.setItemId(rs.getInt("itemId"));
        	cartDetail.setQuantity(rs.getInt("quantity"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartDetail;
    }

	@Override
	public List<CartDetail> getAll() {
		List<CartDetail> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setCartDetail(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
	}

	@Override
	public List<CartDetail> getAllByMbrId(Integer mbrId) {
		List<CartDetail> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_BY_MBRID);
            ps.setInt(1, mbrId);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(setCartDetail(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        if (list.isEmpty()) list.add(null);
        return list;
	}

	@Override
	public void update(CartDetail cartDetail) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1, cartDetail.getCartId());
            ps.setInt(2, cartDetail.getMbrId());
            ps.setInt(3, cartDetail.getItemId());
            ps.setInt(4, cartDetail.getQuantity());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫修改成功的執行代碼
            System.out.println("修改成功");
        } else {
            // 編寫修改失敗的執行代碼
            System.out.println("修改失敗");
        }
	}

	@Override
	public void delete(Integer cartId) {
		Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, cartId);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, null);
        }

        if (count == 1) {
            // 編寫刪除成功的執行代碼
            System.out.println("刪除成功");
        } else {
            // 編寫刪除失敗的執行代碼
            System.out.println("刪除失敗");
        }
	}	 
}
