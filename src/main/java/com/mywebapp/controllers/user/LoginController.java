package com.mywebapp.controllers.user;

import com.mywebapp.dao.MemberDao;
import com.mywebapp.dto.MemberDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        // 공백란 존재
        if (userId.isEmpty() || password.isEmpty()) {
            req.setAttribute("errMsg", "아이디와 비밀번호 모두 기입");
            req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
        }

        MemberDao dao = new MemberDao();
        MemberDto dto = dao.loginMember(userId, password);

        HttpSession session = req.getSession();
        session.setAttribute("user", dto);
        session.setMaxInactiveInterval(30 * 60);

        if(dto.getMemberType() == 0) {
            resp.sendRedirect(req.getContextPath() + "/guestMain");
        } else if (dto.getMemberType() ==1) {
            resp.sendRedirect(req.getContextPath() + "/hostMain");
        } else if (dto.getMemberType() == 3) {
            resp.sendRedirect(req.getContextPath() + "/adminMain");
        }
    }
}