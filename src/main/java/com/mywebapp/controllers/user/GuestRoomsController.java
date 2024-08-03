package com.mywebapp.controllers.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;

@WebServlet("/user/guestBooking")
public class GuestRoomsController extends HttpServlet {
	
	private RoomDao roomDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		roomDao = new RoomDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user");
		
		if (userId != null) {
			long guestId = Long.parseLong(userId);
			
			//사용자 방 정보 조회
			
			
			
		} else {
			
		}
	}
	
	
}
