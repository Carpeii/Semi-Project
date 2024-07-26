package com.mywebapp.dao;

import com.mywebapp.model.RoomOption;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomOptionDao {

    public void insert(Connection con, RoomOption roomOption) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            String sql = "INSERT INTO room_option (room_id, room_options) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);
            System.out.println(roomOption.getRoomOptions());
            pstmt.setLong(1, roomOption.getRoomId());
            pstmt.setString(2, roomOption.getRoomOptions());
            pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
    }
}
