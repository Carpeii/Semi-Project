package com.mywebapp.controllers.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 기본 사용자 자격 증명
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청에서 사용자 이름과 비밀번호를 가져옴
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 자격 증명을 확인
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            // 성공적인 로그인
            response.sendRedirect("success.jsp");
        } else {
            // 로그인 실패
            response.sendRedirect("error.jsp");
        }
    }
}