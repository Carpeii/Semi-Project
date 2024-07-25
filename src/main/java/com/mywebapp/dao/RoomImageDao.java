package com.mywebapp.dao;

import com.mywebapp.model.RoomImage;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomImageDao {

    public void create(RoomImage roomImage) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = JdbcUtil.getCon();
            String sql = "INSERT INTO room_image (room_id, image_name, image_path, image_order) VALUES (?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, roomImage.getRoomId());
            stmt.setString(2, roomImage.getImageName());
            stmt.setString(3, roomImage.getImagePath());
            stmt.setInt(4, roomImage.getImageOrder());
            stmt.executeUpdate();
        } finally {
            JdbcUtil.close(connection, stmt, null);
        }
    }
}
