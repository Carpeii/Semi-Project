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

@WebServlet("/auth/hostJoin")
public class HostJoinController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/auth/hostJoinForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String bankName = req.getParameter("bankName");
        String account = req.getParameter("account");
        String accountHolder = req.getParameter("accountHolder");

        // 앞에서 기재한 아이디 가져오기
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        // 유효성 검사
        if(bankName.isEmpty()) {
            req.setAttribute("errMsg", "은행 이름을 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
        }
        if(account.isEmpty()) {
            req.setAttribute("errMsg", "계좌를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
        }
        if(accountHolder.isEmpty()) {
            req.setAttribute("errMsg", "예금주를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
        }

        MemberDao dao = new MemberDao();
        boolean joinOk = dao.joinHostMember(userId, bankName, account, accountHolder);
        if(!joinOk) { // 회원가입 실패
            req.setAttribute("errMsg", "회원가입에 실패하였습니다.");
            req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/hostMain");
        }
    }
}
