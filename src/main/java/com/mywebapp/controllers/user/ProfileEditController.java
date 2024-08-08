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

@WebServlet("/user/edit")
public class ProfileEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(); // 수정 페이지에 기본정보 자동으로 입력되어있도록 -> null이 아님
        MemberDto user = (MemberDto) session.getAttribute("user");

        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String newPassword = req.getParameter("newPassword");
        String pwConfirm = req.getParameter("pwConfirm");

        // 비밀번호 확인되지 않으면 개인정보 수정 불가
        if (password.isEmpty()) {
            req.setAttribute("errMsg", "비밀번호를 입력하세요.");
            req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
        }

        MemberDao dao = new MemberDao();
        boolean passwordCheck = dao.passwordCheck(password, user);
        boolean updateMember = dao.updateMember(name, phone, user);
        boolean changePassword = dao.changePassword(newPassword, user);
        if (!passwordCheck) {
            req.setAttribute("pwErrMsg", "비밀번호를 확인하세요.");
            req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
            return;
        }
        if (!updateMember) {
            req.setAttribute("errMsg", "회원정보 수정 실패");
            req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
            return;
        }

        if (newPassword.isEmpty() && pwConfirm.isEmpty()) { // 둘 다 비어있지 않은 경우
//            user.setId(user.getId());
            user.setName(name);
            user.setPhone(phone);
            // 세션에 수정된 정보 저장
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/user/myPage");
        } else if (newPassword.equals(pwConfirm)) { // 비밀번호 수정하는 경우
            if (!changePassword) {
                req.setAttribute("newPwErrMsg", "비밀번호 수정에 실패하였습니다.");
                req.getRequestDispatcher("/jsp/user/profile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/user/myPage");
            }
        }
    }
}