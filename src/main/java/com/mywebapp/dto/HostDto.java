package com.mywebapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HostDto {
    private long memberId;
    private String hostName;
    private String account;
    private String account_holder;

    public HostDto() {
    }
    public HostDto(Long memberId, String hostName, String account, String account_holder) {
        this.memberId = memberId;
        this.hostName = hostName;
        this.account = account;
        this.account_holder = account_holder;
    }
}