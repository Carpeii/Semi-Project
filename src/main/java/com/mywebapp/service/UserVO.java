package com.mywebapp.service;

import java.sql.Connection;

public class UserVO {

    private String id;
    private String password;

    public UserVO() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
