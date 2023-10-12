package com.twoclothing.web.orderreport.copy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.twoclothing.utils.JDBCUtil;

public class OrderReportJDBCDAO implements OrderReportDAO {
	public static final String INSERT = "INSERT INTO orderreport (orderid, empid, reportdate, description, rstatus, auditdate, result, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String GET_BY_PK = "SELECT * FROM orderreport WHERE reportid = ?";

	public static final String GET_ALL = "SELECT * FROM orderreport";

	public static final String GET_ALL_BY_ORDERID = "SELECT * FROM orderreport WHERE orderid = ? ORDER BY reportdate";

	public static final String GET_ALL_BY_EMPID = "SELECT * FROM orderreport WHERE empid = ? ORDER BY reportdate";

	public static final String GET_ALL_BY_RSTATUS = "SELECT * FROM orderreport WHERE rstatus = ? ORDER BY reportdate";

	public static final String GET_ALL_BY_RESULT = "SELECT * FROM orderreport WHERE result = ? ORDER BY reportdate";

	public static final String UPDATE = "UPDATE orderreport set empid = ?, rstatus = ?, result = ?, note = ?, auditdate = ? WHERE reportid = ?";

	@Override
	public void insert(OrderReport orderReport) {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(INSERT);
			ps.setInt(1, orderReport.getOrderId());
			ps.setObject(2, orderReport.getEmpId(), Types.INTEGER);
			ps.setTimestamp(3, orderReport.getReportDate());
			ps.setString(4, orderReport.getDescription());
			ps.setInt(5, orderReport.getRStatus());
			ps.setObject(6, orderReport.getAuditDate(), Types.TIMESTAMP);
			ps.setObject(7, orderReport.getResult(), Types.INTEGER);
			ps.setObject(8, orderReport.getNote(), Types.VARCHAR);
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
	public OrderReport getByPrimaryKey(Integer reportId) {
		OrderReport orderReport = new OrderReport();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(GET_BY_PK);
			ps.setInt(1, reportId);
			rs = ps.executeQuery();

			if (rs.next()) {
				orderReport = setOrderReport(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}

		return orderReport;
	}

	@Override
	public List<OrderReport> getAll() {
		return getAllBy(GET_ALL);
	}

	@Override
	public List<OrderReport> getAllByOrderId(Integer orderId) {
		return getAllBy(GET_ALL_BY_ORDERID, orderId);
	}

	@Override
	public List<OrderReport> getAllByEmpId(Integer empId) {
		return getAllBy(GET_ALL_BY_EMPID, empId);
	}

	@Override
	public List<OrderReport> getAllByRStatus(Integer rStatus) {
		return getAllBy(GET_ALL_BY_RSTATUS, rStatus);
	}

	@Override
	public List<OrderReport> getAllByResult(Integer result) {
		return getAllBy(GET_ALL_BY_RESULT, result);
	}

	@Override
	public void update(OrderReport orderReport) {
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(UPDATE);
			ps.setObject(1, orderReport.getEmpId(), Types.INTEGER);
			ps.setInt(2, orderReport.getRStatus());
			ps.setObject(3, orderReport.getResult(), Types.INTEGER);
			ps.setObject(4, orderReport.getNote(), Types.VARCHAR);
			ps.setTimestamp(5, currentTimestamp);
			ps.setInt(6, orderReport.getReportId());
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

	private OrderReport setOrderReport(ResultSet rs) {
		OrderReport orderReport = new OrderReport();

		try {
			orderReport.setReportId(rs.getInt("reportId"));
			orderReport.setOrderId(rs.getInt("orderId"));
			orderReport.setEmpId(rs.getObject("empId", Integer.class));
			orderReport.setReportDate(rs.getTimestamp("reportDate"));
			orderReport.setDescription(rs.getString("description"));
			orderReport.setRStatus(rs.getInt("rStatus"));
			orderReport.setAuditDate(rs.getObject("auditDate", Timestamp.class));
			orderReport.setResult(rs.getObject("result", Integer.class));
			orderReport.setNote(rs.getObject("note", String.class));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderReport;
	}

	private List<OrderReport> getAllBy(String by, Integer... byid) {
		if (byid.length > 1) {
			return null;
		}

		List<OrderReport> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(by);

			if (byid.length == 1) {
				ps.setInt(1, byid[0]);
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(setOrderReport(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}

		if (list.isEmpty()) {
			list.add(null);
		}

		return list;
	}
}
