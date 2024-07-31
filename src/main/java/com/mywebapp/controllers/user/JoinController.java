package com.mywebapp.controllers.user;

import com.mywebapp.dto.MemberDto;
import com.mywebapp.service.JoinValidator;
import com.mywebapp.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/joinOk")
public class JoinController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        boolean isDuplicate = Boolean.parseBoolean(req.getParameter("isDuplicate"));
        // 비밀번호 확인
        String pwConfirm = req.getParameter("pwConfirm");
        String password = req.getParameter("password");

        // 아이디 중복확인을 위한 변수
        String action = req.getParameter("action");
        String userId = req.getParameter("userId");

        // db 연결 변수
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");

        // db 연결
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        // 에러 메세지 띄우기 위한 변수
        String idErrMsg = null;
        String pwErrMsg = null;
        String errMsg = null;

        // 아이디 중복 체크 버튼이 눌렸을 경우에
        if (action.equals("checkId")) {
            try {
                conn = JdbcUtil.getCon();

                // db에 존재하는 아이디인지 확인
                String sql = "select * from member where user_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userId);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    req.setAttribute("idErrMsg", "사용할 수 없는 아이디 입니다.");
                    req.setAttribute("isDuplicate", true);
                }else {
                    req.setAttribute("isDuplicate", false);
                }
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);

            } catch (SQLException e) {
                e.printStackTrace();
                idErrMsg = "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.";
            } finally {
                JdbcUtil.close(conn, pstmt, rs);
            }
        }

        //guest버튼이 눌렸을때
        if(action.equals("guest")) { // 아이디 중복 x
            if(isDuplicate){
                req.setAttribute("errMsg", "중복검사 하고오세요.");
            }else{

            }

            // 비밀번호 == 비밀번호 확인
            if (!pwConfirm.equals( "password")) {
                pwErrMsg = "비밀번호가 일치하지 않습니다.";
            } else {
                pwErrMsg = "일치하는 비밀번호 입니다!";

                // db 에 회원정보 저장
                if (userId != null && password != null && name != null && phone != null) {
                    try {
                        String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, PASSWORD(?), ?, ?, ?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userId);
                        pstmt.setString(2, password);
                        pstmt.setString(3, name);
                        pstmt.setString(4, phone);
                        pstmt.setInt(5, 0);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        JdbcUtil.close(conn, pstmt, rs);
                    }
                }
            }
        }

        //host버튼이 눌렸을때
        if(action.equals("host")) { // 아이디 중복 x
            if(isDuplicate){
                req.setAttribute("errMsg", "중복검사 하고오세요.");
            }else{

            }

            // 비밀번호 == 비밀번호 확인
            if (!pwConfirm.equals( "password")) {
                pwErrMsg = "비밀번호가 일치하지 않습니다.";
            } else {
                pwErrMsg = "일치하는 비밀번호 입니다!";

                // db 에 회원정보 저장
                if (userId != null && password != null && name != null && phone != null) {

                    try {
                        String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, PASSWORD(?), ?, ?, ?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userId);
                        pstmt.setString(2, password);
                        pstmt.setString(3, name);
                        pstmt.setString(4, phone);
                        pstmt.setInt(5, 1);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } finally {
                        JdbcUtil.close(conn, pstmt, rs);
                    }
                }
            }
        }
    }
}