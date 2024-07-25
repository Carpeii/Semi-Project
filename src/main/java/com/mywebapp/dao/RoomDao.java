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

    public void create(Room room) throws SQLException {
        String sql = "INSERT INTO room (host_id, jibun_address, street_address, floor, usable_area, room_count, " +
                "living_room_count, toilet_count, kitchen_count, duplex, elevator, park, park_detail, " +
                "room_type, minimum_contract, approve) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, room.getHostId());
            stmt.setString(2, room.getJibunAddress());
            stmt.setString(3, room.getStreetAddress());
            stmt.setInt(4, room.getFloor());
            stmt.setInt(5, room.getUsableArea());
            stmt.setInt(6, room.getRoomCount());
            stmt.setInt(7, room.getLivingRoomCount());
            stmt.setInt(8, room.getToiletCount());
            stmt.setInt(9, room.getKitchenCount());
            stmt.setBoolean(10, room.isDuplex());
            stmt.setBoolean(11, room.isElevator());
            stmt.setBoolean(12, room.isPark());
            stmt.setString(13, room.getParkDetail());
            stmt.setInt(14, room.getRoomType());
            stmt.setInt(15, room.getMinimumContract());
            stmt.setInt(16, room.getApprove());
            stmt.executeUpdate();
        }
    }

    public Room read(long id) throws SQLException {
        String sql = "SELECT * FROM room WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                            rs.getLong("id"),
                            rs.getString("host_id"),
                            rs.getString("jibun_address"),
                            rs.getString("street_address"),
                            rs.getInt("floor"),
                            rs.getInt("usable_area"),
                            rs.getInt("room_count"),
                            rs.getInt("living_room_count"),
                            rs.getInt("toilet_count"),
                            rs.getInt("kitchen_count"),
                            rs.getBoolean("duplex"),
                            rs.getBoolean("elevator"),
                            rs.getBoolean("park"),
                            rs.getString("park_detail"),
                            rs.getInt("room_type"),
                            rs.getInt("minimum_contract"),
                            rs.getInt("approve"),
                            // Assuming we need to populate related lists, you need additional queries to fetch these
                            new ArrayList<>(), // Placeholder for roomImageList
                            null, // Placeholder for roomOption
                            null // Placeholder for roomPrice
                    );
                }
                return null;
            }
        }
    }

    public List<Room> findAll() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rooms.add(new Room(
                        rs.getLong("id"),
                        rs.getString("host_id"),
                        rs.getString("jibun_address"),
                        rs.getString("street_address"),
                        rs.getInt("floor"),
                        rs.getInt("usable_area"),
                        rs.getInt("room_count"),
                        rs.getInt("living_room_count"),
                        rs.getInt("toilet_count"),
                        rs.getInt("kitchen_count"),
                        rs.getBoolean("duplex"),
                        rs.getBoolean("elevator"),
                        rs.getBoolean("park"),
                        rs.getString("park_detail"),
                        rs.getInt("room_type"),
                        rs.getInt("minimum_contract"),
                        rs.getInt("approve"),
                        new ArrayList<>(), // Placeholder for roomImageList
                        null, // Placeholder for roomOption
                        null // Placeholder for roomPrice
                ));
            }
        }
        return rooms;
    }
    
    /* 게스트 페이지에서 보여줄 방 리스트 */
    public List<RoomListItemDto> findAllRoomListItems() throws SQLException {
    	List<RoomListItemDto> roomList = new ArrayList<RoomListItemDto>();
        String sql = "SELECT ri.image_path, ri.image_name, r.room_name, r.street_address, rp.rent_price, ro.room_option " +
                "FROM room r " +
                "INNER JOIN room_image ri ON r.id = ri.room_id " +
                "INNER JOIN room_option ro ON r.id = ro.room_id " +
                "INNER JOIN room_price rp ON r.id = rp.room_id";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
        	while (rs.next()) {
        		roomList.add(new RoomListItemDto(
        				rs.getString("image_path"),
        				rs.getString("image_name"),
        				rs.getString("room_name"),
        				rs.getString("street_address"),
        				rs.getInt("rent_price"),
        				rs.getString("room_option")		
        				));
        	}
        }
        
        return roomList;
    }

    public void update(Room room) throws SQLException {
        String sql = "UPDATE room SET host_id = ?, jibun_address = ?, street_address = ?, floor = ?, usable_area = ?, " +
                "room_count = ?, living_room_count = ?, toilet_count = ?, kitchen_count = ?, duplex = ?, elevator = ?, " +
                "park = ?, park_detail = ?, room_type = ?, minimum_contract = ?, approve = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getHostId());
            stmt.setString(2, room.getJibunAddress());
            stmt.setString(3, room.getStreetAddress());
            stmt.setInt(4, room.getFloor());
            stmt.setInt(5, room.getUsableArea());
            stmt.setInt(6, room.getRoomCount());
            stmt.setInt(7, room.getLivingRoomCount());
            stmt.setInt(8, room.getToiletCount());
            stmt.setInt(9, room.getKitchenCount());
            stmt.setBoolean(10, room.isDuplex());
            stmt.setBoolean(11, room.isElevator());
            stmt.setBoolean(12, room.isPark());
            stmt.setString(13, room.getParkDetail());
            stmt.setInt(14, room.getRoomType());
            stmt.setInt(15, room.getMinimumContract());
            stmt.setInt(16, room.getApprove());
            stmt.setLong(17, room.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM room WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
    
    
    //승인여부도 확인해야됨 applove -> 1정상
    public List<Room> search(String searchWord) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Room> rooms = new ArrayList<Room>();
		
		try {
			connection = JdbcUtil.getCon();
			String sql = "select distinct* from  room r "
					+"LEFT JOIN room_price rp ON r.id = rp.room_id "
					+ "where "
					+ "(r.approve=1) "
					+ "and (r.jibun_address like ? "
					+ "or r.jibun_address like ? "
					+ "or r.jibun_address like ? "
					+ "or r.street_address like ? "
					+ "or r.street_address like ? "
					+ "or r.street_address like ? "
					+ "or r.room_name like ? "
					+ "or r.room_name like ? "
					+ "or r.room_name like ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1,searchWord+"%");
			pstmt.setString(2,"%"+searchWord+"%");
			pstmt.setString(3,"%"+searchWord);
			pstmt.setString(4,searchWord+"%");
			pstmt.setString(5,"%"+searchWord+"%");
			pstmt.setString(6,"%"+searchWord);
			pstmt.setString(7,searchWord+"%");
			pstmt.setString(8,"%"+searchWord+"%");
			pstmt.setString(9,"%"+searchWord);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 Long roomId = rs.getLong("room_id");
				 int rentPrice =  rs.getInt("rent_price");
				int longTermDiscount = rs.getInt("long_term_discount");
				 int earlyCheckInDiscount = rs.getInt("early_check_in_discount");
				 
				 long id = rs.getLong("id");
				 String hostId = rs.getString("host_id");
				 String jibunAddress = rs.getString("jibun_address");
				 int roomCount           = rs.getInt("room_count");
					int livingRoomCount  = rs.getInt("living_room_count");
					int toiletCount      = rs.getInt("toilet_count");
					int kitchenCount     = rs.getInt("kitchen_count");
					 int approve         = rs.getInt("approve");
					Room room = new Room(id, hostId, jibunAddress,
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

