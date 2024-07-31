package com.mywebapp.controllers.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.dto.UserDto;
import com.mywebapp.service.RoomService;
import com.mywebapp.service.RoomServiceImpl;

@WebServlet("/service/guestMain")
public class RoomListController extends HttpServlet {
	
	//RoomServiceImpl 불러오기
	private RoomService roomService = new RoomServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		// 페이지 번호 처리
		String pNum = req.getParameter("pageNum");
		
		int pageNum = 1;
		if(pNum != null) {
			pageNum = Integer.parseInt(pNum);
		}
		int pageSize = 10; // 한 페이지에 보여줄 항목 수
		
		// RoomServiceImpl의 메서드들 호출
		List<RoomListItemDto> roomList = roomService.getRoomList(pageNum, pageSize);
		int totalRoomCount = roomService.getTotalRoomCount();
		Map<String, Object> paginationInfo = roomService.calculatePagination(pageNum, pageSize, totalRoomCount);
		
		// request 객체를 통해 guestMain.jsp로 데이터들 전달
		req.setAttribute("roomList", roomList);
		req.setAttribute("pageCount", paginationInfo.get("pageCount"));
		req.setAttribute("startPage", paginationInfo.get("startPage"));
		req.setAttribute("endPage", paginationInfo.get("endPage"));
		req.setAttribute("pageNum", paginationInfo.get("pageNum"));
		
		// 세션에 사용자 정보가 있는 경우만 사용자 정보를 가져와서 전달
		Object userObj = req.getSession().getAttribute("user");
		if (userObj != null) {
			req.setAttribute("userId" , ((UserDto) userObj).getId());
		}
		
		req.getRequestDispatcher("/jsp/service/guestMain.jsp").forward(req, resp);
		
		
	}
	

}
