package com.mywebapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HostDto {
    private long memberId;
    private String bankName;
    private String account;
    private String account_holder;

    public HostDto() {
    }
    public HostDto(Long memberId, String bankName, String account, String account_holder) {
        this.memberId = memberId;
        this.bankName = bankName;
        this.account = account;
        this.account_holder = account_holder;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setHostName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount_holder() {
        return account_holder;
    }

    public void setAccount_holder(String account_holder) {
        this.account_holder = account_holder;
    }
}