package com.mywebapp.dao;

import com.mywebapp.model.Booking;

import java.sql.Date;
import java.util.List;

public interface BookingDao {
    void insertBooking(Booking booking);
    List<Booking> getBookingsByRoomId(long roomId, int bookingStatus);
    void approveBooking(long bookingId);
    void declineBooking(long bookingId);
    List<Booking> rentalSchedule(Long roomId);
    Booking reservationAvailablePeriodCall(Date selectDate, long roomId);
    

}

