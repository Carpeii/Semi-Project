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

    public MemberDto(long id, String userId, String password, String name, String phone, int memberType, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.memberType = memberType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Private로 선언한 변수를 밖에서 쓰기 위한 getter setter 생성
//    public String getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}