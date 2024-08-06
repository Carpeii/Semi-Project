package com.mywebapp.controllers.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dto.RoomDetailDto;

@WebServlet("/service/roomDetail")
public class RoomDetailController extends HttpServlet {
	private RoomDao roomDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		roomDao = new RoomDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    // guestMain.jsp에서 방 id 파라미터 가져오기
        int roomId = Integer.parseInt(req.getParameter("roomId"));
        
        System.out.println(roomId);
        // DAO를 사용하여 방의 상세 정보를 가져옵니다.
        RoomDetailDto room = roomDao.getRoomById(roomId);
        System.out.println(room.getRoomName());
        req.setAttribute("room", room);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/service/roomDetail.jsp");
        dispatcher.forward(req, resp);
	}
	
}
