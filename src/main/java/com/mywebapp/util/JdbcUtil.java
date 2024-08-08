package com.mywebapp.util;

import java.sql.*;

public class JdbcUtil {
    public static Connection getCon() {
        Connection con = null;
        try {
            // MariaDB JDBC 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");
            // MariaDB URL (localhost:3306은 MariaDB의 기본 포트)
            String url = "jdbc:mariadb://192.168.0.34:3306/pyeong?useUnicode=true&characterEncoding=UTF-8";
            // MariaDB 사용자명과 비밀번호
            con = DriverManager.getConnection(url, "test", "123");
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
    public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
