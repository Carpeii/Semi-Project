package com.mywebapp.servlets;

import org.example.semiproject.HelloServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginServlet extends HelloServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        if(id != null && pw != null) { // 공백이 아니고 값이 다 있는 경우
            // if(req.getParameter("guestLogin") != null) {
                //resp.sendRedirect(req.getContextPath() + "/auth/guestLoginOk.jsp");
                req.getRequestDispatcher("/guestLoginOk.jsp").forward(req, resp);
            } else if(req.getParameter("hostLogin") != null) {
                //resp.sendRedirect(req.getContextPath() + "/auth/hostLoginOk.jsp");
                req.getRequestDispatcher("/hostLoginOk.jsp").forward(req, resp);
            }
        }
    }
}
