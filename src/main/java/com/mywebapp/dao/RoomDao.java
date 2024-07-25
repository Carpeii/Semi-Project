package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.model.RoomOption;
import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;

public class RoomDao {

    public int insert(Room room) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String sql = "INSERT INTO room (host_id, room_name, jibun_address, street_address, address_detail, floor, usable_area, room_count, " +
                "living_room_count, toilet_count, kitchen_count, duplex, elevator, park, park_detail, room_type, " +
                "minimum_contract, approve) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = JdbcUtil.getCon();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, room.getHostId());
            pstmt.setString(2, room.getRoomName());
            pstmt.setString(3, room.getJibunAddress());
            pstmt.setString(4, room.getStreetAddress());
            pstmt.setString(5, room.getAddressDetail());
            pstmt.setInt(6, room.getFloor());
            pstmt.setInt(7, room.getUsableArea());
            pstmt.setInt(8, room.getRoomCount());
            pstmt.setInt(9, room.getLivingRoomCount());
            pstmt.setInt(10, room.getToiletCount());
            pstmt.setInt(11, room.getKitchenCount());
            pstmt.setBoolean(12, room.isDuplex());
            pstmt.setBoolean(13, room.isElevator());
            pstmt.setBoolean(14, room.isPark());
            pstmt.setString(15, room.getParkDetail());
            pstmt.setInt(16, room.getRoomType());
            pstmt.setInt(17, room.getMinimumContract());
            pstmt.setInt(18, room.getApprove());
            int result = pstmt.executeUpdate();

//            // Get generated ID
//            ResultSet generatedKeys = pstmt.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                long roomId = generatedKeys.getLong(1);
//
//                // Insert related RoomOption
//                if (room.getRoomOption() != null) {
//                    insertRoomOption(roomId, room.getRoomOption());
//                }
//
//                // Insert related RoomPrice
//                if (room.getRoomPrice() != null) {
//                    insertRoomPrice(roomId, room.getRoomPrice());
//                }
//
//                // Insert related RoomImages
//                if (room.getRoomImageList() != null) {
//                    for (RoomImage image : room.getRoomImageList()) {
//                        insertRoomImage(roomId, image);
//                    }
//                }
//            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }

    private int insertRoomOption(long roomId, RoomOption roomOption){
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO room_option (room_id, room_option) VALUES (?, ?)";
        try {
            con = JdbcUtil.getCon();
            pstmt = con.prepareStatement(sql);

            pstmt.setLong(1, roomId);
            pstmt.setString(2, roomOption.getRoomOption());
            return pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }

    


 

    private int insertRoomPrice(long roomId, RoomPrice roomPrice)  {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO room_price (room_id, rent_price, long_term, long_term_discount, early_check_in, " +
                "early_check_in_discount, maintenance_bill, maintenance_bill_detail, electricity, water, gas, " +
                "internet, cleaning_fee, refund_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = JdbcUtil.getCon();
            pstmt = con.prepareStatement(sql);

            pstmt.setLong(1, roomId);
            pstmt.setInt(2, roomPrice.getRentPrice());
            pstmt.setInt(3, roomPrice.getLongTerm());
            pstmt.setInt(4, roomPrice.getLongTermDiscount());
            pstmt.setInt(5, roomPrice.getEarlyCheckIn());
            pstmt.setInt(6, roomPrice.getEarlyCheckInDiscount());
            pstmt.setInt(7, roomPrice.getMaintenanceBill());
            pstmt.setString(8, roomPrice.getMaintenanceBillDetail());
            pstmt.setBoolean(9, roomPrice.isElectricity());
            pstmt.setBoolean(10, roomPrice.isWater());
            pstmt.setBoolean(11, roomPrice.isGas());
            pstmt.setBoolean(12, roomPrice.isInternet());
            pstmt.setInt(13, roomPrice.getCleaningFee());
            pstmt.setInt(14, roomPrice.getRefundType());
            return pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }

    private int insertRoomImage(long roomId, RoomImage roomImage) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO room_image (room_id, image_name, image_path, image_order) VALUES (?, ?, ?, ?)";
        try{
            con = JdbcUtil.getCon();
            pstmt = con.prepareStatement(sql);
            
            pstmt.setLong(1, roomId);
            pstmt.setString(2, roomImage.getImageName());
            pstmt.setString(3, roomImage.getImagePath());
            pstmt.setInt(4, roomImage.getImageOrder());
            return pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }
    
    /* 게스트 페이지에서 보여줄 방 리스트 */
    public List<RoomListItemDto> findAllRoomListItems() {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
        String sql = "SELECT ri.image_path, ri.image_name, r.room_name, r.street_address, rp.rent_price, ro.room_option " +
                "FROM room r " +
                "INNER JOIN room_image ri ON r.id = ri.room_id " +
                "INNER JOIN room_option ro ON r.id = ro.room_id " +
                "INNER JOIN room_price rp ON r.id = rp.room_id";
        try {
        	con = JdbcUtil.getCon();
        	pstmt = con.prepareStatement(sql);
        	rs = pstmt.executeQuery();
        	List<RoomListItemDto> roomList = new ArrayList<RoomListItemDto>();
        	while (rs.next()) {
				String imagePath = rs.getString("image_path");
				String imageName = rs.getString("image_name");
				String roomName = rs.getString("room_name");
				String streetAddress = rs.getString("street_address");
				int rentPrice = rs.getInt("rent_price");
				String roomOption = rs.getString("room_option");
				RoomListItemDto dto = new RoomListItemDto(imagePath, imagePath, roomName, streetAddress, rentPrice, roomOption);
        		roomList.add(dto);
        	}
        	return roomList;
        } catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
        

    }

    
    
}
