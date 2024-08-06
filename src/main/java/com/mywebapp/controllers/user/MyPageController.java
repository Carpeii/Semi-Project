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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/auth/myPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String booking = req.getParameter("booking");
        String account = req.getParameter("account");
        String chat = req.getParameter("chat");

        if(booking != null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/myPageGuestBooking.jsp");
        } else if (account != null) {
            resp.sendRedirect(req.getContextPath() + "/edit");
        } else if (chat != null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/myPageGuestChat.jsp");
        }
    }
}
