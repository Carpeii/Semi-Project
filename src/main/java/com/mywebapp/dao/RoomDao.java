package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

    private Connection connection;

    public RoomDao() {}
    public RoomDao(Connection connection) {
        this.connection = connection;
    }

//    public List<Room> findAll() throws SQLException {
//        List<Room> rooms = new ArrayList<>();
//        String sql = "SELECT * FROM room";
//        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                rooms.add(new Room(
//                        rs.getLong("id"),
//                        rs.getString("host_id"),
//                        rs.getString("jibun_address"),
//                        rs.getString("street_address"),
//                        rs.getInt("floor"),
//                        rs.getInt("usable_area"),
//                        rs.getInt("room_count"),
//                        rs.getInt("living_room_count"),
//                        rs.getInt("toilet_count"),
//                        rs.getInt("kitchen_count"),
//                        rs.getBoolean("duplex"),
//                        rs.getBoolean("elevator"),
//                        rs.getBoolean("park"),
//                        rs.getString("park_detail"),
//                        rs.getInt("room_type"),
//                        rs.getInt("minimum_contract"),
//                        rs.getInt("approve"),
//                        new ArrayList<>(), // Placeholder for roomImageList
//                        null, // Placeholder for roomOption
//                        null // Placeholder for roomPrice
//                ));
//            }
//        }
//        return rooms;
//    }
    
    //승인여부도 확인해야됨 applove -> 1정상
    public ArrayList<Room> getRoomList(String searchWord,int viewRecord) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Room> rooms = new ArrayList<Room>();
		try {
			connection = JdbcUtil.getCon();
			String sql = "select distinct * from room r "
					+"LEFT JOIN room_price rp ON r.id = rp.room_id "
					+"where (r.approve=1) "
					+"and (r.jibun_address like ? "
					+"or r.street_address like ? "
					+"or r.room_name like ?) "
					+"order by r.id desc "
					+"limit 15 offset ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,"%"+searchWord+"%");
			pstmt.setString(2,"%"+searchWord+"%");
			pstmt.setString(3,"%"+searchWord+"%");
			pstmt.setInt(4,viewRecord);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				Room room = new Room(rs.getLong("id"),rs.getString("host_id"),
						rs.getString("room_name"),rs.getString("jibun_address"),
						rs.getString("street_address"),rs.getString("address_detail"),
						rs.getInt("floor"),rs.getInt(" usable_area"),
						rs.getInt("room_count"),rs.getInt("living_room_count"),
						rs.getInt("toilet_count"),rs.getInt("kitchen_count"),
						rs.getBoolean("duplex"),rs.getBoolean("elevator"),
						rs.getBoolean("park"),rs.getString("park_detail"),
						rs.getInt("room_type"),rs.getInt("minimum_contract"),
						rs.getInt("approve"),
						
						null,
						null,
						new RoomPrice(
								rs.getLong("room_id"),rs.getInt("rent_price"),
								rs.getInt("long_term_discount"),rs.getInt("early_check_in_discount")
								)
						);
				
				
				rooms.add(room);
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return rooms;
	}
    
    public int searchTotalRecord(String searchWord) {
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalRecord = 0;
		try {
			connection = JdbcUtil.getCon();
			String sql = "SELECT COUNT(*) totalrecord "
	    	+"FROM room r "
	    	+"LEFT JOIN room_price rp ON r.id = rp.room_id " 
	    	+"WHERE r.approve = 1 "
	    	  +"AND (r.jibun_address LIKE ? "
	    	       +"OR r.street_address LIKE ? "
	    	       +"OR r.room_name LIKE ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,"%"+searchWord+"%");
			pstmt.setString(2,"%"+searchWord+"%");
			pstmt.setString(3,"%"+searchWord+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalRecord = rs.getInt("totalrecord");
			}
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return totalRecord;
	}
}

