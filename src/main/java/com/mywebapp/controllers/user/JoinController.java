package com.mywebapp.controllers.user;

import com.mywebapp.dto.MemberDto;
import com.mywebapp.service.JoinValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/joinOk")
public class JoinController extends HttpServlet {
    private JoinValidator joinValidator = new JoinValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 기능에 대한 메서드를 생성해서 가져와서 쓰기
        MemberDto member = JoinValidator.extractUserFromRequeste(req);

        // 비밀번호 확인
        String pwConfirm = req.getParameter("pwConfirm");

        boolean isIdValid = false;
        boolean isPasswordValid = false;
        boolean isInputValid = false;
        boolean isUserValid = false;

        try {
            // 기존회원인지 확인
            isUserValid = joinValidator.RUNew(member.getName(), member.getPhone());

            if(isUserValid) { // 새로운 회원
                // 아이디 중복 검사
                isIdValid = joinValidator.usedId(member.getUserId());
                // 비밀번호 확인
                isPasswordValid = joinValidator.checkPassword(member.getPassword(), pwConfirm);
                // db에 회원정보 저장
                if (isIdValid && isPasswordValid) {
                    isInputValid = joinValidator.notNull(member.getUserId(), member.getPassword(), member.getName(), member.getPhone());
                } else {
                    req.setAttribute("error", "아이디 또는 비밀번호가 유효하지 않습니다.");
                }

                // 리다이렉트
                if (isInputValid) {
                    resp.sendRedirect("success.jsp");
                } else {
                    req.setAttribute("errMsg", "회원가입에 실패했습니다. 다시 시도해주세요.");
                    req.getRequestDispatcher(req.getContextPath() + "/auth/joinForm.jsp").forward(req, resp);
                }
            } else {
                // 기존 회원인 경우
                req.setAttribute("errMsg", "기존 회원입니다. 로그인해주세요.");
                req.getRequestDispatcher(req.getContextPath() +"/auth/joinForm.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errMsg", "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.");
            req.getRequestDispatcher(req.getContextPath() +"/auth/joinForm.jsp").forward(req, resp);
        }
    }

//        // 입력 다 받아왔는지 확인하는 메서드
//        String errMsg = UseValidator.getErrorMessage(member);
//        if (errMsg != null) {
//            setErrorAndForward(req, resp, errMsg);
//            return;
//        }
//
//        // 비밀번호 확인
//        if (!member.getPassword().equals(req.getParameter("pwCon"))) {
//            req.setAttribute("errMsg", "회원가입에 실패했습니다. 다시 시도해주세요.");
//            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
//        }
//
//        // 0: 게스트, 1: 호스트
//        int memberType = 0;
//        if (req.getParameter("guest") != null) {
//            memberType = 0;
//        } else if (req.getParameter("host") != null) {
//            memberType = 1;
//        }
//
//
//        MemberDao mem = new MemberDao(JdbcUtil.getCon());
//        PreparedStatement pstmt = null;
//        Connection conn = null;
//        ResultSet rs = null;
//
//        try {
//            conn = JdbcUtil.getCon();
//
//            // 회원가입 이력이 있는지 확인
//            String RUmember = "select * from member where user_id = ? and member_type = ?";
//            pstmt = conn.prepareStatement(RUmember);
//            pstmt.setString(1, member.getUserId());
//            pstmt.setInt(2, memberType);
//            rs = pstmt.executeQuery();
//            if(rs.next()) {
//                // 이미 가입된 사용자
//                req.setAttribute("errMsg", "이미 가입된 회원입니다. 로그인 해주세요.");
//                req.getRequestDispatcher("/jsp/auth/loginMain.jsp").forward(req, resp);
//                return;
//            }
//
//
//            // 아이디 중복검사
//            String existId = "select * from member where user_id = ?";
//            pstmt.setString(1, member.getUserId());
//            rs = pstmt.executeQuery();
//            if(rs.next()) {
//                // 아이디가 존재
//                req.setAttribute("idErrMsg", "아이디가 존재합니다.");
//            }
//
//
//            // member로 insert 하는 쿼리문
//            //String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, ?, ?, ?, ?)";
//            String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, PASSWORD(?), ?, ?, ?)";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, member.getUserId());
//            pstmt.setString(2, member.getPassword());
//            pstmt.setString(3, member.getName());
//            pstmt.setString(4, member.getPhone());
//            pstmt.setInt(5, memberType);
//
//            int rowsAffected = pstmt.executeUpdate();
//            if (rowsAffected > 0) {
//                //if(req.getParameter("guest") != null){
//                if(memberType == 0){
//                    // 로그인 성공 -> 로그인 페이지로 리다이렉트
//                    resp.sendRedirect(req.getContextPath() + "/jsp/auth/loginMain.jsp");
//                //} else if (req.getParameter("host") != null) {
//                } else if (memberType == 1) {
//                    // 호스트는 추가 작성 페이지로
//                    resp.sendRedirect(req.getContextPath() + "/jsp/auth/hostJoinForm.jsp");
//                }
//            } else {
//                req.setAttribute("errMsg", "회원가입에 실패했습니다. 다시 시도해주세요.");
//                req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();  // 에러 메시지를 콘솔에 출력
//            req.setAttribute("errMsg", "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.");
//            req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
//        } finally {
//            try {
//                if (pstmt != null) pstmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private MemberDto extractUserFromRequest(HttpServletRequest req) {
//        MemberDto member = new MemberDto();
//        member.setUserId(req.getParameter("id"));
//        member.setPassword(req.getParameter("pw"));
//        member.setName(req.getParameter("name"));
//        member.setPhone(req.getParameter("phone"));
//        member.setMemberType(Integer.parseInt(req.getParameter("guest")));
//        member.setMemberType(Integer.parseInt(req.getParameter("host")));
//        // String host = req.getParameter("host");
//        // member.setMemberType(Integer.parseInt(req.getParameter("memberType")));
//        String pwCon = req.getParameter("pwConfirm");
//        return member;
//    }
//
//    private void setErrorAndForward(HttpServletRequest req, HttpServletResponse resp, String errMsg) throws ServletException, IOException {
//        req.setAttribute("errMsg", errMsg);
//        req.getRequestDispatcher("/jsp/auth/joinForm.jsp").forward(req, resp);
//    }
}