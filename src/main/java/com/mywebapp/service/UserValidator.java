package com.mywebapp.service;

import com.mywebapp.dto.JoinDto;

public class UserValidator {

    // UserDTO를 검증하여 에러 메시지를 반환하는 메서드
    public static String getErrorMessage(JoinDto user) {
        if (isNullOrEmpty(user.getId())) {
            return "아이디를 확인해주세요";
        }
        if (isNullOrEmpty(user.getPw()) || isNullOrEmpty(user.getPwConfirm())) {
            return "비밀번호를 확인해주세요";
        }
        if (isNullOrEmpty(user.getName())) {
            return "이름을 확인해주세요";
        }
        if (isNullOrEmpty(user.getPhone())) {
            return "휴대전화 번호를 확인해주세요";
        }
        if (!user.getPw().equals(user.getPwConfirm())) {
            return "비밀번호와 확인이 맞지 않습니다.";
        }
        return null; // 에러가 없는 경우 null 반환
    }

    // 입력값 검증 메서드
    public static boolean isNullOrEmpty(String param) {
        return param == null || param.trim().isEmpty();
    }
}