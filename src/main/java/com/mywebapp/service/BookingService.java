package com.mywebapp.service;

import java.time.LocalDate;
import com.mywebapp.dao.BookingDaoImpl;
import com.mywebapp.model.Booking;
import com.mywebapp.model.Period;

public class BookingService {

    private final BookingDaoImpl bookingDao;

    public BookingService() {
        this.bookingDao = new BookingDaoImpl();
    }

    // 사용자가 선택한 날짜(selectDate)와 방 ID (roomId)를 바탕으로 예약 가능한 기간을 계산
    public int calculateAvailablePeriod(LocalDate selectDate, long roomId) {
        Booking booking = bookingDao.reservationAvailablePeriodCall(selectDate, roomId);

        if (booking.getCheckInDate() != null) {
            LocalDate firstCheckInDate = booking.getCheckInDate().toLocalDate();
            return selectDate.until(firstCheckInDate).getDays();
        } else {
            return Period.MAX_AVAILABLE_DAYS.getDays();
        }
    }
}
