package com.mywebapp.controllers.user;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dao.BookingDao;
import com.mywebapp.dao.BookingDaoImpl;
import com.mywebapp.dto.UserDto;

@WebServlet("/service/bookRoom")
public class RoomBookingController extends HttpServlet {
	
	private BookingDao bookingDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		bookingDao = new BookingDaoImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 세션에서 현재 로그인한 사용자의 정보 가져오기
		UserDto user = (UserDto) req.getSession().getAttribute("user");
		
		if (user != null) {
	        // 로그인된 사용자의 아이디 가져오기
			long guestId = Long.parseLong(user.getId());
			
	        // 선택한 방의 ID 가져오기
			long roomId = Long.parseLong(req.getParameter("roomId"));
			// 체크인 날짜와 체크아웃 날짜 가져오기
			Date checkInDate = Date.valueOf(req.getParameter("checkInDate"));
			Date checkOutDate = Date.valueOf(req.getParameter("checkOutDate"));
			
			bookingDao.insertBooking(guestId, roomId, checkInDate, checkOutDate);
			
		      // 예약된 방 정보 가져오기 (예시)
            //Room bookedRoom = bookingDAO.getBookedRoom(roomId); // 예약된 방 정보를 DB에서 가져오는 예시 메서드
			
            // 예약이 성공적으로 저장되었다면 bookingSuccess.jsp로 포워드
//	         request.setAttribute("bookedRoom", bookedRoom);
//			RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/jsp/service/bookingOk.jsp");
//			dispatcher.forward(req, resp);
			
	        req.getRequestDispatcher("/jsp/service/bookingStart.jsp").forward(req, resp);

			
		} else {
            // 사용자 정보가 없을 경우에 대한 처리 (예: 로그인 페이지로 리다이렉트)
			resp.sendRedirect(req.getContextPath() + "/jsp/auth/loginMain.jsp");
		}
	}

}
