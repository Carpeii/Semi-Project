package com.mywebapp.controllers.admin;

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

import static com.mywebapp.dao.MemberDao.checkUserID;
import static java.lang.Boolean.parseBoolean;

@WebServlet("/admin/adminJoin")
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

        MemberDao dao = new MemberDao();
        dao.joinMember(dto);

        HttpSession session = req.getSession();
        session.setAttribute("userId", userId); // session에 userId 저장

        // 회원가입 성공 -> 메인 페이지로 이동
        resp.sendRedirect(req.getContextPath() + "/admin/adminMain.jsp");
    }
}
