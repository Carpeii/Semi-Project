package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mywebapp.model.Booking;
import com.mywebapp.util.JdbcUtil;

public class BookingDaoImpl implements BookingDao {

	@Override
	public void insertBooking(long guestId, long roomId, Date checkInDate, Date checkOutDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//booking_status는 0으로 설정(승인 요청) 
		String sql = "INSERT INTO booking (guest_id, room_id, check_in_date, check_out_date, booking_status) VALUES (?, ?, ?, ?, 0)";
		
		try {
			con = JdbcUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, guestId);
			pstmt.setLong(2, roomId);
			pstmt.setDate(3, checkInDate);
			pstmt.setDate(4, checkOutDate);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, null);

		}
		
	}

	

}
