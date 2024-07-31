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

public interface BookingDao {
    void insertBooking(Booking booking);
    List<Booking> getBookingsByRoomId(long roomId, int bookingStatus);
    void approveBooking(long bookingId);
    void declineBooking(long bookingId);
    List<Booking> rentalSchedule(Long roomId);
}
