package com.twoclothing.model.employee;

import com.twoclothing.utils.JDBCUtil;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class EmployeeJDBCDAO {

//	private static final String INSERT_STMT = "INSERT INTO employee(deptId,empName,phone,address,email,pswdHash,empStatus,avatar) VALUES(?,?,?,?,?,?,?,?)";
//	private static final String GET_ALL_STMT = "SELECT * FROM employee";
//	private static final String GET_ONE_STMT = "SELECT * FROM employee WHERE empId = ?";
//	private static final String DELETE = "DELETE FROM employee WHERE empId = ?";
//	private static final String UPDATE = "UPDATE employee SET "
//			+ "deptId = ?, empName = ?, empName = ?, phone = ?, address = ?, email = ?, pswdHash = ?, empStatus = ?, avatar = ?"
//			+ "WHERE empId = ?";

//	@Override
//	public Employee getEmployeeById(Integer empId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Employee> getAllEmployees() {
//		List<Employee> list = new ArrayList<>();
//		Employee emp = null;
//
//		Field[] fields = Employee.class.getDeclaredFields();
//		for (Field field : fields) {
//			field.setAccessible(true); // 將屬性設置為可訪問,即使是私有屬性也可以訪問
//		}
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = JDBCUtil.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				emp = new Employee();
//
//				for (Field field : fields) {
//
//					String fieldName = field.getName(); // 獲取屬性名稱
//					Object value = rs.getObject(fieldName); // 從結果集中獲取資料
//
//					// 使用FieldUtils的writeField方法將資料設置到物件的屬性中
//					FieldUtils.writeField(field, emp, value);
//
//				}
//				list.add(emp);
//			}
//
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException("類別反射失敗 " + e.getMessage());
//		} catch (SQLException se) {
//			throw new RuntimeException("資料庫操作失敗 " + se.getMessage());
//		} finally {
//			JDBCUtil.close(con, pstmt, rs);
//		}
//		return list;
//
//	}
//
//	@Override
//	public void addEmployee(Employee employee) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void updateEmployee(Employee employee) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteEmployee(Integer empId) {
//		// TODO Auto-generated method stub
//
//	}

}
