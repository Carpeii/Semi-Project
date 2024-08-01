package com.mywebapp.dao;

import com.mywebapp.model.RoomOption;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomOptionDao {

    public int insert(RoomOption roomOption) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = JdbcUtil.getCon();
            String sql = "INSERT INTO room_option (room_id, room_options) VALUES (?, ?)";
            pstmt = con.prepareStatement(sql);
            System.out.println(roomOption.getRoomOptions());
            pstmt.setLong(1, roomOption.getRoomId());
            pstmt.setString(2, roomOption.getRoomOptions());
            return pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
        finally {
            JdbcUtil.close(con, pstmt, null);
        }
    }
}
