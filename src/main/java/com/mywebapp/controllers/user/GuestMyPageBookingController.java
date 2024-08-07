package com.mywebapp.controllers.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dto.GuestRoomBookingDto;
import com.mywebapp.dto.MemberDto;

@WebServlet("/user/guestBooking")
public class GuestMyPageBookingController extends HttpServlet {
	
	private RoomDao roomDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		roomDao = new RoomDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberDto user = (MemberDto) session.getAttribute("user");
		Long bookedRoomId = (Long) session.getAttribute("bookedRoomId");
//		RoomDetailDto bookedRoom = (RoomDetailDto)session.getAttribute("bookedRoom");
		
		if (user != null) {
			long guestId = user.getId();
			

			//사용자 방 정보 조회
			List<GuestRoomBookingDto> userRooms = roomDao.getRoomsByGuestIdWithStatus(guestId);
			req.setAttribute("userRooms", userRooms);
			
	        // bookedRoomId를 JSP 페이지로 전달
			req.setAttribute("bookedRoomId", bookedRoomId);
			
			
			req.getRequestDispatcher("/jsp/user/myPageGuestBooking.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/jsp/auth/loginMain.jsp");
		}
	}
	
	
}
