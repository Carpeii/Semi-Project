package com.mywebapp.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dto.RoomDTO;

@WebServlet("/jsp/service/guestMain")
public class roomListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RoomDao dao = new RoomDao();
		ArrayList<RoomDTO> roomList = dao.list();
		
		req.setAttribute("roomList", roomList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/service/guestMain.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
	}
	
	

}
