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
	private long roomId;
	private LocalDate notSelectedDay;
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		roomId = Long.parseLong(req.getParameter("roomId"));
		int monthControll = 0;
		Booking b;
    	LocalDate checkIn = null;
    	LocalDate checkOut = null;
    	LocalDate in = null;
    	LocalDate out = null;
    	LocalDate selectStartDate = null;
    	LocalDate selectEndDate = null;
		if(req.getSession().getAttribute("monthControll") != null) {
			monthControll =(Integer)req.getSession().getAttribute("monthControll"); 
		}
		if(req.getSession().getAttribute("selectDate") != null && req.getSession().getAttribute("selectEndDate") != null ) {
			String select = (String)req.getSession().getAttribute("selectDate");
			selectStartDate = LocalDate.parse(select);
			
			selectEndDate =  (LocalDate)req.getSession().getAttribute("selectEndDate");
			
		}
		
		//                                          ?
		//쿼리문 select * from booking where room_id =1 and curdate() <= check_out_date order by check_in_date asc;
		//booking table에서 가져온 값 
		BookingDaoImpl dao = new BookingDaoImpl();
		List<Booking> scheduleList = dao.rentalSchedule(roomId);
		for(Booking booking : scheduleList) {
			checkIn = booking.getCheckInDate().toLocalDate();
			checkOut = booking.getCheckOutDate().toLocalDate();
		}		
				LocalDate today = null;
				LocalDate day = LocalDate.now();
				//최초 호출시 이번달 달력 + 이번달의 오늘날짜 마킹[]
				if(monthControll == 0) {
					today = LocalDate.now();
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
				 sb.append("<tr><th colspan='7'>"+" <button class='btn' value=\"before\" name=\"moveMonth\">&lt</button>   "+year+"-"+month+"   <button class='btn' value=\"next\" name=\"moveMonth\">&gt</button>"+"</th></tr>\n");
			        // 요일 헤더
				 	//월 화 수 목 금 토 일
				 	//1 2  3 4  5 6 7
				 //->
				 //일 월 화 수 목 금 토
				 // 1 2 3 4 5 6 7 
				 //if (1=<요일)   +1
				 //if (7==요일) 요일 = 1
			        sb.append("<tr>\n");
			        sb.append("<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>\n");
			        sb.append("</tr>\n");
			        
			        // 날짜 행
			        sb.append("<tr>\n");

			        // 빈 칸 추가 (첫째 주의 시작일까지)
			        int calendarBlank = 0;
			        int calendarBlankDay = firstDay.minusDays(1).getDayOfMonth()-firstDayOfWeek+1;
			        if(firstDayOfWeek<=6) {
			        	calendarBlank = firstDayOfWeek+1; 
			        } else if(firstDayOfWeek == 7) {
			        	calendarBlank = 1;
			        }
			        for (int i = 1; i < calendarBlank; i++,calendarBlankDay++) {
			            sb.append("<td class='text-secondary'>"+calendarBlankDay+" </td>\n");
			        }

			        int i = 0;
			        int j = 1;
			        if(!scheduleList.isEmpty()) {
				         b = scheduleList.get(0);
				         checkIn = b.getCheckInDate().toLocalDate();
				         checkOut = b.getCheckOutDate().toLocalDate();
			        }
			        // 날짜 추가
			        do{	
			        	
			        	if(!scheduleList.isEmpty()) {
			        	b = scheduleList.get(i);
			        	checkIn = b.getCheckInDate().toLocalDate();
			        	checkOut = b.getCheckOutDate().toLocalDate();
			        	}
			        	
				      	while ( j <= endDay) {
				      		notSelectedDay = LocalDate.of(today.getYear(),today.getMonthValue(),j);
				        	//true일시 마킹
				        	//마킹을 in out날짜도 포함시킴
				        	if(!scheduleList.isEmpty()) {
				        	in = checkIn.plusDays(-1);
				        	out = checkOut.plusDays(1);
				        	}
				        	 // 체크인 날짜보다 높으면서 체크아웃날짜와 년 월이 다를때
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
					        		 i++;
					        		 b = scheduleList.get(i);
					        		 checkIn = b.getCheckInDate().toLocalDate();
							        	checkOut = b.getCheckOutDate().toLocalDate();
							        	in = checkIn.plusDays(-1);
							        	out = checkOut.plusDays(1);
					        		 
					        	 }
				        	 }
				        	 //true -> 이미 예약된 날짜
				        	 //false -> 예약 가능한 날짜
				        	 //예약스케쥴이 없을 경우
				        	if(scheduleList.isEmpty()) {
					        		//오늘 이전임?
				        		if((monthControll == 0 && notSelectedDay.getDayOfMonth() < today.getDayOfMonth() )  || monthControll < 0 ) {
				        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-outline-secondary' style'border: none;' value='"+notSelectedDay+"' disabled>");
				        			sb.append(j).append("</button></td>\n");
				        			
				        			
				        			//사용자가 선택한날임?
				        		} else if((selectStartDate != null && selectEndDate != null) &&
			        					(notSelectedDay.isAfter(selectStartDate.minusDays(1)) &&notSelectedDay.isBefore(selectEndDate.plusDays(1)))) {
				        			if(day.isEqual(notSelectedDay)) {
				        				sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-success' style'border: none;' value='"+notSelectedDay+"'>");
					        			sb.append("["+j+"]").append("</button></td>\n");
					        		} else if(notSelectedDay.isEqual(selectStartDate)) {
					        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-success' style'border: none;' value='"+notSelectedDay+"'>");
					        			sb.append(j).append("</button></td>\n");
					        		}else if(notSelectedDay.isEqual(selectEndDate)) {
					        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-success' style'border: none;' value='"+notSelectedDay+"'>");
					        			sb.append(j).append("</button></td>\n");
					        		} else {
					        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-outline-success' style'border: none;' value='"+notSelectedDay+"'>");
					        			sb.append(j).append("</button></td>\n");
					        		}
				        			
				        			//예약하려고 누르기만 한 날 -> 현재 controller에서 날짜를 눌렀을 때 ReservationAvailablePeriodCallAction 메소드를 호출해서 CalendarAction이 호출이 안됨
				        			// 두 개의 메소드를 같이 호출할 방법이 생기면 사용
				        			//세션에 담으면 가능할듯 ㅎ;
//				        		} else if(selectStartDate != null && notSelectedDay.equals(selectStartDate)) {
//				        			if(day.isEqual(notSelectedDay)) {
//				        				sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-primary' style'border: none;' value='"+notSelectedDay+"'>");
//					        			sb.append("["+j+"]").append("</button></td>\n");
//					        		} else {
//					        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-primary' style'border: none;' value='"+notSelectedDay+"'>");
//					        			sb.append(j).append("</button></td>\n");
//					        		}
				        			
				        			//아무것도 아니면 선택 가능한 날
				        		} else {
				        			if(day.isEqual(notSelectedDay)) {
				        				sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-outline-primary' style'border: none;' value='"+notSelectedDay+"'>");
				        				sb.append("["+j+"]").append("</button></td>\n");
				        			} else {
				        				sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-outline-primary' style'border: none;' value='"+notSelectedDay+"'>");
				        				sb.append(j).append("</button></td>\n");
				        			}
				        		}
			        		
				        	}
				        	//예약스케쥴에 예약이 있는 경우------------------------------------------------------------------
				        	if(!scheduleList.isEmpty()) {
				        		
				        		//예약중인 날임?
				        		if((in.isBefore(notSelectedDay) && out.isAfter(notSelectedDay))) {
				        			//오늘임?
					        		if(day.isEqual(notSelectedDay)) {
					        			sb.append("<td class='disabled bg-light'>").append("["+j+"]").append("</td>\n");
					        		} else {
					        			sb.append("<td class='disabled bg-light'>").append(j).append("</td>\n");
					        		}
					        		
					        		
						        	//오늘 이전임?
				        		} else if((monthControll == 0 && notSelectedDay.getDayOfMonth() < today.getDayOfMonth() )  || monthControll < 0 ) {
				        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-outline-secondary' style'border: none;' value='"+notSelectedDay+"' disabled>");
				        			sb.append(j).append("</button></td>\n");
				        			
				        			
				        			//사용자가 선택한날임?
				        		} else if((selectStartDate != null && selectEndDate != null) &&
			        					(notSelectedDay.isAfter(selectStartDate.minusDays(1)) &&notSelectedDay.isBefore(selectEndDate.plusDays(1)))) {
				        			
				        			if(day.isEqual(notSelectedDay)) {
				        				sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-success' style'border: none;' value='"+notSelectedDay+"'>");
					        			sb.append("["+j+"]").append("</button></td>\n");
					        		} else if(notSelectedDay.isEqual(selectStartDate)) {
					        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-success' style'border: none;' value='"+notSelectedDay+"'>");
					        			sb.append(j).append("</button></td>\n");
					        		}else if(notSelectedDay.isEqual(selectEndDate)) {
					        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-success' style'border: none;' value='"+notSelectedDay+"'>");
					        			sb.append(j).append("</button></td>\n");
					        		} else {
					        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-outline-success' style'border: none;' value='"+notSelectedDay+"'>");
					        			sb.append(j).append("</button></td>\n");
					        		}
				        			
				        			//예약하려고 누르기만 한 날 -> 현재 controller에서 날짜를 눌렀을 때 ReservationAvailablePeriodCallAction 메소드를 호출해서 CalendarAction이 호출이 안됨
				        			// 두 개의 메소드를 같이 호출할 방법이 생기면 사용
//				        		} else if(selectStartDate != null && notSelectedDay.equals(selectStartDate)) {
//				        			if(day.isEqual(notSelectedDay)) {
//				        				sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-primary' style'border: none;' value='"+notSelectedDay+"'>");
//					        			sb.append("["+j+"]").append("</button></td>\n");
//					        		} else {
//					        			sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-primary' style'border: none;' value='"+notSelectedDay+"'>");
//					        			sb.append(j).append("</button></td>\n");
//					        		}
				        			
				        			//아무것도 아니면 선택 가능한 날
				        		} else {
				        			if(day.isEqual(notSelectedDay)) {
				        				sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-outline-primary' style'border: none;' value='"+notSelectedDay+"'>");
				        				sb.append("["+j+"]").append("</button></td>\n");
				        			} else {
				        				sb.append("<td><button type='submit' name='selectDate' class='w-100 h-100 btn btn-outline-primary' style'border: none;' value='"+notSelectedDay+"'>");
				        				sb.append(j).append("</button></td>\n");
				        			}
				        		}
				        		
				        	}
				            
				            // 토요일에 줄 바꿈
				            if ((j + calendarBlank - 1) % 7 == 0) {
				                sb.append("</tr>\n<tr>\n");
				            }
				            if(!scheduleList.isEmpty()) {
				            	
					            if(notSelectedDay.isEqual(checkOut)) {
					            	i++;
					            	
					            	if(i < scheduleList.size()) {
					            		 b = scheduleList.get(i);
								         checkIn = b.getCheckInDate().toLocalDate();
								         checkOut = b.getCheckOutDate().toLocalDate();
					            	}
					            }
				            }
				            j++;
				        }
				        	
				      	
				      	if(!scheduleList.isEmpty()) {
				      		i++;
				      	}
				      	
			        }while(i < scheduleList.size());
			        
			        
			        // 마지막 행의 끝을 추가
			        //추가!
			        if(notSelectedDay.getDayOfWeek().getValue()<=6) {
			        	calendarBlank = notSelectedDay.getDayOfWeek().getValue()+1; 
			        } else if(notSelectedDay.getDayOfWeek().getValue() == 7) {
			        	calendarBlank = 1;
			        }
			        calendarBlankDay = notSelectedDay.withDayOfMonth(1).getDayOfMonth();
			        	for (int x = calendarBlank; x <=6; x++,calendarBlankDay++) {
			        		sb.append("<td class='text-secondary'>"+calendarBlankDay+" </td>\n");
			        	}
			        sb.append("</tr>\n");
			        
			        //행 수가 5줄일 때 한줄 추가
			   
			        	
			         if(notSelectedDay.getDayOfWeek().getValue() == 6) {
			        	sb.append("<tr>\n");
			        	for (int x = 0; x <=6; x++,calendarBlankDay++) {
			        		sb.append("<td class='h-100 text-secondary'>"+calendarBlankDay+" </td>\n");
			        	} 
			        	sb.append("</tr>\n");
			        }
			        
			        
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