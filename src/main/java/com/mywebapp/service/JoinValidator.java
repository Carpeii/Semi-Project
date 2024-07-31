package com.mywebapp.service;

import com.mywebapp.dao.MemberDao;
import com.mywebapp.dto.MemberDto;
import com.mywebapp.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinValidator {
    private String idErrMsg;
    private String errMsg;
    private String pwErrMsg;
    private int memberType;
    private String guest;
    private String host;


    // 아이디 중복 검사 메서드
    public boolean usedId(String id) throws SQLException {
        MemberDao member = new MemberDao(JdbcUtil.getCon());
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();

            // db에 존재하는 아이디인지 확인
            String sql = "select * from member where user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                idErrMsg = "사용할 수 없는 아이디 입니다.";
                return false; // 회원가입 불가
            } else {
                idErrMsg = "가능한 아이디 입니다!";
                return true; // 회원가입 가능
            }
        } catch (SQLException e) {
            e.printStackTrace();
            pwErrMsg = "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.";
            return false;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    // 비밀번호 확인하는 메서드
    public boolean checkPassword(String password, String pwConfirm) {
        if(password.equals(pwConfirm)) {
            pwErrMsg = "가능한 비밀번호 입니다!";
            return true;
        } else {
            pwErrMsg = "비밀번호가 일치하지 않습니다.";
            return false;
        }
    }
    // 회원가입 이력 조회 메서드
    public boolean RUNew(String name, String phone) throws SQLException {
        MemberDao member = new MemberDao(JdbcUtil.getCon());
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();
            String sql = "select * from member where name=? and phone=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                return false; // 회원가입 불가 - 기존 회원으로 로그인
            } else {
                return true; // 회원가입 가능
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errMsg = "데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.";
            return false;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
    }

    // 공백을 확인하고 데이터베이스에 회원 정보를 넣는 메서드
    public boolean notNull(String id, String password, String name, String phone) throws SQLException {
        MemberDao member = new MemberDao(JdbcUtil.getCon());
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getCon();
            if (id != null && password != null && name != null && phone != null) {
                String sql = "INSERT INTO member (user_id, password, name, phone, member_type) VALUES (?, PASSWORD(?), ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.setString(2, password);
                pstmt.setString(3, name);
                pstmt.setString(4, phone);
                // memberType - 0: 게스트, 1: 호스트
                if(guest != null) {
                    pstmt.setInt(5, 0);
                } else if (host != null) {
                    pstmt.setInt(5, 1);

                }
            } else {
                errMsg ="공백이 없게 모든 정보를 기입하세요";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errMsg ="데이터베이스 오류가 발생했습니다. 관리자에게 문의하세요.";
            return false;
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
            if (rs != null) conn.close();
            return true;
        }
    }

    // 클라이언트로부터 입력받은 데이터
    public static MemberDto extractUserFromRequeste(HttpServletRequest req) {
        MemberDto member = new MemberDto();
        member.setUserId(req.getParameter("id"));
        member.setPassword(req.getParameter("password"));
        member.setName(req.getParameter("name"));
        member.setPhone(req.getParameter("phone"));
        return member;
    }


    /////////////호스트 전용 메서드////////////

    //

    // 공백확인 메서드
//    public static String getErrorMessage(MemberDto member) {
//        if (isNullOrEmpty(member.getUserId())) {
//            return "아이디를 확인해주세요";
//        }
//        if (isNullOrEmpty(member.getPassword())) {
//            return "비밀번호를 확인해주세요";
//        }
//        if (isNullOrEmpty(member.getName())) {
//            return "이름을 확인해주세요";
//        }
//        if (isNullOrEmpty(member.getPhone())) {
//            return "휴대전화 번호를 확인해주세요";
//        }
//        return null;
//    }
}
