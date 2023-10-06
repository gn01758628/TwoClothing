package com.twoclothing.members;

import com.twoclothing.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class members {
    Connection conn;

    {
        try {
            conn = JDBCUtils.getConnection();
            System.out.println("連接成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
