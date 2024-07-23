package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	public static Connection getCon() {
		Connection con = null;
			String url = "jdbc:mariadb://localhost:3306/pyeong";
			String user = "root";
			String password1 = "123";
			
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				
				con = DriverManager.getConnection(url,user,password1);
				return con;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	public static void close(Connection con) {
		try {
			if(con!=null) con.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt!=null) stmt.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs!=null) rs.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
	public static void close(Connection con,Statement stmt,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
}