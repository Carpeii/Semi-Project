package com.mywebapp.controllers.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomPrice;
@WebServlet("/host/search")
public class SearchController  extends HttpServlet  {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String searchword = req.getParameter("searchword");
		System.out.println(searchword);
		
	RoomDao dao = new RoomDao(); 
	ArrayList<Room> rooms = (ArrayList<Room>)dao.search(searchword);
	for(Room r : rooms) {
		int price = r.getRoomPrice().getRentPrice();
		int approve = r.getApprove();
		String address = r.getJibunAddress();
		String addressDetail = r.getAddressDetail();
		
		System.out.println(approve +"   " + address +" " +addressDetail+" " + price );
		
	}
	req.setAttribute("rooms", rooms);
	req.getRequestDispatcher("/jsp/service/search.jsp").forward(req, resp);
	}

}
