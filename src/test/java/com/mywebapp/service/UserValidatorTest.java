package com.mywebapp.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.mywebapp.dto.JoinDto;
import com.mywebapp.service.UserValidator;

public class UserValidatorTest {

    @Test
    public void testGetErrorMessage_withEmptyId() {
        JoinDto user = new JoinDto();
        user.setId("");
        user.setPw("password");
        user.setPwConfirm("password");
        user.setName("John");
        user.setPhone("1234567890");

        String errMsg = UserValidator.getErrorMessage(user);
        assertEquals("아이디를 확인해주세요", errMsg);
    }

    @Test
    public void testGetErrorMessage_withEmptyPw() {
    	JoinDto user = new JoinDto();
        user.setId("testId");
        user.setPw("");
        user.setPwConfirm("");
        user.setName("John");
        user.setPhone("1234567890");

        String errMsg = UserValidator.getErrorMessage(user);
        assertEquals("비밀번호를 확인해주세요", errMsg);
    }

    @Test
    public void testGetErrorMessage_withEmptyName() {
    	JoinDto user = new JoinDto();
        user.setId("testId");
        user.setPw("password");
        user.setPwConfirm("password");
        user.setName("");
        user.setPhone("1234567890");

        String errMsg = UserValidator.getErrorMessage(user);
        assertEquals("이름을 확인해주세요", errMsg);
    }

    @Test
    public void testGetErrorMessage_withEmptyPhone() {
    	JoinDto user = new JoinDto();
        user.setId("testId");
        user.setPw("password");
        user.setPwConfirm("password");
        user.setName("John");
        user.setPhone("");

        String errMsg = UserValidator.getErrorMessage(user);
        assertEquals("휴대전화 번호를 확인해주세요", errMsg);
    }

    @Test
    public void testGetErrorMessage_withPwMismatch() {
        JoinDto user = new JoinDto();
        user.setId("testId");
        user.setPw("password");
        user.setPwConfirm("differentPassword");
        user.setName("John");
        user.setPhone("1234567890");

        String errMsg = UserValidator.getErrorMessage(user);
        assertEquals("비밀번호와 확인이 맞지 않습니다.", errMsg);
    }

    @Test
    public void testGetErrorMessage_withValidInput() {
    	JoinDto user = new JoinDto();
        user.setId("testId");
        user.setPw("password");
        user.setPwConfirm("password");
        user.setName("John");
        user.setPhone("1234567890");

        String errMsg = UserValidator.getErrorMessage(user);
        assertNull(errMsg); // 에러 메시지가 없어야 함
    }
}
