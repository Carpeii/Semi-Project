package com.mywebapp.controllers.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/myPage")
public class MyPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/auth/myPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

//        String hostBooking = req.getParameter("hostBooking");
//        String hostEdit = req.getParameter("hostEdit");
//        String guestBooking = req.getParameter("guestBooking");
//        String guestEdit = req.getParameter("guestEdit");

        if("hostBooking".equals(action)) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/myPageHostBooking.jsp");
        } else if ("hostEdit".equals(action)) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/profile.jsp");
        } else if ("guestBooking".equals(action)) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/myPageGuestBooking.jsp");
        } else if ("guestEdit".equals(action)) {
            resp.sendRedirect(req.getContextPath() + "/jsp/user/profile.jsp");
        }
    }
}
