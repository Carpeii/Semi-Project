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

    //승인여부도 확인해야됨 applove -> 1정상
    public ArrayList<Room> search(String searchWord) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		try {
			connection = JdbcUtil.getCon();
			String sql = "select distinct * from room r "
					+"LEFT JOIN room_price rp ON r.id = rp.room_id "
					+ "where (r.approve=1) "
					+ "and (r.jibun_address like ? "
					+ "or r.street_address like ? "
					+ "or r.room_name like ?) ";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,"%"+searchWord+"%");
			pstmt.setString(2,"%"+searchWord+"%");
			pstmt.setString(3,"%"+searchWord+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Long roomId              = rs.getLong("room_id");
				int rentPrice            =  rs.getInt("rent_price");
				int longTermDiscount     = rs.getInt("long_term_discount");
				int earlyCheckInDiscount = rs.getInt("early_check_in_discount");
				 
				long id              = rs.getLong("id");
				String hostId        = rs.getString("host_id");
				String jibunAddress  = rs.getString("jibun_address");
				String addressDetail = rs.getString("address_detail");
				int roomCount        = rs.getInt("room_count");
				int livingRoomCount  = rs.getInt("living_room_count");
				int toiletCount      = rs.getInt("toilet_count");
				int kitchenCount     = rs.getInt("kitchen_count");
				int approve          = rs.getInt("approve");
				Room room = new Room(id, hostId, jibunAddress, addressDetail,
						roomCount, livingRoomCount, toiletCount, kitchenCount,
						approve, new RoomPrice(roomId,rentPrice,longTermDiscount,earlyCheckInDiscount));
				rooms.add(room);
			}
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
			return rooms;
		
	}
    
}

