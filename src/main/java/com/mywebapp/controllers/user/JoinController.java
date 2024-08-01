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

import static java.lang.Boolean.parseBoolean;

@WebServlet("/joinOk")
public class JoinController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        boolean isDuplicate = parseBoolean(req.getParameter("isDuplicate"));
        //isDuplicate = true;

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
        if ("checkId".equals(action)) {
            try {
                conn = JdbcUtil.getCon();

                //db에 존재하는 아이디인지 확인
                String sql = "select * from member where user_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userId);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    req.setAttribute("idErrMsg", "사용할 수 없는 아이디 입니다. 새로운 아이디로 다시 시도하세요.");
                    req.setAttribute("isDuplicate", true);
                    req.setAttribute("userId", "");
                } else {
                    req.setAttribute("idErrMsg", "사용할 수 있는 아이디 입니다!");
                    req.setAttribute("isDuplicate", false);
                    req.setAttribute("userId", userId);
                }

                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
                return;

            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("idErrMsg", "데이터베이스의 오류입니다. 관리자에게 문의하세요.");
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);

            } finally {
                JdbcUtil.close(conn, pstmt, rs);
            }
        }

        if ("guest".equals(action) || "host".equals(action)) {
            if (isDuplicate) {
                req.setAttribute("errMsg", "아이디 중복 검사 필수 입니다.");
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
                return;
            }

            // 비밀번호 확인
            if (!pwConfirm.equals(password)) {
                req.setAttribute("pwErrMsg", "비밀번호가 일치하지 않습니다.");
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
                return;
            }

            // 회원가입으로 데이터베이스에 insert
            try {
                conn = JdbcUtil.getCon();
                String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, PASSWORD(?), ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userId);
                pstmt.setString(2, password);
                pstmt.setString(3, name);
                pstmt.setString(4, phone);
                pstmt.setInt(5, "guest".equals(action) ? 0 : 1);
                pstmt.executeUpdate();

                HttpSession session = req.getSession();
                session.setAttribute("userId", userId); // session에 userId 저장

                // 회원가입 성공 -> 메인 페이지로 이동
                if ("guest".equals(action)) { // 게스트 회원가입
                    req.getRequestDispatcher("/jsp/service/guestMain.jsp").forward(req, resp);
                } else { // 호스트 회원가입
                    req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                req.setAttribute("errMsg", "데이터베이스의 오류입니다. 관리자에게 문의하세요.");
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
            } finally {
                JdbcUtil.close(conn, pstmt, rs);
            }
        }
    }
}