package com.mywebapp.actions.calendar;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.actions.Action;
import com.mywebapp.dao.BookingDaoImpl;
import com.mywebapp.model.Booking;

public class ReservationAvailablePeriodCallAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String dateString = req.getParameter("select");
		System.out.println(dateString);
		
		// String으로 받은 날짜(연도-월-일) 형식 -> sqlDate타입으로 형변환
		try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//문자열을 util.Date타입으로 변환
		java.util.Date utilDate = format.parse(dateString);
		//util.Date타입을 SQL.Date타입으로 변환
		Date sqlDate = new Date(utilDate.getTime());
		
		BookingDaoImpl dao = new BookingDaoImpl();
		//checkIn값을 가져오는 객체
		Booking b = dao.reservationAvailablePeriodCall(sqlDate);
		 LocalDate a = b.getCheckInDate().toLocalDate();
		 System.out.println(a);
		
		
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
