package com.mywebapp.dao;

import com.mywebapp.dto.MemberDto;
import com.mywebapp.model.Room;
import com.mywebapp.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    public MemberDao() {}

    public void joinMember(MemberDto dto)  {
//        String sql = "INSERT INTO members (user_id, password, name, phone, member_type, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, password(?), ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtil.getCon();
            pstmt = conn.prepareStatement(sql);
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
            JdbcUtil.close(conn, pstmt, null);
        }
    }

    public static void checkUserID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 아이디 중복 체크 버튼이 눌렸을 경우에
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();

            //db에 존재하는 아이디인지 확인
            String sql = "select * from member where user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, req.getParameter("userId"));
            rs = pstmt.executeQuery();

            if (rs.next()) {
                req.setAttribute("idErrMsg", "사용할 수 없는 아이디 입니다. 새로운 아이디로 다시 시도하세요.");
                req.setAttribute("isDuplicate", true);
                req.setAttribute("userId", "");
            } else {
                req.setAttribute("idErrMsg", "사용할 수 있는 아이디 입니다!");
                req.setAttribute("isDuplicate", false);
                req.setAttribute("userId", req.getParameter("userId"));
            }

            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("idErrMsg", "데이터베이스의 오류입니다. 관리자에게 문의하세요.");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);

        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }
}
