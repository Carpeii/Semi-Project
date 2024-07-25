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

@WebServlet("/hostJoin")
public class HostJoinController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/auth/hostJoinForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String memberId = req.getParameter("memberId");
        String bankName = req.getParameter("bankName");
        String account = req.getParameter("account");
        String accountHolder = req.getParameter("accountHolder");

        // 앞에서 기재한 아이디 가져오기
        HttpSession session = req.getSession();
        String id = (String) session.getAttribute("id");

        // 유효성 검사
        if(bankName.equals("") || bankName == null) {
            req.setAttribute("errMsg", "은행 이름을 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
        }
        if(account.equals("") || account == null) {
            req.setAttribute("errMsg", "계좌를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
        }
        if(accountHolder.equals("") || accountHolder == null) {
            req.setAttribute("errMsg", "예금주를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
        }

        //Dto

        MemberDao member = new MemberDao(JdbcUtil.getCon());
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;



        try {
            conn = JdbcUtil.getCon();



            String sql = "INSERT INTO host (member_id, bank_name, account, account_holder) VALUES (?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, bankName);
            pstmt.setString(3, account);
            pstmt.setString(4, accountHolder);
            pstmt.executeUpdate();

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // 로그인 성공 -> 로그인 페이지로 리다이렉트
                resp.sendRedirect(req.getContextPath() + "/jsp/auth/loginMain.jsp");
            } else {
                req.setAttribute("errMsg", "회원가입에 실패했습니다. 다시 시도해주세요.");
                req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errMsg", "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.");
            req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
