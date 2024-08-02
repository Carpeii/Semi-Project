package com.mywebapp.controllers.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myPage")
public class MyPageController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String booking = req.getParameter("booking");
        String account = req.getParameter("account");
        String chat = req.getParameter("chat");

        if(booking != null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/myPageGuestBooking.jsp");
        } else if (account != null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/profile.jsp");
        } else if (chat != null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/myPageGuestChat.jsp");
        }
    }
}
