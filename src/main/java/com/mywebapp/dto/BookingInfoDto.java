package com.mywebapp.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookingInfoDto {
    private String roomName;
    private String jibunAddress;
    private String streetAddress;
    private String addressDetail;
    private int floor;
    private String memberName;
    private String phone;
    private Date checkInDate;
    private Date checkOutDate;
    private int rentPrice;
    private int longTermDiscount;
    private int earlyCheckInDiscount;
    private int maintenanceBill;
    private int cleaningFee;
}
