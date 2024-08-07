package com.mywebapp.controllers.user;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.actions.Action;
import com.mywebapp.actions.calendar.CalendarAction;
import com.mywebapp.actions.calendar.ReservationAvailablePeriodCallAction;
import com.mywebapp.model.Booking;
@WebServlet("/calendar/*")
public class CalendarController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("달력Controller 호출");
		String requestUrl  = req.getPathInfo();
		Action action;
//		System.out.println("requestUrl : "+requestUrl);
		//main페이지 -> 제어변수 초기화 후 session에 저장
		//popup페이지 -> 제어변수 업데이트 다음달 ->+1  ,  이전달 -> -1
		//팝업창을 닫았을 때 날짜 값을 받고 session을 삭제하는 코드 추가해야함
		int monthControll  = 0;
		//최초 호출 
		if(requestUrl.equals("/call")) {
			req.getSession().setAttribute("monthControll", monthControll);
		action = new CalendarAction();
		action.execute(req, resp);
		//이전달 , 다음달 이동
		} else if(requestUrl.equals("/move")) {
			String moveMonth = req.getParameter("moveMonth");
			if(moveMonth != null && moveMonth.equals("next")) {
				monthControll = (Integer)req.getSession().getAttribute("monthControll");
				monthControll ++;
				req.getSession().setAttribute("monthControll", monthControll);
				
				action = new CalendarAction();
				action.execute(req, resp);
				
			} else if(moveMonth != null && moveMonth.equals("before")) {
				monthControll = (Integer)req.getSession().getAttribute("monthControll");
				monthControll --;
				req.getSession().setAttribute("monthControll", monthControll);
			
				action = new CalendarAction();
				action.execute(req, resp);
				//날짜 버튼을 눌렀을 때 호출
			} else if(req.getParameter("selectDate") != null){
				req.getSession().setAttribute("selectDate", req.getParameter("selectDate"));
				action = new ReservationAvailablePeriodCallAction();
				action.execute(req, resp);
				//취소 버튼을 눌렀을 때
			} 
			//날짜 버튼을 누른 후 나오는 기간버튼을 누르면 호출
		} else if(requestUrl.equals("/select")) {
			//당일포함-> -1
			int selectPeriod = Integer.parseInt(req.getParameter("period"));
			String select = (String)req.getSession().getAttribute("selectDate");
			LocalDate selectStartDate = LocalDate.parse(select);
			//사용자가 선택한 기간 
			LocalDate selectEndDate = selectStartDate.plusDays(selectPeriod-1);
			req.getSession().setAttribute("selectEndDate", selectEndDate);
			req.setAttribute("datecheck", 1);
			action =new CalendarAction();
			action.execute(req, resp);
//			req.getRequestDispatcher("/test/popup.jsp").forward(req, resp);
			
		} else if(requestUrl.equals("/cancel")) {
			System.out.println("취소 버튼 ㄱ");
				req.getSession().removeAttribute("selectEndDate");
				action = new CalendarAction();
				action.execute(req, resp);
			
		}
		
	}
}
