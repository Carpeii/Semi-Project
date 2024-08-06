package com.mywebapp.actions.calendar;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.actions.Action;
import com.mywebapp.dao.BookingDaoImpl;
import com.mywebapp.model.Booking;

public class ReservationAvailablePeriodCallAction implements Action {
	private String calendarUrl = "/test/popup.jsp";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String dateString = req.getParameter("selectDate");
		LocalDate select = LocalDate.parse(dateString);
		//db에서 데이터를 받아온 객체
		Booking b;
		//db에서 가져온 checkInDate 값을 LocalDate형으로 받는 객체
		LocalDate firstCheckInDate;
		//선택한 날짜와 db에서 가져온 날짜의 차이값을 구하는 변수
		Long dateDiff = 0L;
		System.out.println(select);
		
		// String으로 받은 날짜(연도-월-일) 형식 -> sqlDate타입으로 형변환
		try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//문자열을 -> util.Date타입으로 변환
		java.util.Date utilDate = format.parse(dateString);
		//         util.Date타입을 -> SQL.Date타입으로 변환
		Date sqlDate = new Date(utilDate.getTime());
		long roomId = 2l;
		
		BookingDaoImpl dao = new BookingDaoImpl();
		//checkIn값을 가져오는 객체
		b = dao.reservationAvailablePeriodCall(sqlDate, roomId);
		//db에서 가져온 값이 있다면 선택한 날짜와 비교해 최대로 가능한 기간 계산
		if(b.getCheckInDate() != null) {
			firstCheckInDate = b.getCheckInDate().toLocalDate();
			System.out.println(firstCheckInDate);
			dateDiff = ChronoUnit.DAYS.between(select, firstCheckInDate);
			System.out.println("차이 값"+dateDiff);
			
			//db에서 가져온 값이 없다면 최대가간 91일 3달
		} else {
			dateDiff = 91L;
			System.out.println("모두 가능 : " + dateDiff);
		}
		req.setAttribute("dateDiff", dateDiff);
			req.getRequestDispatcher(calendarUrl).forward(req, resp);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
