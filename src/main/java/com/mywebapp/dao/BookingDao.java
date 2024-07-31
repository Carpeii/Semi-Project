package com.mywebapp.dao;

import com.mywebapp.model.Booking;
import com.mywebapp.model.Room;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
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
