package com.mywebapp.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter @Setter
public class Member {
    private long id;
    private String userId;
    private String password;
    private String name;
    private String phone;
    private int memberType;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private Host host;

    public Member(){
    }

    public Member(long id, String userId, String password, String name, String phone, int memberType, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.memberType = memberType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
