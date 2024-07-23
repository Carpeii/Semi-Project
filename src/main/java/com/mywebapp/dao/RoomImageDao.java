package com.mywebapp.dao;

import com.mywebapp.model.RoomImage;
import com.mywebapp.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomImageDao {

    private Connection connection;

    public RoomImageDao(Connection connection) {
        this.connection = connection;
    }

    public void create(RoomImage roomImage) throws SQLException {
        String sql = "INSERT INTO room_image (room_id, image_name, image_path, image_order) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, roomImage.getRoomId());
            stmt.setString(2, roomImage.getImageName());
            stmt.setString(3, roomImage.getImagePath());
            stmt.setInt(4, roomImage.getImageOrder());
            stmt.executeUpdate();
        }
    }

    public RoomImage read(long id) throws SQLException {
        String sql = "SELECT * FROM room_image WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RoomImage(
                            rs.getLong("id"),
                            rs.getLong("room_id"),
                            rs.getString("image_name"),
                            rs.getString("image_path"),
                            rs.getInt("image_order")
                    );
                }
                return null;
            }
        }
    }

    public List<RoomImage> findAll() throws SQLException {
        List<RoomImage> roomImages = new ArrayList<>();
        String sql = "SELECT * FROM room_image";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                roomImages.add(new RoomImage(
                        rs.getLong("id"),
                        rs.getLong("room_id"),
                        rs.getString("image_name"),
                        rs.getString("image_path"),
                        rs.getInt("image_order")
                ));
            }
        }
        return roomImages;
    }

    public void update(RoomImage roomImage) throws SQLException {
        String sql = "UPDATE room_image SET room_id = ?, image_name = ?, image_path = ?, image_order = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, roomImage.getRoomId());
            stmt.setString(2, roomImage.getImageName());
            stmt.setString(3, roomImage.getImagePath());
            stmt.setInt(4, roomImage.getImageOrder());
            stmt.setLong(5, roomImage.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM room_image WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
