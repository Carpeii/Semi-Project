package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mywebapp.dto.RoomDTO;

import db.JdbcUtil;

public class RoomDao {
	public ArrayList<RoomDTO> list() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = JdbcUtil.getCon();
			String sql = "select room_name, jibun_address, street_address from room";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<RoomDTO> roomList = new ArrayList<RoomDTO>();
			while (rs.next()) {
				String roomName = rs.getString("room_name");
				String jibunAddress = rs.getString("jibun_address");
				String streetAddress = rs.getString("street_address");
				RoomDTO room = new RoomDTO();
				room.setRoomName(roomName);
				room.setJibunAddress(jibunAddress);
				room.setStreetAddress(streetAddress);
				roomList.add(room);
			}
			return roomList;
		}catch(SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}

}
