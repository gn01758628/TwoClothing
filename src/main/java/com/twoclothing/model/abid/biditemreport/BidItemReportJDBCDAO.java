package com.twoclothing.model.abid.biditemreport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.sql.Types;

import com.twoclothing.utils.JDBCUtil;

public class BidItemReportJDBCDAO implements BidItemReportDAO {

	public static final String INSERT = "INSERT INTO biditemreport (bidItemId, mbrId, empId, reportDate, bidDescription, bidStatus, auditDate, result, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String GET_BY_PK = "SELECT * FROM biditemreport WHERE reportId = ? ";
	public static final String GET_ALL = "SELECT * FROM biditemreport ORDER BY reportId";
	public static final String GET_ALL_BY_EMPID = "SELECT * FROM biditemreport WHERE empId = ? ORDER BY reportDate";
	public static final String GET_ALL_BY_BIDITEMID = "SELECT * FROM biditemreport WHERE bidItemId = ? ORDER BY reportDate";
	public static final String GET_ALL_BY_BIDSTATUS = "SELECT * FROM biditemreport WHERE bidStatus = ? ORDER BY reportDate";
	public static final String GET_ALL_BY_RESULT = "SELECT * FROM biditemreport WHERE result = ? ORDER BY reportDate";
	// 員工編號,審核狀態,審核結果,備註
	public static final String UPDATE = "UPDATE bidItemReport set empId = ?, bidStatus = ?, result = ?, note = ?, auditDate = ? WHERE reportId = ?";

	@Override
	public void insert(BidItemReport bidItemReport) {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(INSERT);
			ps.setInt(1, bidItemReport.getBidItemId());
			ps.setInt(2, bidItemReport.getMbrId());
			ps.setObject(3, bidItemReport.getEmpId(), Types.INTEGER);
			ps.setTimestamp(4, bidItemReport.getReportDate());
			ps.setString(5, bidItemReport.getBidDescription());
			ps.setInt(6, bidItemReport.getBidStatus());
			ps.setObject(7, bidItemReport.getAuditDate(), Types.TIMESTAMP);
			ps.setObject(8, bidItemReport.getResult(), Types.INTEGER);
			ps.setObject(9, bidItemReport.getNote(), Types.VARCHAR);
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
	public BidItemReport getByPrimaryKey(Integer reportId) {
		BidItemReport bidItemReport = new BidItemReport();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(GET_BY_PK);
			ps.setInt(1, reportId);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				bidItemReport = setBidItemReport(rs);
			}else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, ps, rs);
		}

		return bidItemReport;
	}


	@Override
	public List<BidItemReport> getAll() {				
		return getAllBy(GET_ALL);
	}

	@Override
	public List<BidItemReport> getAllByEmpId(Integer empId) {
		return getAllBy(GET_ALL_BY_EMPID,empId);
	}

	@Override
	public List<BidItemReport> getAllByBidItemId(Integer bidItemId) {
		return getAllBy(GET_ALL_BY_BIDITEMID,bidItemId);
	}

	@Override
	public List<BidItemReport> getAllByBidStatus(Integer bidStatus) {
		return getAllBy(GET_ALL_BY_BIDSTATUS,bidStatus);
	}

	@Override
	public List<BidItemReport> getAllByResult(Integer result) {
		return getAllBy(GET_ALL_BY_RESULT,result);
	}

	@Override
	public void update(BidItemReport bidItemReport) {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0 ;
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        //修改的這裡應該是不會有NULL 但我還是一起改了
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(UPDATE);
			ps.setObject(1, bidItemReport.getEmpId(), Types.INTEGER);
			ps.setInt(2, bidItemReport.getBidStatus());
			ps.setObject(3, bidItemReport.getResult(), Types.INTEGER);
			ps.setObject(4, bidItemReport.getNote(), Types.VARCHAR);
			ps.setTimestamp(5, currentTimestamp);
			ps.setInt(6, bidItemReport.getReportId());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
	private BidItemReport setBidItemReport(ResultSet rs) {
		BidItemReport bidItemReport = new BidItemReport();
		
		try {
			bidItemReport.setReportId(rs.getInt("reportId"));
			bidItemReport.setBidItemId(rs.getInt("bidItemId"));
			bidItemReport.setMbrId(rs.getInt("mbrId"));
			bidItemReport.setEmpId(rs.getObject("empId", Integer.class));
			bidItemReport.setReportDate(rs.getTimestamp("reportDate"));
			bidItemReport.setBidDescription(rs.getString("bidDescription"));
			bidItemReport.setBidStatus(rs.getInt("bidStatus"));
			bidItemReport.setAuditDate(rs.getObject("auditDate", Timestamp.class));
			bidItemReport.setResult(rs.getObject("result", Integer.class));
			bidItemReport.setNote(rs.getObject("note", String.class));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bidItemReport;
	}
	
	private List<BidItemReport> getAllBy(String by, Integer... byid ) {
		if (byid.length > 1) return null;
        List<BidItemReport> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(by);
			if (byid.length ==1) ps.setInt(1, byid[0]);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(setBidItemReport(rs));
			}
			
        } catch (SQLException e) {
			e.printStackTrace();
		}finally {
            JDBCUtil.close(conn, ps, rs);
        }
        
        if(list.isEmpty()) list.add(null);
        
        
		return list;
	}
}
