package com.mywebapp.dao;

import java.util.List;

import com.mywebapp.model.Booking;

public interface BookingDao {
	void updateBookingStatus(long bookingId, int bookingStatus);
	List<Booking> getAllBookings();
}
