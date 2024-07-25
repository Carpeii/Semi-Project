package com.mywebapp.dao;

import com.mywebapp.model.RoomOption;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomOptionDao {

    public void insert(RoomOption roomOption) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = JdbcUtil.getCon();
            String sql = "INSERT INTO room_option (room_id, room_option) VALUES (?, ?)";
            pstmt = connection.prepareStatement(sql);

            pstmt.setLong(1, roomOption.getRoomId());
            pstmt.setString(2, roomOption.getRoomOption());
            pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(connection, pstmt, null);
        }
    }
}
