package com.mywebapp.controllers.user;

import com.mywebapp.dao.MemberDao;
import com.mywebapp.dto.MemberDto;
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

@WebServlet("/adminJoin")
public class AdminJoinController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/amin/adminJoin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 아이디 중복 확인
        boolean isDuplicate = parseBoolean(req.getParameter("isDuplicate"));
        //isDuplicate = true;

        // 비밀번호 확인
        String pwConfirm = req.getParameter("pwConfirm");
        String password = req.getParameter("password");

        // 관리자는 무조건 3
        int memberType = 3;

        // 아이디 중복확인을 위한 변수
        String action = req.getParameter("action");
        String userId = req.getParameter("userId");

        // db 연결 변수
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 아이디 중복 체크 버튼이 눌렸을 경우에
        if ("checkId".equals(action)) {
            checkUserID(req, resp);
            return;
        }

        if (isDuplicate) {
            req.setAttribute("errMsg", "아이디 중복 검사 필수 입니다.");
            req.getRequestDispatcher("/jsp/admin/adminJoin.jsp").forward(req, resp);
            return;
        }

        // 비밀번호 확인
        if (!pwConfirm.equals(password)) {
            req.setAttribute("pwErrMsg", "비밀번호가 일치하지 않습니다.");
            req.getRequestDispatcher("/jsp/admin/adminJoin.jsp").forward(req, resp);
            return;
        }

        // dto에 담기
        MemberDto dto = new MemberDto();
        dto.setUserId(userId);
        dto.setPassword(password);
        dto.setName(name);
        dto.setPhone(phone);
        dto.setMemberType(memberType);

        // 회원가입으로 데이터베이스에 insert
        try {
            conn = JdbcUtil.getCon();
            MemberDao dao = new MemberDao(conn);
            dao.joinMember(dto);

            HttpSession session = req.getSession();
            session.setAttribute("userId", userId); // session에 userId 저장

            // 회원가입 성공 -> 메인 페이지로 이동
            resp.sendRedirect(req.getContextPath() + "/adminMain.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errMsg", "데이터베이스의 오류입니다. 관리자에게 문의하세요.");
            req.getRequestDispatcher("/jsp/admin/adminMain.jsp").forward(req, resp);
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }

    protected void checkUserID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 아이디 중복 체크 버튼이 눌렸을 경우에
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

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

            req.getRequestDispatcher("/jsp/admin/adminJoin.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("idErrMsg", "데이터베이스의 오류입니다. 관리자에게 문의하세요.");
            req.getRequestDispatcher("/jsp/admin/adminJoin.jsp").forward(req, resp);

        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }
}