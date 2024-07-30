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
import com.mywebapp.model.Room;
import com.mywebapp.util.JdbcUtil;

@WebServlet("/jsp/service/guestMain")
public class RoomListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try {
//			RoomDao dao = new RoomDao(JdbcUtil.getCon());
//			List<Room> roomList = dao.findAll();
//			req.setAttribute("roomList", roomList);
//			
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/service/guestMain.jsp");
//			dispatcher.forward(req, resp);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
	}
	
	

}
