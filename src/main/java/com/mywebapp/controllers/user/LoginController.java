package com.mywebapp.controllers.user;

import com.mywebapp.dto.HostDto;
import com.mywebapp.dto.UserDto;
import com.mywebapp.util.JdbcUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        // 공백란 존재
        if (userId.isEmpty() || password.isEmpty()) {
            req.setAttribute("errMsg", "아이디와 비밀번호 모두 기입");
            req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();

            String sql = "select * from member where user_id = ? and password = password(?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                // id, pw와 일치하는 사용자 발견
                UserDto dto = new UserDto();
                dto.setId(rs.getLong("id"));
                dto.setUserId(rs.getString("user_id"));
                dto.setName(rs.getString("name"));
                dto.setMemberType(rs.getInt("member_type"));
                dto.setPhone(rs.getString("phone"));

                // session에 저장
                HttpSession session = req.getSession();
                session.setAttribute("user", dto);
                session.setMaxInactiveInterval(30 * 60); // session 유지시간 30분으로 설정

                if(dto.getMemberType() == 0) {
                    resp.sendRedirect(req.getContextPath() + "/service/guestMain");
                } else if (dto.getMemberType() ==1) {
                    // 호스트 정보 조회
                    String hostSql = "SELECT * FROM host WHERE member_id = ?";
                    pstmt = conn.prepareStatement(hostSql);
                    pstmt.setLong(1, dto.getId()); // UserDto의 id를 사용
                    ResultSet hostRs = pstmt.executeQuery();

                    if (hostRs.next()) {
                        // 호스트 정보 설정
                        HostDto hostDto = new HostDto();
                        hostDto.setMemberId(hostRs.getLong("member_id"));
                        hostDto.setBankName(hostRs.getString("bank_name"));
                        hostDto.setAccount(hostRs.getString("account"));
                        hostDto.setAccountHolder(hostRs.getString("account_holder"));

                        // 세션에 호스트 정보 저장
                        session.setAttribute("host", hostDto);
                        session.setMaxInactiveInterval(30 * 60);
                    }

                    resp.sendRedirect(req.getContextPath() + "/hostMain");
                } else if(dto.getMemberType() == 3) { // admin
                    resp.sendRedirect(req.getContextPath() + "/adminMain");
                }
            } else {
                req.setAttribute("errMsg", "아이디 비밀번호를 확인하세요.");
                req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.getMessage();
            req.setAttribute("errMsg", e.getMessage());
            req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }

    }
}
