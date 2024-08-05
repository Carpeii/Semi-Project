package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mywebapp.model.Booking;
import com.mywebapp.model.Room;
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
	
//	@Override
//	public void insertBookingAndRoom(Booking booking, Room room) {
//	    Connection con = null;
//	    PreparedStatement pstmtRoom = null;
//	    PreparedStatement pstmtBooking = null;
//	    ResultSet generatedKeys = null;
//	    
//	    String insertRoomSql = "INSERT INTO room (room_name, jibun_address, street_address, address_detail, floor) VALUES (?, ?, ?, ?, ?)";
//	    String insertBookingSql = "INSERT INTO booking (guest_id, room_id, check_in_date, check_out_date, booking_status) VALUES (?, LAST_INSERT_ID(), ?, ?, ?)";
//	    
//	    try {
//	        con = JdbcUtil.getCon();
//	        pstmtRoom = con.prepareStatement(insertRoomSql, PreparedStatement.RETURN_GENERATED_KEYS);
//	        
//	        // Insert room
//	        pstmtRoom.setString(1, room.getRoomName());
//	        pstmtRoom.setString(2, room.getJibunAddress());
//	        pstmtRoom.setString(3, room.getStreetAddress());
//	        pstmtRoom.setString(4, room.getAddressDetail());
//	        pstmtRoom.setInt(5, room.getFloor());
//
//	        int affectedRows = pstmtRoom.executeUpdate();
//
//	        if (affectedRows == 0) {
//	            throw new SQLException("Creating room failed, no rows affected.");
//	        }
//
//	        // Retrieve the generated room ID
//	        generatedKeys = pstmtRoom.getGeneratedKeys();
//	        if (generatedKeys.next()) {
//	            long roomId = generatedKeys.getLong(1);
//
//	            pstmtBooking = con.prepareStatement(insertBookingSql);
//	            // Insert booking
//	            pstmtBooking.setLong(1, booking.getGuestId());
//	            pstmtBooking.setLong(2, roomId); // Use the generated room ID
//	            pstmtBooking.setDate(3, booking.getCheckInDate());
//	            pstmtBooking.setDate(4, booking.getCheckOutDate());
//	            pstmtBooking.setInt(5, 0); // booking_status is 0
//
//	            pstmtBooking.executeUpdate();
//	        } else {
//	            throw new SQLException("Creating booking failed, no ID obtained.");
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    } finally {
//	        // Close resources in the reverse order of their creation
//	        if (generatedKeys != null) {
//	            try {
//	                generatedKeys.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        if (pstmtBooking != null) {
//	            try {
//	                pstmtBooking.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        if (pstmtRoom != null) {
//	            try {
//	                pstmtRoom.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        if (con != null) {
//	            try {
//	                con.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//	}

	
	
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



}
