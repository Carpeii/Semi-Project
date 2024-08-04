package com.mywebapp.controllers.user;

import com.mywebapp.dto.UserDto;
import com.mywebapp.util.JdbcUtil;
import com.mywebapp.util.PasswordUtil;

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
        HttpSession session = req.getSession(); // 수정 페이지에 기본정보 자동으로 입력되어있도록 -> 그러면 null이 아님
        UserDto user = (UserDto) session.getAttribute("user");

        String name= req.getParameter("name");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String pwConfirm = req.getParameter("pwConfirm");

        // 비밀번호 확인되지 않으면 개인정보 수정 불가
        if(password.isEmpty()) {
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
            if(rs.next()) {
                try {
    //                conn = JdbcUtil.getCon();
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
                    if(newPassword.isEmpty() && pwConfirm.isEmpty()) {
                        resp.sendRedirect(req.getContextPath() + "/jsp/service/guestMain.jsp");
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

                        resp.sendRedirect(req.getContextPath() + "/jsp/service/guestMain.jsp");
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errMsg", e.getMessage());
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
        // 이름, 휴대폰 번호 수정시
//        try {
//            conn = JdbcUtil.getCon();
//            String updateSql = "update member set name = coalesce(nullif(?, ''), name), phone = coalesce(nullif(?, ''), phone), COALESCE(CASE WHEN ? = '' THEN NULL ELSE password(?) END, password) where id = ?";
//            pstmt = conn.prepareStatement(updateSql);
//            pstmt.setString(1, name);
//            pstmt.setString(2, phone);
//            pstmt.setLong(3, user.getId());
//            int result = pstmt.executeUpdate();
//
//            if (result == 0) { // 업데이트 실패
//                req.setAttribute("errMsg", "User already exists");
//                req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
//            }
//
//            // 업데이트 성공, 비밀번호 수정하지 않는 경우
//            if(newPassword == null || newPassword == "") {
//                resp.sendRedirect(req.getContextPath() + "/jsp/service/guestMain.jsp");
//            } else if (newPassword.equals(pwConfirm)) { // 업데이트 성공, 비밀번호 수정하는 경우
//                conn = JdbcUtil.getCon();
//                String updatePwSql = "update member set password = password(?) where id = ?";
//                pstmt = conn.prepareStatement(updatePwSql);
//                pstmt.setString(1, newPassword);
//                pstmt.setLong(2, user.getId());
//                int result2 = pstmt.executeUpdate();
//
//                if (result2 == 0) {
//                    req.setAttribute("errMsg", "Password does not match");
//                    req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
//                }
//            }
//
////            // 업데이트 성공, 비밀번호 수정시
////            if(newPassword.equals(pwConfirm)) {
////                conn = JdbcUtil.getCon();
////                String updatePwSql = "update member set password = password(?) where id = ?";
////                pstmt = conn.prepareStatement(updatePwSql);
////                pstmt.setString(1, newPassword);
////                pstmt.setLong(2, user.getId());
////                int result2 = pstmt.executeUpdate();
////
////                if (result2 == 0) {
////                    req.setAttribute("errMsg", "Password does not match");
////                    req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
////                }
////            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            req.setAttribute("errMsg", e.getMessage());
//        } finally {
//            JdbcUtil.close(conn, pstmt, rs);
//        }


    }
//        HttpSession session = req.getSession();
//        UserDto user = (UserDto) session.getAttribute("user");
//
//        String name = req.getParameter("name");
//        String phone = req.getParameter("phone");
//        String password = req.getParameter("password");
//        String newPassword = req.getParameter("newPassword");
////        String pwConfirm = req.getParameter("pwConfirm");
//
//        // 1. 기존 비밀번호 공백란이면 에러메세지
//        if (password == null || password == "") {
//            req.setAttribute("errMsg", "아이디와 비밀번호 모두 기입");
//            req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
//        }
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        // 2. 사용자 입력 기존 비밀번호 == db에 저장된 기존 비밀번호
//        try {
//            conn = JdbcUtil.getCon();
//
//            String sql = "select * from member where id =? and password = password(?)";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setLong(1, user.getId());
//            pstmt.setString(2, password);
//            rs = pstmt.executeQuery();
//
//            if(rs.next()) {
//                // 비밀번호가 확인된 경우
////                updateUser(user, phone, name, req, resp);
//                // 새로운 화원정보로 업데이트
//                String updateSql = "update member set name = coalesce(nullif(?, ''), name), phone = coalesce(nullif(?, ''), phone), COALESCE(CASE WHEN ? = '' THEN NULL ELSE password(?) END, password) where id = ?";
//                pstmt = conn.prepareStatement(updateSql);
//                pstmt.setString(1, name);
//                pstmt.setString(2, phone);
//                pstmt.setLong(3, user.getId());
//                pstmt.executeUpdate();
//
////                if((newPassword != null && newPassword != "") && (pwConfirm != null && pwConfirm != "")) {
////                    updatePw(user, newPassword, pwConfirm, req, resp);
////                }
//            } else {
//                errorMsg(req, resp, "기존 비밀번호가 틀립니다.", "/jsp/user/profile.jsp");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            errorMsg(req, resp, "데이터베이스 오류입니다. 관리자에게 문의하세요.", "/jsp/service/guestMain.jsp");
//        } finally {
//            JdbcUtil.close(conn, pstmt, rs);
//        }
//    }
//
//    private void errorMsg(HttpServletRequest req, HttpServletResponse resp, String errMessage, String path) throws IOException, ServletException {
//        req.setAttribute("errorMsg", errMessage);
//        req.getRequestDispatcher(path).forward(req, resp);
//    }
//
//    private void updatePw(UserDto user, String newPassword, String pwConfirm, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        if(!newPassword.equals(pwConfirm)) {
//            errorMsg(req, resp, "새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.", "/jsp/user/profile.jsp");
//            return;
//        }
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = JdbcUtil.getCon();
//            String updatePwSql = "update member set password = password(?) where id = ?";
//            pstmt = conn.prepareStatement(updatePwSql);
//            pstmt.setString(1, newPassword);
//            pstmt.setLong(2, user.getId());
//            int rowUpdate = pstmt.executeUpdate();
//
//            if(rowUpdate == 0) {
//                errorMsg(req, resp, "회원정보 수정에 실패하였습니다. 다시 시도해주세요.", "/jsp/user/profile.jsp");
//            }
//
//            if(user.getMemberType() == 0) { // 게스트
//                resp.sendRedirect(req.getContextPath() +"/jsp/service/guestMain.jsp");
//            } else if (user.getMemberType() == 1) { // 호스트
//                resp.sendRedirect(req.getContextPath() +"/jsp/service/hostMain.jsp");
//            }
//
//        } catch (SQLException e) {
//            e.getMessage();
//            errorMsg(req, resp, "데이터베이스 오류입니다. 관리자에게 문의하세요.", "/jsp/service/guestMain.jsp");
//        } finally {
//            JdbcUtil.close(conn, pstmt, null);
//        }
//    }
//
//    private void updateUser(UserDto user, String phone, String name, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            conn = JdbcUtil.getCon();
//
//            String updateSql = "update member set name = coalesce(nullif(?, ''), name), phone = coalesce(nullif(?, ''), phone) where id = ?";
//            pstmt = conn.prepareStatement(updateSql);
//            pstmt.setString(1, name);
//            pstmt.setString(2, phone);
//            pstmt.setLong(3, user.getId());
//            int rowUpdate = pstmt.executeUpdate();
//
//            if (rowUpdate == 0) { // update된 회원정보 없음
//                errorMsg(req, resp, "회원정보 수정에 실패하였습니다. 다시 시도해주세요.", "/jsp/user/profile.jsp");
//                return;
//            }
//            // 유저에 다시 담아주기
//            user.setName(name);
//            user.setPhone(phone);
//
//            // 업데이트된 회원정보를 세션에 다시 담아주기
//            HttpSession session = req.getSession();
//            session.setAttribute("user", user);
//            System.out.println(session.getAttribute("user"));
//
//            // 게스트, 호스트 페이지로 보내주는 메서드
//            if(user.getMemberType() == 0) { // 게스트
//                resp.sendRedirect(req.getContextPath() +"/jsp/service/guestMain.jsp");
//            } else if (user.getMemberType() == 1) { // 호스트
//                resp.sendRedirect(req.getContextPath() +"/jsp/service/hostMain.jsp");
//            }
//
//        } catch (SQLException e) {
//            e.getMessage();
//            errorMsg(req, resp, "데이터베이스 오류입니다. 관리자에게 문의하세요.", "/jsp/service/guestMain.jsp");
//        } finally {
//            JdbcUtil.close(conn, pstmt, null);
//        }
//    }
//    protected void goMain(UserDto user, HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        if(user.getMemberType() == 0) { // 게스트
//            resp.sendRedirect(req.getContextPath() +"/jsp/service/guestMain.jsp");
//        } else if (user.getMemberType() == 1) { // 호스트
//            resp.sendRedirect(req.getContextPath() +"/jsp/service/hostMain.jsp");
//        }
//    }
}