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
	
	 public ArrayList<Booking> rentalSchedule(Long roomId) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<Room> rentalList = new ArrayList<Room>();
			Booking booking = new Booking();
			
			try {
			
				rentalList.add(booking);
				}
			}catch(SQLException s) {
				s.printStackTrace();
			}
			return rentalList;
		}
}
