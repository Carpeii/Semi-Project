package com.mywebapp.dao;

import com.mywebapp.dto.MemberDto;
import com.mywebapp.model.Room;
import com.mywebapp.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    private Connection connection;

    public MemberDao(Connection connection) {
        this.connection = connection;
    }

    public void joinMember(MemberDto dto) throws SQLException {
//        String sql = "INSERT INTO members (user_id, password, name, phone, member_type, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, password(?), ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, dto.getUserId());
            pstmt.setString(2, dto.getPassword());
            pstmt.setString(3, dto.getName());
            pstmt.setString(4, dto.getPhone());
            pstmt.setInt(5, dto.getMemberType());
//            pstmt.setTimestamp(6, member.getCreatedAt());
//            pstmt.setTimestamp(7, member.getUpdatedAt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            JdbcUtil.close(conn, pstmt, null);
        }

    }
}
