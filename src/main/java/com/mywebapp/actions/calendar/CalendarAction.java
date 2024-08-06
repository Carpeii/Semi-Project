package com.mywebapp.actions.calendar;

import java.awt.PageAttributes;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.actions.Action;
import com.mywebapp.dao.BookingDaoImpl;
import com.mywebapp.model.Booking;


public class CalendarAction implements Action {
	private String calendarUrl = "/test/popup.jsp";
	LocalDate notSelectedDay;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int monthControll = 0;
		Booking b;
    	LocalDate checkIn = null;
    	LocalDate checkOut = null;
    	LocalDate in = null;
    	LocalDate out = null;
		if(req.getSession().getAttribute("monthControll") != null) {
			monthControll =(Integer)req.getSession().getAttribute("monthControll"); 
		}
		System.out.println(monthControll);
		System.out.println("달력Action 호출");
		
		Long roomId = 2l;
		//                                          ?
		//쿼리문 select * from booking where room_id =1 and curdate() <= check_out_date order by check_in_date asc;
		//booking table에서 가져온 값 
		BookingDaoImpl dao = new BookingDaoImpl();
		List<Booking> scheduleList = dao.rentalSchedule(roomId);
		for(Booking booking : scheduleList) {
			checkIn = booking.getCheckInDate().toLocalDate();
			checkOut = booking.getCheckOutDate().toLocalDate();
			System.out.println("Local : "+checkIn+"  "+checkOut);
		}		
				if(req.getParameter("select") != null) {
					String selectDate = req.getParameter("select");
					LocalDate selectDay =  LocalDate.parse(selectDate);
				}
				LocalDate today = null;
				int todayNum = 0;
				//최초 호출시 이번달 달력 + 이번달의 오늘날짜 마킹[]
				if(monthControll == 0) {
					today = LocalDate.now();
					todayNum = today.getDayOfMonth();
					//이전달 , 다음달 을 눌렀을 때 0이 되면 다시 이번달 -> 달력의 년 , 월을 바꾸는 컨트롤러 변수
				} else if(monthControll != 0) {
					today = LocalDate.now().plusMonths(monthControll);
				}
				//이번달의 첫날
				LocalDate firstDay = today.withDayOfMonth(1);
				int firstDayOfWeek = firstDay.getDayOfWeek().getValue();
				//이번달의 총 일 수 , 이번달의 마지막 날
				int endDay = today.lengthOfMonth();
				StringBuilder sb = new StringBuilder();
				 
