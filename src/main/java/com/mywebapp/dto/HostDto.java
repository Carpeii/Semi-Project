package com.mywebapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HostDto {
    private long memberId;
    private String bankName;
    private String account;
    private String accountHolder;

    public HostDto() {
    }
}