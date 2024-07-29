package com.mywebapp.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.model.Room;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String searchWord = req.getParameter("searchWord");
		System.out.println(searchWord);
		
	RoomDao dao = new RoomDao(); 
	ArrayList<Room> rooms = (ArrayList<Room>)dao.search(searchWord);
	for(Room r : rooms) {
		int price = r.getRoomPrice().getRentPrice();
		int approve = r.getApprove();
		String address = r.getJibunAddress();
		String addressDetail = r.getAddressDetail();
		
		System.out.println(approve +"   " + address +" " +addressDetail+" " + price );
		
	}
	//클라이언트에 보여줄 값으로 가공 후 dto에 넣어서 보내기
	req.setAttribute("rooms", rooms);
	
	
	
	try {
		
		req.getRequestDispatcher("/jsp/service/search.jsp").forward(req, resp);
	} catch (ServletException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}


