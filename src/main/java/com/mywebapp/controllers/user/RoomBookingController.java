package com.mywebapp.controllers.user;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mywebapp.dao.BookingDao;
import com.mywebapp.dao.BookingDaoImpl;
import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dto.RoomDetailDto;
import com.mywebapp.dto.MemberDto;
import com.mywebapp.model.Booking;
import com.mywebapp.model.Room;

@WebServlet("/service/bookRoom")
public class RoomBookingController extends HttpServlet {
	
	private BookingDao bookingDao;
	private RoomDao roomDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		bookingDao = new BookingDaoImpl();
		roomDao = new RoomDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		 // 세션에서 현재 로그인한 사용자의 정보 가져오기
		HttpSession session = req.getSession();
		MemberDto user = (MemberDto) session.getAttribute("user");
//		String userId = (String) session.getAttribute("user");
		
	    // 디버깅을 위한 출력
	    if (user == null) {
	        System.out.println("DEBUG: MemberDto is null. Redirecting to login.");
	    } else {
	        System.out.println("DEBUG: MemberDto found. User ID = " + user.getId());
	    }

		

		if (user != null) {
	        // 로그인된 사용자의 아이디 가져오기
			long guestId = user.getId();

			
	        // 선택한 방의 ID 가져오기
			long roomId = Long.parseLong(req.getParameter("roomId"));
			// 체크인 날짜와 체크아웃 날짜 가져오기
			Date checkInDate = Date.valueOf(req.getParameter("checkInDate"));
			Date checkOutDate = Date.valueOf(req.getParameter("checkOutDate"));
			
			Booking booking = new Booking();
			booking.setGuestId(guestId);
			booking.setRoomId(roomId);
			booking.setCheckInDate(checkInDate);
			booking.setCheckOutDate(checkOutDate);

			// booking_status 0으로 해서 booking테이블에 하나 삽입
			bookingDao.insertBooking(booking);
			
			//------------------------------------위에까지가 booking

			
			// 세션에 예약한 방 정보를 저장
			session.setAttribute("bookedRoomId", roomId); // roomId를 직접 저장
			
			// 사용자 방 정보 조회를 위해 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/user/guestBooking");
			
		} else {
            // 사용자 정보가 없을 경우에 대한 처리 (예: 로그인 페이지로 리다이렉트)
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

}
