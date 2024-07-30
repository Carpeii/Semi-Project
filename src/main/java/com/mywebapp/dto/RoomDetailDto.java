package com.mywebapp.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomDetailDto {
    private String hostName;
    private String roomName;
    private String jibunAddress;
    private String streetAddress;
    private String addressDetail;
    private int floor;
    private double usableArea;
    private int roomCount;
    private int livingRoomCount;
    private int toiletCount;
    private int kitchenCount;
    private boolean duplex;
    private boolean elevator;
    private boolean park;
    private String parkDetail;
    private String roomType;
    private int minimumContract;
    private String imageName;
    private String imagePath;
    private int imageOrder;
    private String roomOption;
    private int rentPrice;
    private int longTerm;
    private int longTermDiscount;
    private int earlyCheckIn;
    private int earlyCheckInDiscount;
    private int maintenanceBill;
    private String maintenanceBillDetail;
    private boolean electricity;
    private boolean water;
    private boolean gas;
    private boolean internet;
    private int cleaningFee;
    private int refundType;
    private Date checkInDate;
    private Date checkOutDate;
    private String reviewMessage;
    private int rating;
    private Date reviewCreatedAt;




}
