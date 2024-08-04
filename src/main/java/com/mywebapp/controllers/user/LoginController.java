package com.mywebapp.controllers.user;

import com.mywebapp.dto.UserDto;
import com.mywebapp.util.JdbcUtil;

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
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        // 공백란 존재
        if (userId == null || userId == "" || password == null || password == "") {
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
                UserDto vo = new UserDto();
                vo.setId(rs.getLong("id"));
                vo.setUserId(rs.getString("user_id"));
                vo.setName(rs.getString("name"));
                vo.setMemberType(rs.getInt("member_type"));
                vo.setPhone(rs.getString("phone"));

                // session에 저장
                HttpSession session = req.getSession();
                session.setAttribute("user", vo);
                session.setMaxInactiveInterval(30 * 60); // session 유지시간 30분으로 설정

                if(vo.getMemberType() == 0) {
                    resp.sendRedirect(req.getContextPath() + "/jsp/service/guestMain.jsp");
                } else if (vo.getMemberType() ==1) {
                    resp.sendRedirect(req.getContextPath() + "/jsp/service/hostMain.jsp");
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
