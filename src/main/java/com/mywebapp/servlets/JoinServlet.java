//package com.mywebapp.servlets;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.mywebapp.dto.JoinDto;
//import com.mywebapp.service.UserValidator;
//
//@WebServlet("/joinOk")
//public class JoinServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	JoinDto user = extractUserFromRequest(req);
//
//        // 입력값 검증
//        String errMsg = UserValidator.getErrorMessage(user);
//        if (errMsg != null) {
//            setErrorAndForward(req, resp, errMsg);
//            return;
//        }
//
//        // 회원 유형에 따른 페이지 이동
//        if (user.getGuest() != null) {
//            resp.sendRedirect(req.getContextPath() + "/jsp/auth/guestJoinOk.jsp");
//        } else if (user.getHost() != null) {
//            resp.sendRedirect(req.getContextPath() + "/jsp/auth/hostJoinOk.jsp");
//        } else {
//            setErrorAndForward(req, resp, "회원 유형을 선택해주세요.");
//        }
//
//    }
//    
//    // 요청에서 UserDTO를 추출하는 메서드
//    private JoinDto extractUserFromRequest(HttpServletRequest req) {
//    	JoinDto user = new JoinDto();
//        user.setId(req.getParameter("id"));
//        user.setPw(req.getParameter("pw"));
//        user.setPwConfirm(req.getParameter("pwConfirm"));
//        user.setName(req.getParameter("name"));
//        user.setPhone(req.getParameter("phone"));
//        user.setGuest(req.getParameter("guest"));
//        user.setHost(req.getParameter("host"));
//        return user;
//    }
//    
//    // 에러 메시지 설정 후 포워딩 메서드
//    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse resp, String errMsg) throws ServletException, IOException {
//        req.setAttribute("errMsg", errMsg);
//        req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
//    }
//}
