package com.mywebapp.controllers.user;

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

@WebServlet("/edit")
public class ProfileEditController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 삼항 연산자 사용해서 null인 경우 -> "" 넣어주기
//        String edit = req.getParameter("edit") != null ? req.getParameter("edit") : ""; // 무조건 눌러야 controller 타는 거라서 필요없음
        String name = req.getParameter("name") != null ? req.getParameter("name") : "";
        String phone = req.getParameter("phone") != null ? req.getParameter("phone") : "";
        String newPassword = req.getParameter("newPassword") != null ? req.getParameter("newPassword") : "";
        String password = req.getParameter("password") != null ? req.getParameter("password") : "";
        String pwConfirm = req.getParameter("pwConfirm") != null ? req.getParameter("pwConfirm") : "";

        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

//        String errMsg = null;

        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        if(password != null) {
            try {
                conn = JdbcUtil.getCon();

                // 1. 기존 비밀번호 확인 - 맞지 않으면 pwErrMsg 에러 띄우기
                // 확인해야 할 항목 password랑 session으로 받아온 userId가 같은지 -> 비번 같은 사람 존재할 수 있음
                String sql = "select * from member where password=? and user_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, password);
                pstmt.setString(2, userId);
                rs = pstmt.executeQuery();

                if (rs.next()) { // 기존 비밀번호와 같음 - 개인정보 수정 가능!
                    // 바뀐 정보 Insert
                    // 2. 공백이 아닌 칸에 있는 정보 insert문으로 db에 집어넣기
                    String updateSql = "update member set name = coalesce(nullif(?, ''), name), phone = coalesce(nullif(?, ''), phone), password = coalesce(nullif(?, ''), password) where user_id = ?";
                    pstmt = conn.prepareStatement(updateSql);
                    pstmt.setString(1, name);
                    pstmt.setString(2, phone);
                    if(newPassword.equals(pwConfirm)) {
                        pstmt.setString(3, newPassword);
                    } else {

                    }
                    pstmt.setString(4, userId);
                    int rowUpdate = pstmt.executeUpdate();
                    if (rowUpdate > 0) {
                        req.setAttribute("errMsg", "회원정보가 성공적으로 수정되었습니다.");
                        req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("errMsg", "회원정보 수정에 실패하였습니다.");
                        req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
                    }

                } else {
                    req.setAttribute("errMsg", "기존 비밀번호와 일치하지 않습니다. 다시 확인해주세요.");
                    req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errMsg", "데이터베이스의 오류입니다. 관리자에게 문의하세요.");
                req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
            } finally {
                JdbcUtil.close(conn, pstmt, rs);
            }
        } else { // 기존 비밀번호 확인란이 공백인 경우
            req.setAttribute("errMsg", "기존 비밀번호를 입력하세요.");
            req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
        }
    }
}

