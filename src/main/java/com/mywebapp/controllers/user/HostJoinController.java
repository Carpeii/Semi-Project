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
        String bankName = req.getParameter("bankName");
        String account = req.getParameter("account");
        String accountHolder = req.getParameter("accountHolder");
        String errMsg = null;

        // 앞에서 기재한 아이디 가져오기
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userId");

        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

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

        // session으로 가져온 memberId로 id조회
        try {
            conn = JdbcUtil.getCon();

            String selectSql = "select id from member where user_id=?";
            pstmt = conn.prepareStatement(selectSql);
            pstmt.setString(1, userId); //session으로 가져온 userId 값 넣어서 조회
            rs = pstmt.executeQuery();

            int memberId = -1; // userId가  존재하지 않는 경우 -1을 리턴

            if(rs.next()){
                memberId = rs.getInt("id"); // member 테이블에서 조회된 id값 찾아서 변수에 넣어줌

                String insertSql = "INSERT INTO host (member_id, bank_name, account, account_holder) VALUES (?, ?, ?, ?)";
                pstmt.close(); // 기존 prepatredStatement 닫고 새로운 Pstmt 생성
                pstmt = conn.prepareStatement(insertSql);
                pstmt.setLong(1, memberId); // 조회한 id값 insert
                pstmt.setString(2, bankName);
                pstmt.setString(3, account);
                pstmt.setString(4, accountHolder);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    // 로그인 성공 -> 로그인 페이지로 리다이렉트
                    resp.sendRedirect(req.getContextPath() + "/hostMain");
                } else {
                    // 로그인 실패 -> 다시 회원가입 폼으로
                    req.setAttribute("errMsg", "회원가입에 실패했습니다. 다시 시도해주세요.");
                    req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
                }
            } else { // userId를 가져오지 못한 경우
                req.setAttribute("errMsg", "userId를 찾지 못했습니다. 회원가입을 다시 시도해주세요.");
                System.out.println("userId를 찾지 못했습니다." + userId);
                req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            // hostJoinForm에서 데이터 베이스 오류인경우
            // joinForm은 거쳤기 때문에 member테이블에 회원정보 있음 - 세션존재?
            // 존재하는 경우, 리다이렉트 해준 페이지에서 그대로 진행 가능
            // 존재하지 않는 경우 member테이블에서 기존의 정보 지워주고 처음부터 다시 실행해야 함
            e.printStackTrace();
            req.setAttribute("errMsg", "데이터베이스의 오류입니다. 관리자에게 문의하세요.");
            req.getRequestDispatcher("/jsp/auth/hostJoinForm.jsp").forward(req, resp);
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }
}
