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

@WebServlet("/edit")
public class ProfileEditController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); // 수정 페이지에 기본정보 자동으로 입력되어있도록 -> null이 아님
        UserDto user = (UserDto) session.getAttribute("user");

        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String pwConfirm = req.getParameter("pwConfirm");

        // 비밀번호 확인되지 않으면 개인정보 수정 불가
        if (password.isEmpty()) {
            req.setAttribute("errMsg", "Password is empty");
            req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();
            String passwordCheckSql = "select * from member where id = ? and password = password(?)";
            pstmt = conn.prepareStatement(passwordCheckSql);
            pstmt.setLong(1, user.getId());
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                try {
//                    String updateSql = "update member set name = coalesce(nullif(?, ''), name), phone = coalesce(nullif(?, ''), phone) where id = ?";
                    String updateSql = "update member set name = ?, phone = ? where id = ?";
                    pstmt = conn.prepareStatement(updateSql);
                    pstmt.setString(1, name);
                    pstmt.setString(2, phone);
                    pstmt.setLong(3, user.getId());
                    int result = pstmt.executeUpdate();
                    System.out.println(result);

                    if (result == 0) { // 업데이트 실패
                        req.setAttribute("errMsg", "User already exists");
                        req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
                    }

                    // 업데이트 성공, 비밀번호 수정하지 않는 경우
                    if (newPassword.isEmpty() && pwConfirm.isEmpty()) {
                        UserDto updateUser = new UserDto();
                        updateUser.setId(user.getId());
                        updateUser.setName(name);
                        updateUser.setPhone(phone);
                        // 세션에 수정도니 정보 저장
                        session.setAttribute("user", updateUser);
                        if (user.getMemberType() == 0) {
                            resp.sendRedirect(req.getContextPath() + "/jsp/service/guestMain.jsp");
                        } else if (user.getMemberType() == 1) {
                            resp.sendRedirect(req.getContextPath() + "/jsp/user/hostProfile.jsp");
                        }

                    } else if (newPassword.equals(pwConfirm)) {
                        conn = JdbcUtil.getCon();
                        String updatePwSql = "update member set password = password(?) where id = ?";
                        pstmt = conn.prepareStatement(updatePwSql);
                        pstmt.setString(1, newPassword);
                        pstmt.setLong(2, user.getId());
                        int result2 = pstmt.executeUpdate();
                        System.out.println(result2);

                        if (result2 == 0) {
                            req.setAttribute("errMsg", "Password does not match");
                            req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
                        }

                        // 비밀번호 업데이트도 완료
                        if (user.getMemberType() == 0) {
                            resp.sendRedirect(req.getContextPath() + "/jsp/service/guestMain.jsp");
                        } else if (user.getMemberType() == 1) {
                            resp.sendRedirect(req.getContextPath() + "/jsp/user/hostProfile.jsp");
                        }

                    } else if (newPassword.isEmpty() || pwConfirm.isEmpty()) { // 업데이트는 성공, 비밀번호 변경란 하나가 비어있는 경우
                        req.setAttribute("errMsg", "비밀번호 변경을 원하시면 모두 채워주세요");
                        req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    req.setAttribute("errMsg", e.getMessage());
                } finally {
                    JdbcUtil.close(conn, pstmt, rs);
                }
            } else {
                // 기존 비밀번호 오류
                req.setAttribute("errMsg", "기존 비밀번호를 확인하세요");
                req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errMsg", e.getMessage());
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }
}