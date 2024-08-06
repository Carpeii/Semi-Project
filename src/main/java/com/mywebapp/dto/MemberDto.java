package com.mywebapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter @Setter
public class MemberDto {
    private long id;
    private String userId;
    private String password;
    private String name;
    private String phone;
    private int memberType;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public MemberDto(){
    }
}