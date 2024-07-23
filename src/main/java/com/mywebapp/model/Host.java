package com.mywebapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Host {
    private long memberId;
    private String hostName;
    private String account;
    private String account_holder;

    public Host() {
    }
    public Host(Long memberId, String hostName, String account, String account_holder) {
        this.memberId = memberId;
        this.hostName = hostName;
        this.account = account;
        this.account_holder = account_holder;
    }
}