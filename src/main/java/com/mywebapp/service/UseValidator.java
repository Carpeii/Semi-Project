package com.mywebapp.service;

import com.mywebapp.dto.MemberDto;

public class UseValidator {
    public static String getErrorMessage(MemberDto member) {
        if (isNullOrEmpty(member.getUserId())) {
            return "아이디를 확인해주세요";
        }
        if (isNullOrEmpty(member.getPassword())) {
            return "비밀번호를 확인해주세요";
        }
        if (isNullOrEmpty(member.getName())) {
            return "이름을 확인해주세요";
        }
        if (isNullOrEmpty(member.getPhone())) {
            return "휴대전화 번호를 확인해주세요";
        }
        return null;
    }

    // 입력값 검증 메서드
    public static boolean isNullOrEmpty(String param) {
        return param == null || param.trim().isEmpty();
    }
}
