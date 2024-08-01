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

public class CalendarAction implements Action {
	private String calendarUrl = "/test/popup.jsp";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int monthControll = 0;
		monthControll =(Integer)req.getSession().getAttribute("monthControll"); 
		System.out.println(monthControll);
		System.out.println("달력Action 호출");
		Long roomId = 1l;
		
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
				System.out.println("오늘 :" + today );
				System.out.println("이번달 오늘 일자 : "+ todayNum);
				System.out.println("이번달의 첫 날: "+ firstDay);
				System.out.println("이번달의 첫 날의 요일 : "+ firstDayOfWeek);
				System.out.println("");
				System.out.println("이번달의 마지막 날 : "+ endDay);
				int year = today.getYear();
				int month = today.getMonthValue();
					
				 sb.append("<table border='1' cellspacing='1' width='300px' height='400px' style='text-align: center'>\n");
				 sb.append("<div>");
				 sb.append(year+"-"+month);
				 sb.append("</div>");
			        
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

			        	// calendar브런치에서 진행
			        	//notSelectedDay = 한달의 날짜를 하나씩 순서대로 반환 연 월도 같이 받아옴 다음달이나 다음년도로 이어지는 계약도 ㄱㄴ
			        	//db에서 체크인 날짜를 정렬해서 받아옴
			        	//받아오는 값은 (In)localDate~(Out)localDate 범위를 가진 날짜의 배열 list임
			        	//단순하게 In < 선택 ㄱㄴ < out 하고싶어도  계약 건수가  복수임
			        	//if(b[0].In < (i)선택 ㄱㄴ < b.[0]out) 로 찍고  i의 값이 out과 같아지면 Booking b의 인덱스 값을 하나 올리기?
			        	//이중 for문으로 가능할듯함
			        
			        /* calendarVer2브런치에서 진행
			         * db에서 받은 리스트의 값을 비교할 떄 LocalDate.is 메소드 말고 
			         * check.in/out 필드의 년 월 일 을 하나씩 추출해서 비교하고
			         * 조건이 true일 경우 정수형 변수에 일의 수를 받아서 비교? - >(같은 달에 in out이 있다면 가능하지만 두 날짜의 차이가 달을 넘어가면 비교가 더 복잡할듯
			         * 
			         *  년 월의 값이 차이나는 경우
			         * 년 월이 차이나는 경우 else if로 중첩으로 검사? 
			         * -> X 년 월이 차이나면 해당 달의 날짜는 모두 마킹하면됨 년이나 월의 값이 다르다면 별다른 조건없이 마킹하는 반복문을 진행하면될듯
			         * 
			         * 년 월이 같은 경우 -> 같은 달에 in out이 있는 경우
			         * 
			         * */
			        
			        
			        
			        
			        int i = 0;
			        int j = 1;
			        Booking b = scheduleList.get(0);
			        LocalDate checkIn = b.getCheckInDate().toLocalDate();
			        LocalDate checkOut = b.getCheckOutDate().toLocalDate();
			        // 날짜 추가
			        do{	
			        	if(!scheduleList.isEmpty()) {
			        	b = scheduleList.get(i);
			        	checkIn = b.getCheckInDate().toLocalDate();
			        	checkOut = b.getCheckOutDate().toLocalDate();
			        	}
				      	while ( j <= endDay) {
				      		LocalDate notSelectedDay = LocalDate.of(today.getYear(),today.getMonthValue(),j);
				        	System.out.println("한달의 날짜 값 얻기 : " + notSelectedDay);
				        	//true일시 마킹
				        	System.out.println("i : " + i);
				        	System.out.println("j : " + j);
				        	LocalDate in = checkIn.plusDays(-1);
				        	LocalDate out = checkOut.plusDays(1);
				        	if((in.isBefore(notSelectedDay) && out.isAfter(notSelectedDay))) {
				        		
				        		
				        		if(todayNum == j) {
				        			sb.append("<td>").append("[m"+j+"]").append("</td>\n");
				        		}else {
					        		sb.append("<td>").append("m"+j).append("</td>\n");
					        	}
				        		
				        	} else {
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
				            	i++;
				            	System.out.println("i : " + i);
				            	if(i < scheduleList.size()) {
				            		 b = scheduleList.get(i);
							         checkIn = b.getCheckInDate().toLocalDate();
							         checkOut = b.getCheckOutDate().toLocalDate();
				            	}
				            }
				            j++;
				        }
				      	
				      	if(!scheduleList.isEmpty()) {
				      		i++;
				      		System.out.println("aaaaaaaaa");
				      	}
			        }while(i < scheduleList.size());
			        
			        
			        
			        // 마지막 행의 끝을 추가
			        	if ((endDay + firstDayOfWeek - 1) % 7 != 0) {
			        		sb.append("<td> </td>\n");
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

