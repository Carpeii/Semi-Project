package com.mywebapp.dao;

import com.mywebapp.model.RoomOption;
import com.mywebapp.util.JdbcUtil;

import java.sql.*;

public class RoomOptionDao {

    private Connection connection;

    public RoomOptionDao(Connection connection) {
        this.connection = connection;
    }

    public void create(RoomOption roomOption) throws SQLException {
        String sql = "INSERT INTO room_option (room_id, room_option) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, roomOption.getRoomId());
            stmt.setString(2, roomOption.getRoomOption());
            stmt.executeUpdate();
        }
    }

    public RoomOption read(long roomId) throws SQLException {
        String sql = "SELECT * FROM room_option WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, roomId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RoomOption(
                            rs.getLong("room_id"),
                            rs.getString("room_option")
                    );
                }
                return null;
            }
        }
    }

    public void update(RoomOption roomOption) throws SQLException {
        String sql = "UPDATE room_option SET room_option = ? WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, roomOption.getRoomOption());
            stmt.setLong(2, roomOption.getRoomId());
            stmt.executeUpdate();
        }
    }

    public void delete(long roomId) throws SQLException {
        String sql = "DELETE FROM room_option WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, roomId);
            stmt.executeUpdate();
        }
    }
}
