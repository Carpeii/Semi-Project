package com.mywebapp.actions;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.model.Booking;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;

public class SearchAction implements Action {
	private int viewCount = 15;
	private String searchUrl = "/jsp/service/search.jsp";
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String searchWord = req.getParameter("searchWord");
		int pageNum = 1;
		int blockPerPage = 5;
		System.out.println(searchWord);
		
		if(req.getParameter("pageNum")!=null) {
			pageNum = Integer.parseInt(req.getParameter("pageNum"));
		}
		
	try {
		RoomDao dao = new RoomDaoImpl();
		//검색 결과의 총 수
		int totalRecord = dao.searchTotalRecord(searchWord);
		//총페이지    =  ((총 행수-1)/15)+1) 한페이지에 15개
		int totalPage = ((totalRecord-1)/viewCount)+1;
		//보여줄 페이지의 데이터 1= 2페이지의 행 수
		int viewRecord = (pageNum-1)*viewCount;
		int startBlock = ((pageNum -1) / blockPerPage) * blockPerPage + 1;
        int endBlock = startBlock + blockPerPage -1;
		
		ArrayList<Room> rooms = (ArrayList<Room>)dao.searchRoomList(searchWord,viewRecord);
		//총 9페이지라고 가정
		//현재 6페이지 ->  뒤로 가기 활성화 ㅇ
		//  (6     - 5) <= 1?
		// startBlock -blockPerPage <= 1
		
		//클라이언트에 보여줄 값으로 가공 후 dto에 넣어서 보내기
		//페이지를 컨트롤하는 변수
		//총 페이지
		req.setAttribute("totalPage",totalPage);
		//현재 페이지
		req.setAttribute("pageNum",pageNum);
		//하나의 페이지 블럭의 페이지 수
		req.setAttribute("blockPerPage",blockPerPage);
		//페이지블럭의 시작 페이지
		req.setAttribute("startBlock",startBlock);
		//페이집 블럭의 끝 페이지
		req.setAttribute("endBlock",endBlock);
		
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