				try {
//				System.out.println("오늘 :" + today );
//				System.out.println("이번달 오늘 일자 : "+ todayNum);
//				System.out.println("이번달의 첫 날: "+ firstDay);
//				System.out.println("이번달의 첫 날의 요일 : "+ firstDayOfWeek);
//				System.out.println("");
//				System.out.println("이번달의 마지막 날 : "+ endDay);
				int year = today.getYear();
				int month = today.getMonthValue();
					
//				 sb.append("<table border='1' cellspacing='1' width='300px' height='400px' style='text-align: center'>\n");
				 sb.append("<table class='table calendar-table'>\n");
				 sb.append("<tr><th colspan='7'>"+" <button value=\"before\" name=\"moveMonth\">&lt</button>   "+year+"-"+month+"   <button value=\"next\" name=\"moveMonth\">&gt</button>"+"</th></tr>\n");
			        // 요일 헤더
				 
			        sb.append("<tr>\n");
			        sb.append("<th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th><th>일</th>\n");
			        sb.append("</tr>\n");
			        
			        // 날짜 행
			        sb.append("<tr>\n");

			        // 빈 칸 추가 (첫째 주의 시작일까지)
			        for (int i = 1; i < firstDayOfWeek; i++) {
			            sb.append("<td class='disabled'> </td>\n");
			        }

			        int i = 0;
			        int j = 1;
			        if(!scheduleList.isEmpty()) {
				         b = scheduleList.get(0);
				         checkIn = b.getCheckInDate().toLocalDate();
				         checkOut = b.getCheckOutDate().toLocalDate();
			        }
			        // 날짜 추가
			        System.out.println("109-------------do while 시작-------------------------------------------------------");
			        System.out.println("-------------do while 시작-------------------------------------------------------");
			        System.out.println("-------------do while 시작-------------------------------------------------------");
			        System.out.println("-------------in/out리스트 반복 시작---------------");
			        do{	
			        	
			        	if(!scheduleList.isEmpty()) {
			        	b = scheduleList.get(i);
			        	checkIn = b.getCheckInDate().toLocalDate();
			        	checkOut = b.getCheckOutDate().toLocalDate();
			        	}
			        	
			        	System.out.println("121-------------날짜 찍기while 시작---------------");
				      	while ( j <= endDay) {
				      		notSelectedDay = LocalDate.of(today.getYear(),today.getMonthValue(),j);
				        	System.out.println("현재 날짜 : " + notSelectedDay);
				        	//true일시 마킹
				        	System.out.println("리스트의 인덱스 번째 i : " + i);
				        	System.out.println("현재일 j : " + j);
				        	//마킹을 in out날짜도 포함시킴
				        	if(!scheduleList.isEmpty()) {
				        	in = checkIn.plusDays(-1);
				        	out = checkOut.plusDays(1);
				        	}
				        	 System.out.println("131-------------if분기---------------");
				        	 System.out.println("(132in.isBefore(notSelectedDay) && out.isAfter(notSelectedDay))");
				        	 // 체크인 날짜보다 높으면서 체크아웃날짜와 년 월이 다를때
				        	 System.out.println("134---------if-----------------");
				        	 //Local : 2024-12-27  2025-01-15
				        	 if(!scheduleList.isEmpty()) {
					        	 while(
					        			 (i < (scheduleList.size()-1) &&
					        					 notSelectedDay.getYear() > checkOut.getYear()) 
					        			   || 
					        		       (notSelectedDay.getYear() == checkOut.getYear() && 
					        		       notSelectedDay.getMonthValue() > checkOut.getMonthValue())
					        		       && 
					        		       i < (scheduleList.size()-1)) {
					        		 	System.out.println("i값 : "+i);
					        		 	System.out.println(scheduleList.size());
					        		 System.out.println("while true   i++");
					        		 i++;
					        		 b = scheduleList.get(i);
					        		 checkIn = b.getCheckInDate().toLocalDate();
							        	checkOut = b.getCheckOutDate().toLocalDate();
							        	in = checkIn.plusDays(-1);
							        	out = checkOut.plusDays(1);
					        		 System.out.println(i);
					        		 
					        	 }
				        	 }
				        	 //true -> 이미 예약된 날짜
				        	 //false -> 예약 가능한 날짜
				        	 //예약 날짜가 비어있는지 검사 -> 비어있다면? 그냥 달력 ㄱ
				        	if(scheduleList.isEmpty()) {
				        		System.out.println("158-------------false---------------");
				        		if(todayNum == j) {
				        			sb.append("<td><button type='submit' name='select' class='w-100 h-100 btn btn-outline-primary' style'border: none;' value='"+notSelectedDay+"'>");
				        			sb.append("["+j+"]").append("</button></td>\n");
				        		} else {
				        			sb.append("<td><button type='submit' name='select' class='w-100 h-100 btn btn-outline-primary' style'border: none;' value='"+notSelectedDay+"'>");
				        			sb.append(j).append("</button></td>\n");
				        		}
				        	} else if((in.isBefore(notSelectedDay) && out.isAfter(notSelectedDay))) {
				        		System.out.println("150-------------true---------------");
				        		if(todayNum == j) {
				        			sb.append("<td class='disabled bg-light'>").append("["+j+"]").append("</td>\n");
				        		}else {
					        		sb.append("<td class='disabled bg-light'>").append(j).append("</td>\n");
					        	}
				        		
				        	}
				            
				            // 일요일에 줄 바꿈
				            if ((j + firstDayOfWeek - 1) % 7 == 0) {
				                sb.append("</tr>\n<tr>\n");
				            }
				            if(!scheduleList.isEmpty()) {
				            	
					            if(notSelectedDay.isEqual(checkOut)) {
					            	System.out.println("171if문  notSelectedDay.isEqual(checkOut) ");
					            	 System.out.println("-------------if문true---------------");
					            	 System.out.println("i++");
					            	i++;
					            	System.out.println("i : " + i);
					            	
					            	if(i < scheduleList.size()) {
					            		System.out.println("178if문     i < scheduleList.size()      ");
					            		 System.out.println("179-------------if문true ---------------");
					            		 b = scheduleList.get(i);
								         checkIn = b.getCheckInDate().toLocalDate();
								         checkOut = b.getCheckOutDate().toLocalDate();
					            	}
					            }
				            }
				            System.out.println("j++");
				            j++;
				        }
				        	
				      	
				      	if(!scheduleList.isEmpty()) {
				      		System.out.println("190-------------if문---------------");
				      		System.out.println("191    !scheduleList.isEmpty()    ");
				      		System.out.println("i++");
				      		i++;
				      	}
				      	
			        }while(i < scheduleList.size());
			        System.out.println("197-------------do while 끝 ---------------");
			        
			        
			        // 마지막 행의 끝을 추가
			        	int tableEnd = notSelectedDay.getDayOfWeek().getValue();
			        	for (int x = tableEnd; x <=6; x++) {
			        		sb.append("<td class='disabled'> </td>\n");
			        	}
			        sb.append("</tr>\n");
			        
			        // 테이블의 끝
			        sb.append("</table>\n");
			        	req.getSession().setAttribute("sb", sb);
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