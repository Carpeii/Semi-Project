package com.mywebapp.controllers.user;

import com.mywebapp.dto.HostDto;
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

@WebServlet("/user/editHost")
public class HostProfileEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/user/hostProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        HostDto host = (HostDto) session.getAttribute("host");

        String bankName = req.getParameter("bankName");
        String account = req.getParameter("account");
        String accountHolder = req.getParameter("accountHolder");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();
            String updateSql = "update host set bank_name = ?, account = ?, account_holder = ? where member_id = ?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, bankName);
            pstmt.setString(2, account);
            pstmt.setString(3, accountHolder);
            pstmt.setLong(4, host.getMemberId());
            int result = pstmt.executeUpdate();
            System.out.println(result);

            if(result == 0) {
                req.setAttribute("errMsg", "정보 수정에 실패하였습니다.");
                req.getRequestDispatcher("/jsp/user/hostProfile.jsp").forward(req, resp);
            }
            // 업데이트가 성공한 경우
            String selectSql = "select * from host where member_id = ?";
            pstmt = conn.prepareStatement(selectSql);
            pstmt.setLong(1, host.getMemberId());
            rs = pstmt.executeQuery();

            if(rs.next()) {
                HostDto updateHost = new HostDto();
//                updateHost.setBankName(rs.getString("bankName"));
//                updateHost.setAccount(rs.getString("account"));
//                updateHost.setAccount_holder(rs.getString("accountHolder"));
                updateHost.setMemberId(host.getMemberId());
                updateHost.setBankName(bankName);
                updateHost.setAccount(account);
                updateHost.setAccountHolder(accountHolder);
                // 세션에 수정된 정보 저장
                session.setAttribute("host", updateHost);
            }
            resp.sendRedirect(req.getContextPath() + "/hostMain");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errMsg", e.getMessage());
            req.getRequestDispatcher("/jsp/user/hostProfile.jsp").forward(req, resp);
        } finally {
            JdbcUtil.close(conn, pstmt, rs);
        }
    }
}
