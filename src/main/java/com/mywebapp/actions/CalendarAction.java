package com.mywebapp.actions;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalendarAction implements Action {
	private String calendarUrl = "/test/popup.jsp";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("달력Action 호출");
		String test = "test";
				//달력의 오늘 날짜
				LocalDate today = LocalDate.now();
				//요일
				DayOfWeek dayOfWeek = today.getDayOfWeek();
				int dayOfWeekNum = today.getDayOfWeek().getValue();
				//이번달의 첫날
				LocalDate firstDay = today.withDayOfMonth(1);
				int firstDayOfWeek = firstDay.getDayOfWeek().getValue();
				//이번달의 총 일 수 , 이번달의 마지막 날
				int endDay = today.getDayOfMonth();
				StringBuilder sb = new StringBuilder();
				
				try {
//				System.out.println("오늘 :" + today );
//				System.out.println("요일 : "+ dayOfWeek+dayOfWeekNum);
//				System.out.println("이번달의 첫 날: "+ firstDay);
//				System.out.println("이번달의 첫 날의 요일 : "+ firstDayOfWeek);
//				System.out.println("");
//				System.out.println("이번달의 마지막 날 : "+ endDay);
//				
//				System.out.println();
//				System.out.println();
				 sb.append("<table border='1' width='400' height='600'>\n");
			        
			        // 요일 헤더
			        sb.append("<tr>\n");
			        sb.append("<th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th><th>일</th>\n");
			        sb.append("</tr>\n");
			        
			        // 날짜 행
			        sb.append("<tr>\n");

			        // 빈 칸 추가 (첫째 주의 시작일까지)
			        for (int i = 1; i < firstDayOfWeek; i++) {
			            sb.append("<td> </td>\n");
			        }

			        // 날짜 추가
			        for (int i = 1; i <= endDay; i++) {
			            sb.append("<td>").append(i).append("</td>\n");
			            
			            // 일요일에 줄 바꿈
			            if ((i + firstDayOfWeek - 1) % 7 == 0) {
			                sb.append("</tr>\n<tr>\n");
			            }
			        }

			        // 마지막 행의 끝을 추가
			        if ((endDay + firstDayOfWeek - 1) % 7 != 0) {
			            sb.append("</tr>\n");
			        }
			        
			        // 테이블의 끝
			        sb.append("</table>\n");
			        
			        	req.setAttribute("sb", sb);
			        	req.setAttribute("test", test);
			        	
						req.getRequestDispatcher(calendarUrl).forward(req, resp);
						
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	}

}
