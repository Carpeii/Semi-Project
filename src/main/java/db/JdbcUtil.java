package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
    public static Connection getCon() {
        Connection con = null;
        try {
            // MariaDB JDBC 드라이버 클래스 로드
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/pyeong";
            con = DriverManager.getConnection(url, "root", "123456");
            System.out.println("MariaDB 연결 성공!");
            return con;
        } catch (ClassNotFoundException ce) {
            System.out.println("JDBC 드라이버를 찾을 수 없습니다: " + ce.getMessage());
        } catch (SQLException se) {
            System.out.println("데이터베이스 연결 오류: " + se.getMessage());
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
