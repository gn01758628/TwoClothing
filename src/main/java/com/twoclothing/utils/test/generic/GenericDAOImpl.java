//package com.twoclothing.utils.test.generic;
//
//import com.twoclothing.utils.JDBCUtil;
//import org.apache.commons.lang3.reflect.FieldUtils;
//
//import java.lang.reflect.Field;
//import java.sql.*;
//import java.util.List;
//import java.util.Map;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class GenericDAOImpl<T> {
//	private final Class<T> type;
//	private final Field[] fields;
//	private final Map<String, Class<?>> fieldMap = new HashMap<>();
//
//	private static final String INSERT_TEMPLATE = "INSERT INTO %s (%s) VALUES (%s)";
//	private static final String UPDATE_TEMPLATE = "UPDATE %s SET %s  %s";
//	private static final String DELETE_TEMPLATE = "DELETE FROM %s  %s";
//	private static final String GET_ALL_TEMPLATE = "SELECT * FROM %s";
//	private static final String GET_BY_TEMPLATE = "SELECT * FROM %s %s";
//
//	public GenericDAOImpl(Class<T> type) {
//		this.type = type;
//		fields = type.getDeclaredFields();
//		initializeFieldMap();
//	}
//
//	private void initializeFieldMap() {
//		for (Field field : fields) {
//			field.setAccessible(true);
//			fieldMap.put(field.getName(), field.getType());
//		}
//	}
//
//	public void insert(T entity) throws SQLException {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String tableName = type.getSimpleName().toLowerCase(); // 前提!!! 表格名稱要跟傳進來的類別名稱同名
//
//		StringBuilder columns = new StringBuilder();
//		StringBuilder values = new StringBuilder();
//		buildInsertQuery(columns, values, entity); // 取得傳進來的物件 若欄位值為null 不添加該欄位資料
//
//		String sql = String.format(INSERT_TEMPLATE, tableName, columns, values);
//
//		try {
//			con = JDBCUtil.getConnection();
//			pstmt = con.prepareStatement(sql);
//			int parameterIndex = 1;
//
//			for (Field field : fields) {
//				Object value = null;
//				try {
//					value = field.get(entity);
//				} catch (IllegalAccessException e) {
//					e.printStackTrace(); // 處理IllegalAccessException
//				}
//
//				if (value != null) {
//					pstmt.setObject(parameterIndex, value);
//					parameterIndex++;
//				}
//			}
//			int status = pstmt.executeUpdate();
//			if (status == 1) {
//				// 編寫新增成功的執行代碼
//				System.out.println("成功新增" + status + "筆資料");
//			} else {
//				// 編寫新增失敗的執行代碼
//				System.out.println("新增失敗");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(con, pstmt, rs);
//		}
//	}
//
//	public void update(T entity, Map<String, Object> map) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String tableName = type.getSimpleName().toLowerCase();
//		StringBuilder pairs = new StringBuilder();
//		buildUpdateQuery(pairs, entity);
//		StringBuilder condition = new StringBuilder();
//		buildCondition(condition, map);
//
//		String sql = String.format(UPDATE_TEMPLATE, tableName, pairs, condition);
//
//		try {
//			con = JDBCUtil.getConnection();
//			pstmt = con.prepareStatement(sql);
//			int parameterIndex = 1;
//
//			for (Field field : fields) {
//				Object value = null;
//				try {
//					value = field.get(entity);
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				}
//				if (value != null) {
//					pstmt.setObject(parameterIndex, value);
//					parameterIndex++;
//				}
//			}
//			for (Map.Entry<String, Object> entry : map.entrySet()) {
//				String fieldName = entry.getKey();
//				Object value = entry.getValue();
//				Class<?> fieldType = fieldMap.get(fieldName);
//
//				if (value != null) {
//					// 將資料轉成對應變數名稱的類型
//					Object convertedValue = convertToFieldType(value, fieldType);
//					pstmt.setObject(parameterIndex, convertedValue);
//					parameterIndex++;
//				} else {
//				}
//			}
//			System.out.println(sql);
//			int status = pstmt.executeUpdate();
//			if (status >= 0) {
//				// 編寫新增成功的執行代碼
//				System.out.println("成功修改" + status + "筆資料");
//			} else {
//				// 編寫新增失敗的執行代碼
//				System.out.println("修改失敗");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(con, pstmt, rs);
//		}
//
//	}
//
//	public void delete(Map<String, Object> map) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int paraneterIndex = 1;
//
//		String tableName = type.getSimpleName().toLowerCase();
//		StringBuilder condition = new StringBuilder();
//		buildCondition(condition, map);
//
//		String sql = String.format(DELETE_TEMPLATE, tableName, condition);
//
//		try {
//			con = JDBCUtil.getConnection();
//			pstmt = con.prepareStatement(sql);
//
//			for (Map.Entry<String, Object> entry : map.entrySet()) {
//				Object value = entry.getValue();
//				if (value != null) {
//					Class<?> fieldType = fieldMap.get(entry.getKey());
//					Object convertedValue = convertToFieldType(value, fieldType);
//					pstmt.setObject(paraneterIndex, convertedValue);
//					paraneterIndex++;
//				} else {
//
//				}
//			}
//			int status = pstmt.executeUpdate();
//			if (status >= 0) {
//				// 編寫新增成功的執行代碼
//				System.out.println("成功刪除" + status + "筆資料");
//			} else {
//				// 編寫新增失敗的執行代碼
//				System.out.println("刪除失敗");
//			}
//
//		} catch (Exception e) {
//
//		} finally {
//			JDBCUtil.close(con, pstmt, rs);
//		}
//
//	}
//
//	public List<T> getAll() {
//
//		List<T> list = new ArrayList<>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String tableName = type.getSimpleName().toLowerCase(); // 前提!!! 表格名稱要跟傳進來的類別名稱同名
//		String sql = String.format(GET_ALL_TEMPLATE, tableName); // 表格名填入SQL語句
//
//		try {
//			con = JDBCUtil.getConnection();
//			pstmt = con.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				T entity = type.getDeclaredConstructor().newInstance();// 該類別要有無參數建構子
//
//				for (Field field : fields) {
//					String fieldName = field.getName(); // 獲取屬性名稱
//					Class<?> fieldType = fieldMap.get(fieldName); // 取得fieldMap中 對應的類型
//					Object value = rs.getObject(fieldName, fieldType);
////					Object value = getAttr(rs.getObject(fieldName));// 針對特定資料作類別處理 舊版本 
//					// 使用FieldUtils的writeField方法將資料設置到物件的屬性中
//					FieldUtils.writeField(field, entity, value);
//				}
//				list.add(entity);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace(); // 異常處理
//		} finally {
//			JDBCUtil.close(con, pstmt, rs);
//		}
//
//		return list;
//	}
//
//	public List<T> getBy(Map<String, Object> map) {
//
//		List<T> list = new ArrayList<>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int parameterIndex = 1;
//
//		// 以下針對SQL語句做補完
//		String tableName = type.getSimpleName().toLowerCase(); // 取得類別名
//		StringBuilder condition = new StringBuilder();
//		buildCondition(condition, map); // 補全condition對應問號
//		String sql = String.format(GET_BY_TEMPLATE, tableName, condition); // 補完SQL預設語句
//
//		try {
//			con = JDBCUtil.getConnection();
//			pstmt = con.prepareStatement(sql);
//
//			for (Map.Entry<String, Object> entry : map.entrySet()) {
//				String fieldName = entry.getKey();
//				Object value = entry.getValue();
//				Class<?> fieldType = fieldMap.get(fieldName);
//
//				if (value != null) {
//					Object convertedValue = convertToFieldType(value, fieldType);
//					pstmt.setObject(parameterIndex, convertedValue);
//					parameterIndex++;
//				} else {
//				}
//			}
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				T entity = type.getDeclaredConstructor().newInstance();// 該類別要有無參數建構子
//
//				for (Field field : fields) {
//					String fieldName = field.getName(); // 獲取屬性名稱
//					Class<?> fieldType = fieldMap.get(fieldName); // 取得fieldMap中 對應的類型
//					Object value = rs.getObject(fieldName, fieldType);// 針對特定資料作類別處理
//
//					// 使用FieldUtils的writeField方法將資料設置到物件的屬性中
//					FieldUtils.writeField(field, entity, value);
//				}
//				list.add(entity);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace(); // 處理異常
//		} finally {
//			JDBCUtil.close(con, pstmt, rs);
//		}
//
//		return list;
//
//	}
//
//	private Object convertToFieldType(Object value, Class<?> fieldType) {
//		if (fieldType.equals(Integer.class)) {
//			return Integer.parseInt(value.toString());
//		} else if (fieldType.equals(String.class)) {
//			return value.toString();
//		} else if (fieldType.equals(Double.class)) {
//			return Double.parseDouble(value.toString());
//		} else if (fieldType.equals(Boolean.class)) {
//			return Boolean.parseBoolean(value.toString());
//		}
//		// 其他類型的轉換
//
//		// 如果不需轉換,返回原值
//		return value;
//	}
//
//	private void buildInsertQuery(StringBuilder columns, StringBuilder values, T entity) {
////		Field[] fields = entity.getClass().getDeclaredFields();
//		for (Field field : fields) {
////			field.setAccessible(true); // 將屬性設置為可訪問,即使是私有屬性也可以訪問
//
//			String fieldName = field.getName();
//			Object value = null;
//			try {
//				value = field.get(entity);
//			} catch (IllegalAccessException e) {
//				e.printStackTrace(); // 處理IllegalAccessException
//			}
//
//			if (value != null) {
//				if (columns.length() > 0) {
//					columns.append(", ");
//					values.append(", ");
//				}
//				columns.append(fieldName);
//				values.append("?");
//			}
//		}
//
//	}
//
//	private void buildUpdateQuery(StringBuilder pairs, T entity) {
//		Field[] fields = entity.getClass().getDeclaredFields();
//		for (Field field : fields) {
//			field.setAccessible(true); // 將屬性設置為可訪問,即使是私有屬性也可以訪問
//
//			String fieldName = field.getName();
//			Object value = null;
//			try {
//				value = field.get(entity);
//			} catch (IllegalAccessException e) {
//				e.printStackTrace(); // 處理IllegalAccessException
//			}
//
//			if (value != null) {
//				if (pairs.length() > 0) {
//					pairs.append(", ");
//					pairs.append(fieldName);
//					pairs.append("=");
//					pairs.append("?");
//				}
//				pairs.append(fieldName);
//				pairs.append("=");
//				pairs.append("?");
//			}
//		}
//
//	}
//
//	private void buildCondition(StringBuilder condition, Map<String, Object> map) {
//		for (Map.Entry<String, Object> entry : map.entrySet()) {
//			// 判斷值是否為 null
//			String operater;
//			Class<?> fieldType = fieldMap.get(entry.getKey());
//			if (fieldType.equals(String.class)) {
//				operater = " LIKE ";
//			} else {
//				operater = " = ";
//			}
//			if (entry.getValue() != null) {
//				if (condition.length() > 0) {
//					condition.append(" AND ");
//					condition.append(entry.getKey());
//					condition.append(operater);
//					condition.append(" ? ");
//				} else {
//					condition.append(" WHERE ");
//					condition.append(entry.getKey());
//					condition.append(operater);
//					condition.append(" ? ");
//				}
//
//			}
//		}
//
//	}
//}