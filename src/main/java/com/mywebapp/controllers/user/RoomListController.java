package com.mywebapp.controllers.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.model.Room;
import com.mywebapp.service.RoomService;
import com.mywebapp.service.RoomServiceImpl;
import com.mywebapp.util.JdbcUtil;

@WebServlet("/service/guestMain")
public class RoomListController extends HttpServlet {
	
	private RoomService roomService = new RoomServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String pNum = req.getParameter("pageNum");
		
		int pageNum = 1;
		if(pNum != null) {
			pageNum = Integer.parseInt(pNum);
		}
		int pageSize = 10; // 한 페이지에 보여줄 항목 수
		
		List<RoomListItemDto> roomList = roomService.getRoomList(pageNum, pageSize);
		int totalRoomCount = roomService.getTotalRoomCount();
		Map<String, Object> paginationInfo = roomService.calculatePagination(pageNum, pageSize, totalRoomCount);
		
		req.setAttribute("roomList", roomList);
		req.setAttribute("pageCount", paginationInfo.get("pageCount"));
		req.setAttribute("startPage", paginationInfo.get("startPage"));
		req.setAttribute("endPage", paginationInfo.get("endPage"));
		req.setAttribute("pageNum", paginationInfo.get("pageNum"));
		
		req.getRequestDispatcher("/jsp/service/guestMain.jsp").forward(req, resp);
		
		
	}
	
	

	
	

}
