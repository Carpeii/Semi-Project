package com.mywebapp.dao;

import com.mywebapp.model.RoomImage;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomImageDao {

    public void insert(RoomImage roomImage) throws SQLException {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = JdbcUtil.getCon();
            String sql = "INSERT INTO room_image (room_id, image_name, save_file_name, image_path, image_order) VALUES (?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, roomImage.getRoomId());
            pstmt.setString(2, roomImage.getImageName());
            pstmt.setString(3, roomImage.getSaveFileName());
            pstmt.setString(4, roomImage.getImagePath());
            pstmt.setInt(5, roomImage.getImageOrder());
            pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(connection, pstmt, null);
        }
    }
}
