package com.mywebapp.servlets;

import org.example.semiproject.HelloServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/service/main.jsp").forward(request, response);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        String guestLogin = req.getParameter("guestLogin");
        String hostLogin = req.getParameter("hostLogin");

        if(guestLogin != null) { // 게스트 로그인 버튼을 눌렀고,
            if (id != null && pw != null) { // 공백이 아니고 값이 다 있는 경우
                // db연결해서 아이디와 비밀번호가 일치하는지 확인하기
                // 아직 db 없어서 예시로
                if (id.equals("test") && pw.equals("1234")) {
                    req.getSession().setAttribute("id", id);
                    resp.sendRedirect(req.getContextPath() + "/service/main.jsp");
                } else {
                    req.setAttribute("errMag", "아이디와 비밀번호를 확인하세요");
                    req.getRequestDispatcher("/auth/loginMain.jsp").forward(req, resp);
                }
                // req.getRequestDispatcher("/guestLoginOk.jsp").forward(req, resp);
            } else {
                req.setAttribute("errMag", "아이디와 비밀번호를 모두 채워주세요.");
                req.getRequestDispatcher("/auth/loginMain.jsp").forward(req, resp);
            }
        } else if (hostLogin != null) {
            if (id != null && pw != null) { // 공백이 아니고 값이 다 있는 경우
                // db연결해서 아이디와 비밀번호가 일치하는지 확인하기
                if (id.equals("test") && pw.equals("1234")) {
                    req.getSession().setAttribute("id", id);
                    resp.sendRedirect(req.getContextPath() + "/service/hostMain.jsp");
                } else {
                    req.setAttribute("errMag", "아이디, 비밀번호를 확인하세요");
                    req.getRequestDispatcher("/auth/loginMain.jsp").forward(req, resp);
                    // 로그인처럼 보안이 필요한 이동은 RequestDispatcher 써야함
                    // 사용자 화면 url에서 이동이 보이지 않음
                }
                // req.getRequestDispatcher("/guestLoginOk.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/auth/loginMain.jsp").forward(req, resp);
            }
        }
    }
}
