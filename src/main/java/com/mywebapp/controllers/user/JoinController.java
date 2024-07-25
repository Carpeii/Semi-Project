package com.mywebapp.controllers.user;

import com.mywebapp.dao.MemberDao;
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

@WebServlet("/joinOk")
public class JoinController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 회원가입 폼 페이지로 이동
//        request.getRequestDispatcher("jsp/auth/join.jsp").forward(request, response);
        String id = req.getParameter("id");

        // 아이디 중복 검사
        MemberDao member = new MemberDao(JdbcUtil.getCon());
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();
            String sql = "select * from where user_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                if(rs.getString("id").equals(id)) {
                    req.setAttribute("idErrMsg", "중복된 아이디가 있습니다. 다른 아이디를 기입해주세요.");
                    req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("idErrMsg", "사용할 수 있는 아이디 입니다!");
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // 에러 메시지를 콘솔에 출력
            req.setAttribute("errMsg", "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        } finally {
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 요청에서 사용자 입력 데이터를 가져옴
        String id = req.getParameter("id");

        // 호스트 아이디 insert를 위한 세션에 아이디 저장
        HttpSession session = req.getSession();
        session.setAttribute("id", id);

        String pw = req.getParameter("pw");
        String pwCon = req.getParameter("pwConfirm");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String typeGuest = req.getParameter("guest");
        String typeHost = req.getParameter("host");

        // 기본적인 유효성 검사
        if(id.equals("") || id == null) {
            req.setAttribute("errMsg", "아이디를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        }
        if((pw.equals("") || pw == null) || (pwCon.equals("") || pwCon == null)) {
            req.setAttribute("errMsg", "비밀번호를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        }
        if(name.equals("") || name == null) {
            req.setAttribute("errMsg", "이름을 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        }
        if(phone.equals("") || phone == null) {
            req.setAttribute("errMsg", "휴대전화 번호를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        }

        // 비밀번호 확인
        if(!pw.equals(pwCon)) {
            req.setAttribute("errMsg", "비밀번호와 확인이 맞지 않습니다.");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        }

        // 0: 게스트, 1: 호스트
        int memberType = 0;
        if (typeGuest != null) {
            memberType = 0;
        } else if (typeHost != null) {
            memberType = 1;
        }


        MemberDao member = new MemberDao(JdbcUtil.getCon());
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();

            // 회원가입 이력이 있는지 확인
            String RUmember = "select * from member where user_id = ? and member_type = ?";
            pstmt = conn.prepareStatement(RUmember);
            pstmt.setString(1, id);
            pstmt.setInt(2, memberType);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                // 이미 가입된 사용자
                req.setAttribute("errMsg", "이미 가입된 회원입니다. 로그인 해주세요.");
                req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
                return;
            }

            // member로 insert 하는 쿼리문
            //String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, ?, ?, ?, ?)";
            String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, PASSWORD(?), ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, phone);
            pstmt.setInt(5, memberType);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                if(typeGuest != null){
                    // 로그인 성공 -> 로그인 페이지로 리다이렉트
                    resp.sendRedirect(req.getContextPath() + "/jsp/auth/loginMain.jsp");
                } else if (typeHost != null) {
                    // 호스트는 추가 작성 페이지로
                    resp.sendRedirect(req.getContextPath() + "/jsp/auth/hostJoinForm.jsp");
                }
            } else {
                req.setAttribute("errMsg", "회원가입에 실패했습니다. 다시 시도해주세요.");
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // 에러 메시지를 콘솔에 출력
            req.setAttribute("errMsg", "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}