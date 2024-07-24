package com.mywebapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/joinOk")
public class JoinServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        String pwConfirm = req.getParameter("pwConfirm");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String guest = req.getParameter("guest");
        String host = req.getParameter("host");

        // db 연결 전에 아이디, 비번 등 공백 확인
        if(id.equals("") || id == null) {
            req.setAttribute("errMsg", "아이디를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        }
        if((pw.equals("") || pw == null) || (pwConfirm.equals("") || pwConfirm == null)) {
            req.setAttribute("errMsg", "비밀번호를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        }
        if(name.equals("") || name == null) {
            req.setAttribute("errMsg", "이름을 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        }
        if(phone.equals("") || phone == null) {
            req.setAttribute("errMsg", "휴대전화 번호를 확인해주세요");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        }

        // 비밀번호 확인
        if(!pw.equals(pwConfirm)) {
            req.setAttribute("errMsg", "비밀번호와 확인이 맞지 않습니다.");
            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
        } else {
            if (guest != null) {
                resp.sendRedirect(req.getContextPath() + "/jsp/auth/guestJoinOk.jsp");
            } else if (host != null) {
                resp.sendRedirect(req.getContextPath() + "/jsp/auth/hostJoinOk.jsp");
            }
        }

    }
}
