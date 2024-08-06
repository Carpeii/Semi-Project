package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mywebapp.model.Booking;
import com.mywebapp.util.JdbcUtil;

public class BookingDaoImpl implements BookingDao {

	@Override
	public void insertBooking(Booking booking) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//booking_status는 0으로 설정(승인 요청) 
		String sql = "INSERT INTO booking (guest_id, room_id, check_in_date, check_out_date, booking_status) VALUES (?, ?, ?, ?, 0)";
		
		try {
			con = JdbcUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, booking.getGuestId());
			pstmt.setLong(2, booking.getRoomId());
			pstmt.setDate(3, booking.getCheckInDate());
			pstmt.setDate(4, booking.getCheckOutDate());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
	@Override
	public List<Booking> getBookingsByRoomId(long roomId, int bookingStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Booking> bookings = new ArrayList<>();

		String sql = "SELECT * FROM booking WHERE room_id = ? AND booking_status = ?";
		try {
			con = JdbcUtil.getCon();
			pstmt = con.prepareStatement(sql);

			pstmt.setLong(1, roomId);
			pstmt.setInt(2, bookingStatus);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Booking booking = new Booking();
				booking.setId(rs.getLong("id"));
				booking.setGuestId(rs.getLong("guest_id"));
				booking.setRoomId(rs.getLong("room_id"));
				booking.setCheckInDate(rs.getDate("check_in_date"));
				booking.setCheckOutDate(rs.getDate("check_out_date"));
				booking.setBookingStatus(rs.getInt("booking_status"));

				bookings.add(booking);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return bookings;
	}

	@Override
	public void approveBooking(long bookingId) {
		String sql = "UPDATE booking SET booking_status = 1 WHERE id = ?";
		try (Connection con = JdbcUtil.getCon();
			 PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, bookingId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void declineBooking(long bookingId) {
		String sql = "UPDATE booking SET booking_status = 2 WHERE id = ?";
		try (Connection con = JdbcUtil.getCon();
			 PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setLong(1, bookingId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//방계약 달력의 계약된 날짜 가져오는 메소드
	//roomId를 인자로받음 
	//반환값 List<Booking> checkInDate , dheckOutDate 만 들어있음
	@Override
	 public List<Booking> rentalSchedule(Long roomId) {
			List<Booking> scheduleList = new ArrayList<Booking>();
			//curdate() -> 오늘의 연 월 일 반환 -> 체크아웃 날짜가 오늘과 같거나 이후인 모든 행 오름차순으로 정렬 가장 옛날부터 -> 최신
			String sql = "select * from booking where room_id = ? and curdate() <= check_out_date"
					+ " order by check_in_date asc";
			try {
				Connection con = JdbcUtil.getCon();
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setLong(1,roomId);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Booking booking = new Booking();
							booking.setCheckInDate(rs.getDate("check_in_date"));
							booking.setCheckOutDate(rs.getDate("check_out_date"));
					scheduleList.add(booking);
				}
			}catch(SQLException s) {
				s.printStackTrace();
			}
			return scheduleList;
		}
	@Override
	public Booking reservationAvailablePeriodCall(Date selectDate, long roomId) {
		//Id도 추가해야함
		Booking booking =  new Booking();
		String sql = "select check_in_date from booking where ? < check_in_date and room_id=? limit 1";
		try {
			Connection con = JdbcUtil.getCon();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setDate(1,selectDate);
			pstmt.setLong(2,roomId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				booking = new Booking();
				booking.setCheckInDate(rs.getDate("check_in_date"));
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}
		
		
		return booking;
	}
}
