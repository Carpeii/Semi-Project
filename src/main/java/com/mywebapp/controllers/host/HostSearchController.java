package com.mywebapp.controllers.host;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.model.Room;
@WebServlet("/host/search")
public class HostSearchController  extends HttpServlet  {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String searchword = req.getParameter("searchword");
		System.out.println(searchword);
		
	RoomDao dao = new RoomDao(); 
	ArrayList<Room> rooms = (ArrayList<Room>)dao.search(searchword);
	for(Room r : rooms) {
		int approve = r.getApprove();
		String address = r.getJibunAddress();
		System.out.println(approve +"   " + address);
	}
	}

}
