package com.mywebapp.dao;

import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.model.RoomOption;
import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

    public long insert(Room room) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long roomId = -1;
        String sql = "INSERT INTO room (host_id, room_name, jibun_address, street_address, address_detail, floor, usable_area, room_count, " +
                "living_room_count, toilet_count, kitchen_count, duplex, elevator, park, park_detail, room_type, " +
                "minimum_contract, approve) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = JdbcUtil.getCon();
            pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

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
            int affectedRow = pstmt.executeUpdate();
            if (affectedRow == 0) {
                throw new SQLException("Insert failed");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                roomId = rs.getLong(1);
                System.out.println(roomId);
            }else {
                throw new SQLException("NO ID");
            }
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.close(con, pstmt, null);
        }
        return roomId;
    }
}
