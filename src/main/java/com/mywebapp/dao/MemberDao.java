package com.mywebapp.dao;

import com.mywebapp.dto.MemberDto;
import com.mywebapp.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class MemberDao {

    public MemberDao() {}

    public void joinMember(MemberDto dto)  {
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
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pstmt, null);
        }
    }

    public MemberDto joinHostMember(String userId, String bankName, String account, String accountHolder)  {
        String selectSql = "select id from member where user_id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();
            pstmt = conn.prepareStatement(selectSql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            int memberId = -1;

            if(rs.next()){
                memberId = rs.getInt("id"); // member 테이블에서 조회된 Id값 찾아서 변수에 넣기

                String insertSql = "INSERT INTO host (member_id, bank_name, account, account_holder) VALUES (?, ?, ?, ?)";
                pstmt.close(); // 기존 prepatredStatement 닫고 새로운 Pstmt 생성
                pstmt = conn.prepareStatement(insertSql);
                pstmt.setLong(1, memberId); // 조회한 id값 insert
                pstmt.setString(2, bankName);
                pstmt.setString(3, account);
                pstmt.setString(4, accountHolder);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected == 0) {
                    return null;
                }
                return new MemberDto();
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    public MemberDto loginMember(String userId, String password)  {
        String sql = "select * from member where user_id = ? and password = password(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            MemberDto dto = new MemberDto();
            if (rs.next()) {
                dto.setId(rs.getLong("id"));
                dto.setUserId(rs.getString("user_id"));
                dto.setName(rs.getString("name"));
                dto.setMemberType(rs.getInt("member_type"));
                dto.setPhone(rs.getString("phone"));
                return dto;
            }
            // 로그인에 실패한 경우
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    public static void checkUserID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 아이디 중복 체크 버튼이 눌렸을 경우에
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String userId = req.getParameter("userId");
        if(userId == null || userId.equals("")) {
            req.setAttribute("idErrMsg", "아이디 입력은 필수입니다.");
            req.setAttribute("isDuplicate", true);
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
            return;
        }

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
