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

    public void joinMember(MemberDto member) throws SQLException {
        String sql = "INSERT INTO members (user_id, password, name, phone, member_type, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            pstmt.setString(1, member.getUserId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhone());
            pstmt.setInt(5, member.getMemberType());
            pstmt.setTimestamp(6, member.getCreatedAt());
            pstmt.setTimestamp(7, member.getUpdatedAt());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
