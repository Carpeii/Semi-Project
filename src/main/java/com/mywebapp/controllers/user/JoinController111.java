//package com.mywebapp.controllers.user;
//
//import com.mywebapp.dto.MemberDto;
//import com.mywebapp.service.JoinValidator;
//import com.mywebapp.util.JdbcUtil;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@WebServlet("/joinOk")
//public class JoinController111 extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        // 기능에 대한 메서드를 생성해서 가져와서 쓰기
//        MemberDto member = JoinValidator.extractUserFromRequest(req);
//
//        // 비밀번호 확인
//        String pwConfirm = req.getParameter("pwConfirm");
//        String password = req.getParameter("password");
//        String pwErrMsg = req.getParameter("pwErrMsg");
//
//
//        // 아이디 중복확인을 위한 변수
//        String action = req.getParameter("action");
//        String userId = req.getParameter("userId");
//
//        // 에러 메세지 띄우기 위한 변수
//        String idErrMsg = req.getParameter("idErrMsg");
//        String errMsg = req.getParameter("errMsg");
//
//        PreparedStatement pstmt = null;
//        Connection conn = null; // conn = JdbcUtil.getCon(); 이건 왜 오류??
//        ResultSet rs = null;
//        
//        // 아이디 중복 체크
//        if (action.equals("userId")) {
////            PreparedStatement pstmt = null;
////            Connection conn = null; // conn = JdbcUtil.getCon(); 이건 왜 오류??
////            ResultSet rs = null;
//
//            try {
//                conn = JdbcUtil.getCon();
//
//                // db에 존재하는 아이디인지 확인
//                String sql = "select * from member where user_id=?";
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1, userId);
//                rs = pstmt.executeQuery();
//
//                if (rs.next()) {
//                    idErrMsg = "사용할 수 없는 아이디 입니다.";
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                idErrMsg = "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.";
//            } finally {
//                JdbcUtil.close(conn, pstmt, rs);
//            }
//        } else {
//            // 비밀번호 == 비밀번호 확인
//            if (!pwConfirm.equals("password")) {
//                pwErrMsg = "비밀번호가 일치하지 않습니다.";
//            } else {
//                pwErrMsg = "일치하는 비밀번호 입니다!";
////                
////                Connection conn = null;
////                PreparedStatement pstmt = null;
//
//                // db 에 회원정보 저장
//                if (id != null && password != null && name != null && phone != null) {
//                    String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, PASSWORD(?), ?, ?, ?)";
//                    pstmt = conn.prepareStatement(sql);
//                    pstmt.setString(1, id);
//                    pstmt.setString(2, password);
//                    pstmt.setString(3, name);
//                    pstmt.setString(4, phone);
//                    // memberType - 0: 게스트, 1: 호스트
//                    if (guest != null) {
//                        pstmt.setInt(5, 0);
//                    } else if (host != null) {
//                        pstmt.setInt(5, 1);
//                    } else {
//                        errMsg = "공백이 없게 모든 정보를 기입하세요";
//                    }
//                }
//            }
//
//
////           isIdValid = joinValidator.usedId(member.getUserId());
////            if (isIdValid) {
////                req.setAttribute("idErrMsg", "사용가능한 아이디 입니다.");
////
////                    if (isUserValid) { // 새로운 회원
////                        // 비밀번호 확인
////                        isPasswordValid = joinValidator.checkPassword(member.getPassword(), pwConfirm);
////                        // db에 회원정보 저장
////                        if (isPasswordValid) {
////                            isInputValid = joinValidator.notNull(member.getUserId(), member.getPassword(), member.getName(), member.getPhone());
////                        } else {
////                            req.setAttribute("error", "아이디 또는 비밀번호가 유효하지 않습니다.");
////                        }
////
////                        // 리다이렉트
////                        if (isInputValid) {
////                            resp.sendRedirect("success.jsp");
////                        } else {
////                            req.setAttribute("errMsg", "회원가입에 실패했습니다. 다시 시도해주세요.");
////                            req.getRequestDispatcher(req.getContextPath() + "/auth/joinForm.jsp").forward(req, resp);
////                        }
////                    } else {
////                        // 기존 회원인 경우
////                        req.setAttribute("errMsg", "기존 회원입니다. 로그인해주세요.");
////                        req.getRequestDispatcher(req.getContextPath() + "/auth/joinForm.jsp").forward(req, resp);
////                    }
////
////
////                    req.setAttribute("err" +
////                            "Msg", "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.");
////                    req.getRequestDispatcher(req.getContextPath() + "/auth/joinForm.jsp").forward(req, resp);
////                }
////
////            }
////
////        }
//
//
//        }
//
////    public void MemberDto member(HttpServletRequest req) {
////        MemberDto member = new MemberDto();
////        member.setUserId(req.getParameter("id"));
////        member.setPassword(req.getParameter("password"));
////        member.setName(req.getParameter("name"));
////        member.setPhone(req.getParameter("phone"));
////        return member;
////    }
//    }
//}