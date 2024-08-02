package com.mywebapp.controllers.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.actions.Action;
import com.mywebapp.actions.CalendarAction;
@WebServlet("/calendar/*")
public class Calendar extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("달력Controller 호출");
		String requestUrl  = req.getPathInfo();
//		System.out.println("requestUrl : "+requestUrl);
		//main페이지 -> 제어변수 초기화 후 session에 저장
		//popup페이지 -> 제어변수 업데이트 다음달 ->+1  ,  이전달 -> -1
		//팝업창을 닫았을 때 날짜 값을 받고 session을 삭제하는 코드 추가해야함
		int monthControll  = 0;
		//최초 호출 
		if(requestUrl.equals("/call")) {
			req.getSession().setAttribute("monthControll", monthControll);
		Action action = new CalendarAction();
		action.execute(req, resp);
		//이전달 , 다음달 이동
		} else if(requestUrl.equals("/move")) {
			String moveMonth = req.getParameter("moveMonth");
			if(moveMonth != null && moveMonth.equals("next")) {
				monthControll = (Integer)req.getSession().getAttribute("monthControll");
				monthControll ++;
				req.getSession().setAttribute("monthControll", monthControll);
				Action action = new CalendarAction();
				action.execute(req, resp);
				
			} else if(moveMonth != null && moveMonth.equals("before")) {
				monthControll = (Integer)req.getSession().getAttribute("monthControll");
				monthControll --;
				req.getSession().setAttribute("monthControll", monthControll);
				Action action = new CalendarAction();
				action.execute(req, resp);
				
			} else if(req.getParameter("select") != null){
				String select = req.getParameter("select");
				System.out.println(select);
			}
			
		}
		
	}
}
