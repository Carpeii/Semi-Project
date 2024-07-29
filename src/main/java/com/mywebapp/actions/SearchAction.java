package com.mywebapp.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.model.Room;

public class SearchAction implements Action {
	private int viewCount = 15;
	private String searchUrl = "/jsp/service/search.jsp";
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String searchWord = req.getParameter("searchWord");
		int pageNum = 1;
		if(req.getParameter("pageNum")!=null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}
		System.out.println(searchWord);
		
	try {
		RoomDao dao = new RoomDao(); 
		//검색 결과의 총 수
		int totalRecord = dao.searchTotalRecord(searchWord);
		//총페이지    =  ((총 행수-1)/15)+1) 한페이지에 15개
		int totalPage = ((totalRecord-1)/viewCount)+1;
		//보여줄 페이지의 데이터 1= 2페이지
		int viewRecord = (pageNum-1)*viewCount;
		System.out.println("viewRecord"+viewRecord);
		
		ArrayList<Room> rooms = (ArrayList<Room>)dao.getRoomList(searchWord,viewRecord);
		for(Room r : rooms) {
			int price = r.getRoomPrice().getRentPrice();
			int approve = r.getApprove();
			String address = r.getJibunAddress();
			String addressDetail = r.getAddressDetail();
			String roomName = r.getRoomName();
			
			System.out.println(approve +"   " + address +" "+roomName+" " +addressDetail+" "+ price );
		}
		System.out.println("totalPage"+totalPage);
		System.out.println("pageNum"+pageNum);
		//클라이언트에 보여줄 값으로 가공 후 dto에 넣어서 보내기
		req.setAttribute("totalpage",totalPage);
		req.setAttribute("pagenum",pageNum);
		req.setAttribute("rooms", rooms);
		req.setAttribute("searchWord", searchWord);
		
		req.getRequestDispatcher(searchUrl).forward(req, resp);
	} catch (ServletException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}


