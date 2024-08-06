package com.mywebapp.controllers.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/auth/logout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); // false를 전달해서 세션이 존재하면 세션 반환, 존재하지 않으면 null 반환- > 새로운 세션 생성 방지
        if (session != null) {
            session.invalidate(); // session 날림
        }

        resp.sendRedirect(req.getContextPath() + "/guestMain");
    }
}
