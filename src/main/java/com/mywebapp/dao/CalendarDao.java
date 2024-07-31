package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mywebapp.model.Booking;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomOption;
import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;

public class CalendarDao {
	private Connection connection;
	//달력을 호출 할 때 이미 계약된 날짜를 검색 
	 public ArrayList<Booking> rentalSchedule(Long roomId) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<Booking> scheduleList = new ArrayList<Booking>();
			
			try {
				connection = JdbcUtil.getCon();
				
				String sql = "select * from booking where room_id = ? and curdate() <= check_out_date";
				pstmt = connection.prepareStatement(sql);
				pstmt.setLong(1,roomId);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Booking booking = new Booking(
							rs.getDate("check_in_date"),
							rs.getDate("check_out_date"));
					scheduleList.add(booking);
				}
			}catch(SQLException s) {
				s.printStackTrace();
			}
			return scheduleList;
		}
}
