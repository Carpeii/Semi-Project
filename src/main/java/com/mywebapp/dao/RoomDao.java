package com.mywebapp.dao;

import com.mywebapp.model.Room;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDao {

    public void create(Room room) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = JdbcUtil.getCon();
            String sql = "INSERT INTO room (host_id, room_name, jibun_address, street_address, floor, usable_area, room_count, living_room_count, toilet_count, kitchen_count, duplex, elevator, park, park_detail, room_type, minimum_contract, approve) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, room.getHostId());
            stmt.setString(2, room.getRoomName());
            stmt.setString(3, room.getJibunAddress());
            stmt.setString(4, room.getStreetAddress());
            stmt.setInt(5, room.getFloor());
            stmt.setInt(6, room.getUsableArea());
            stmt.setInt(7, room.getRoomCount());
            stmt.setInt(8, room.getLivingRoomCount());
            stmt.setInt(9, room.getToiletCount());
            stmt.setInt(10, room.getKitchenCount());
            stmt.setBoolean(11, room.isDuplex());
            stmt.setBoolean(12, room.isElevator());
            stmt.setBoolean(13, room.isPark());
            stmt.setString(14, room.getParkDetail());
            stmt.setInt(15, room.getRoomType());
            stmt.setInt(16, room.getMinimumContract());
            stmt.setInt(17, room.getApprove());
            stmt.executeUpdate();
        } finally {
            JdbcUtil.close(connection, stmt, null);
        }
    }
}
