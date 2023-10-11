package com.twoclothing.utils;

import java.sql.*;

public class JDBCUtil {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/twoclothing?serverTimezone=Asia/Taipei";
    private static final String user = "root";
    private static final String password = "root";

    // 註冊驅動只需註冊一次,放在static區塊,類加載時執行一次
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 獲取資料庫連接物件
     * @return 資料庫連接物件
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 釋放資源
     * @param conn 連接物件
     * @param ps 操作物件
     * @param rs 結果集物件
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
