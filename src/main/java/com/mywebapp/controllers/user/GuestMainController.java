package com.mywebapp.controllers.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mywebapp.dto.MemberDto;
import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.service.RoomService;
import com.mywebapp.service.RoomServiceImpl;

@WebServlet("/guestMain")
public class GuestMainController extends HttpServlet {
	
	//RoomServiceImpl 불러오기
	private RoomService roomService = new RoomServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		int approve = 1; // 승인완료
		
		// 페이지 번호 처리
		String pNum = req.getParameter("pageNum");
		int pageNum = 1;
		if(pNum != null) {
			pageNum = Integer.parseInt(pNum);
		}
		int pageSize = 9; // 한 페이지에 보여줄 항목 수
		
		// RoomServiceImpl의 메서드들 호출
		List<RoomListItemDto> roomList = roomService.getRoomList(pageNum, pageSize, approve);
		int totalCount = roomService.getTotalRoomCount(approve);
		
		System.out.println(totalCount);
		Map<String, Object> paginationInfo = roomService.calculatePagination(pageNum, pageSize, totalCount);
		
		
		// request 객체를 통해 guestMain.jsp로 데이터들 전달
		req.setAttribute("roomList", roomList);
		req.setAttribute("pageCount", paginationInfo.get("pageCount"));
		req.setAttribute("startPage", paginationInfo.get("startPage"));
		req.setAttribute("endPage", paginationInfo.get("endPage"));
		req.setAttribute("pageNum", paginationInfo.get("pageNum"));
		

		
		// LoginController에서 세션 받아오기
		HttpSession session = req.getSession();
		MemberDto user = (MemberDto) session.getAttribute("user");
		
        // 세션에 user가 없으면 로그인 상태가 아닌 것으로 간주
		if (user == null) {
			req.setAttribute("user", null);
		} else {
			req.setAttribute("user", user);
		}
//		if (user != null) {
//			req.setAttribute("userId" , userId);
//		}
		
		

		req.getRequestDispatcher("/jsp/service/guestMain.jsp").forward(req, resp);
		
		
	}
	

}
