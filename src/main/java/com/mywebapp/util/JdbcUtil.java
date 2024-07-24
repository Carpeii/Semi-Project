package com.mywebapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
    public static Connection getCon() {
        Connection con = null;
        try {
            // MariaDB JDBC 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");
            // MariaDB URL (localhost:3306은 MariaDB의 기본 포트)
            String url = "jdbc:mariadb://localhost:3306/pyeong";
            // MariaDB 사용자명과 비밀번호
            con = DriverManager.getConnection(url, "root", "123456");
            return con;
        } catch (ClassNotFoundException ce) {
            System.out.println(ce.getMessage());
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return null;
    }

    public static void close(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public static void close(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
