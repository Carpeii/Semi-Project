package com.mywebapp.controllers.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.model.Room;
import com.mywebapp.util.JdbcUtil;

@WebServlet("/service/guestMain")
public class RoomListController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		// 페이징 처리
		String pNum = req.getParameter("pageNum");
		
		int pageNum = 1;
		if(pNum != null) {
			pageNum = Integer.parseInt(pNum);
		}
		
		int startRow = (pageNum - 1) * 10 + 1;
		int endRow = startRow + 9;

		RoomDao dao = new RoomDao();
		List<RoomListItemDto> roomList = dao.findAllRoomListItems();
		req.setAttribute("roomList", roomList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/service/guestMain.jsp");
		dispatcher.forward(req, resp);
	}
	

	
	

}
