package com.mywebapp.model;

import java.sql.Connection;

public class UserVO {
//    private Connection connection;

//    public UserVO(Connection connection) {
//        this.connection = connection;
//    }

    private String id;
    private String pw;

    public UserVO() {}

//    public UserVO(String id, String pw) {
//        this.id = id;
//        this.pw = pw;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
