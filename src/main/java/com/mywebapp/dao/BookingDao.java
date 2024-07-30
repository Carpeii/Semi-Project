package com.mywebapp.dao;

import java.sql.Date;
import java.util.List;

import com.mywebapp.model.Booking;

public interface BookingDao {
	void insertBooking(long guestId, long roomId, Date checkInDate, Date checkOutDate);
}
