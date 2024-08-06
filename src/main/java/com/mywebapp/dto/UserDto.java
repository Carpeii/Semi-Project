package com.mywebapp.dto;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpSession;

@Getter
@Setter
public class UserDto {
    private long id;
//    private String password;
    private String userId;
    private int memberType;
    private String name;
    private String phone;

    public UserDto() {}
}

