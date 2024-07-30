package com.mywebapp.controllers.user;

import com.mywebapp.dao.MemberDao;
import com.mywebapp.dto.UserDto;
import com.mywebapp.service.UserVO;
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/jsp/service/main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        String guestLogin = req.getParameter("guestLogin");
        String hostLogin = req.getParameter("hostLogin");

        MemberDao member = new MemberDao(JdbcUtil.getCon());
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();
            //if (id != null && pw != null) {
            if (id != null && !id.isEmpty() && pw != null && !pw.isEmpty()) {
                // 아이디, 비밀번호가 공백이 아닌 경우
                // db 연결해서 아이디 비밀번호 일치 확인
                try {
                    String sql = "select * from member where user_id = ? and password = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id);
                    pstmt.setString(2, pw);
                    rs = pstmt.executeQuery();

                    if (rs.next()) {
                        //user 로그인 정보를 담기 위한 객체 생성
                        UserDto vo = new UserDto();
                        vo.setId(rs.getString("id"));
                        vo.setPassword(rs.getString("password"));

                        // 세션에 사용자 정보 저장
                        HttpSession session = req.getSession();
                        session.setAttribute("user", vo);

                        // 아이디 비번 일치 일치
                        if (guestLogin != null) {
                            // 게스트 로그인
                            resp.sendRedirect(req.getContextPath() + "/jsp/service/main.jsp");
                        } else if (hostLogin != null) {
                            // 호스트 로그인
                            resp.sendRedirect(req.getContextPath() + "/jsp/service/hostMain.jsp");
                        }
                    } else {
                        // 아이디 비밀번호 불일치
                        req.setAttribute("errMsg", "아이디, 비밀번호를 확인하세요");
                        req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // 아이디 비밀번호에 null 값이 있는 경우
                req.setAttribute("errMsg", "아이디와 비밀번호를 모두 채워주세요.");
                req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}