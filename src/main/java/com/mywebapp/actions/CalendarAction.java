package com.mywebapp.actions;

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

import com.mywebapp.dao.BookingDaoImpl;
import com.mywebapp.model.Booking;

/*
 * 테스트로 쓴 더미 값
insert into booking values(0, 1 , 1 , '2024-7-15' , '2024-7-21' ,0);
insert into booking values(0, 1 , 1 , '2024-7-29' , '2024-8-13' ,0);
insert into booking values(0, 1 , 1 , '2024-8-14' , '2024-8-20' ,0);
insert into booking values(0, 1 , 1 , '2024-8-29' , '2024-9-5' ,0);
insert into booking values(0, 1 , 1 , '2024-11-1' , '2024-11-15' ,0);
insert into booking values(0, 1 , 1 , '2024-12-27' , '2025-1-15' ,0);
 * */
public class CalendarAction implements Action {
	private String calendarUrl = "/test/popup.jsp";
	LocalDate notSelectedDay;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int monthControll = 0;
		monthControll =(Integer)req.getSession().getAttribute("monthControll"); 
		System.out.println(monthControll);
		System.out.println("달력Action 호출");
		Long roomId = 1l;
		//                                          ?
		//쿼리문 select * from booking where room_id =1 and curdate() <= check_out_date order by check_in_date asc;
		//booking table에서 가져온 값 
		BookingDaoImpl dao = new BookingDaoImpl();
		List<Booking> scheduleList = dao.rentalSchedule(roomId);
		for(Booking b : scheduleList) {
			LocalDate checkIn = b.getCheckInDate().toLocalDate();
			LocalDate checkOut = b.getCheckOutDate().toLocalDate();
			System.out.println("Local : "+checkIn+"  "+checkOut);
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
				 sb.append("<tr><th colspan='7'>"+year+"-"+month+"</th></tr>\n");
			        
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

			        
			        	//notSelectedDay = 한달의 날짜를 하나씩 순서대로 반환 연 월도 같이 받아옴 다음달이나 다음년도로 이어지는 계약도 ㄱㄴ
			        	//db에서 체크인 날짜를 정렬해서 받아옴
			        	//받아오는 값은 (In)localDate~(Out)localDate 범위를 가진 날짜의 배열 list임
			        	//단순하게 In < 선택 ㄱㄴ < out 하고싶어도  계약 건수가  복수임
			        	//if(b[0].In < (i)선택 ㄱㄴ < b.[0]out) 로 찍고  i의 값이 out과 같아지면 Booking b의 인덱스 값을 하나 올리기?
			        	//이중 for문으로 가능할듯함
			        /*
			        문제  if((in.isBefore(notSelectedDay) && out.isAfter(notSelectedDay)))
			       	조건을 검사하고 사이값이 아니면 i의 값을 올려줘야 하는데 해당 로직이 없음 
			       	->db에서 가져온 list의 0번째 인덱스만 참조중
			       	달을 넘기면 무조건 false를 반환
			        
			        */
			        
			        
			        int i = 0;
			        int j = 1;
			        Booking b = scheduleList.get(0);
			        LocalDate checkIn = b.getCheckInDate().toLocalDate();
			        LocalDate checkOut = b.getCheckOutDate().toLocalDate();
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
				        	LocalDate in = checkIn.plusDays(-1);
				        	LocalDate out = checkOut.plusDays(1);
				        	 System.out.println("131-------------if분기---------------");
				        	 System.out.println("(132in.isBefore(notSelectedDay) && out.isAfter(notSelectedDay))");
				        	 // 체크인 날짜보다 높으면서 체크아웃날짜와 년 월이 다를때
				        	 System.out.println("134---------if-----------------");
				        	 //Local : 2024-12-27  2025-01-15
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
				        	 //true -> 이미 예약된 날짜
				        	 //false -> 예약 가능한 날짜
				        	if((in.isBefore(notSelectedDay) && out.isAfter(notSelectedDay))) {
				        		System.out.println("150-------------true---------------");
				        		if(todayNum == j) {
				        			sb.append("<td class='disabled bg-light'>").append("["+j+"]").append("</td>\n");
				        		}else {
					        		sb.append("<td class='disabled bg-light'>").append(j).append("</td>\n");
					        	}
				        		
				        	} else {
				        		System.out.println("158-------------false---------------");
				        		if(todayNum == j) {
				        			sb.append("<td>").append("["+j+"]").append("</td>\n");
				        		} else {
				        			sb.append("<td>").append(j).append("</td>\n");
				        		}
				        	}
				            
				            // 일요일에 줄 바꿈
				            if ((j + firstDayOfWeek - 1) % 7 == 0) {
				                sb.append("</tr>\n<tr>\n");
				            }
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
			        
			        	req.setAttribute("sb", sb);
			        	
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

//do{	
//	
//	if(!scheduleList.isEmpty()) {
//	b = scheduleList.get(i);
//	checkIn = b.getCheckInDate().toLocalDate();
//	checkOut = b.getCheckOutDate().toLocalDate();
//	}
//  	while ( j <= endDay) {
//    	 while((notSelectedDay.getYear() > checkOut.getYear()) || 
//    		       (notSelectedDay.getYear() == checkOut.getYear() && 
//    		       notSelectedDay.getMonthValue() > checkOut.getMonthValue())) {
//    		 i++;
//    	 }
//    	if((in.isBefore(notSelectedDay) && out.isAfter(notSelectedDay))) {
//    		if(todayNum == j) {
//    		}else {
//        	}
//    		
//    	} else {
//    		if(todayNum == j) {
//    		} else {
//    		}
//    	}
//        
//        if ((j + firstDayOfWeek - 1) % 7 == 0) {
//        }
//        if(notSelectedDay.isEqual(checkOut)) {
//        	i++;
//        	if(i < scheduleList.size()) {
//        		 b = scheduleList.get(i);
//		         checkIn = b.getCheckInDate().toLocalDate();
//		         checkOut = b.getCheckOutDate().toLocalDate();
//        	}
//        }
//        System.out.println("j++");
//        j++;
//    }
//  	if(!scheduleList.isEmpty()) {
//  		i++;
//  	}
//  	
//}while(i < scheduleList.size());
