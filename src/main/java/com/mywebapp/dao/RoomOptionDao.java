package com.mywebapp.dao;

import com.mywebapp.model.RoomOption;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomOptionDao {

    public void create(RoomOption roomOption) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = JdbcUtil.getCon();
            String sql = "INSERT INTO room_option (room_id, room_option) VALUES (?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, roomOption.getRoomId());
            stmt.setString(2, roomOption.getRoomOption());
            stmt.executeUpdate();
        } finally {
            JdbcUtil.close(connection, stmt, null);
        }
    }
}
