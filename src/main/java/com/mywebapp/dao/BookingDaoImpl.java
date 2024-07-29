package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mywebapp.model.Booking;
import com.mywebapp.util.JdbcUtil;

public class BookingDaoImpl implements BookingDao {

	// 예약 상태 업데이트
	@Override
	public void updateBookingStatus(long bookingId, int bookingStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getCon();
			String sql = "UPDATE booking SET booking_status = ? WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookingStatus);
			pstmt.setLong(2, bookingId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}

	@Override
	public List<Booking> getAllBookings() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
