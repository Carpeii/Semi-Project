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

import static com.mywebapp.dao.MemberDao.checkUserID;
import static java.lang.Boolean.parseBoolean;

@WebServlet("/auth/join")
public class JoinController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 아이디 중복 확인
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

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        // 아이디 중복 체크 버튼이 눌렸을 경우에
        if ("checkId".equals(action)) {
            checkUserID(req, resp);
            return;
        }

        if ("guest".equals(action) || "host".equals(action)) {
            if (isDuplicate) {
                req.setAttribute("errMsg", "아이디 중복 검사 필수 입니다.");
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
                return;
            }

            if(userId.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()) { // 공백이면 회원가입 안되게
                req.setAttribute("errMsg", "회원가입 폼을 모두 작성해주세요.");
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
            }

            // 비밀번호 확인
            if (!pwConfirm.equals(password)) {
                req.setAttribute("pwErrMsg", "비밀번호가 일치하지 않습니다.");
                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
                return;
            }

            // dto에 담기
            MemberDto dto = new MemberDto();
            dto.setUserId(userId);
            dto.setPassword(password);
            dto.setName(name);
            dto.setPhone(phone);

            // 회원가입으로 데이터베이스에 insert
            MemberDao dao = new MemberDao();
            dao.joinMember(dto);

            HttpSession session = req.getSession();
            session.setAttribute("userId", userId); // session에 userId 저장

            // 회원가입 성공 -> 메인 페이지로 이동
            if ("guest".equals(action)) { // 게스트 회원가입
                int memberType = 0;
                dto.setMemberType(memberType);
                resp.sendRedirect(req.getContextPath() + "/main.jsp");

            } else if ("host".equals(action)) { // 호스트 회원가입
                int memberType = 1;
                dto.setMemberType(memberType);
                req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp); // url변경 x
            }
        }
    }
}