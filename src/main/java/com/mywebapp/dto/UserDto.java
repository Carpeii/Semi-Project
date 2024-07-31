package com.mywebapp.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String password;

    public UserDto() {}
}