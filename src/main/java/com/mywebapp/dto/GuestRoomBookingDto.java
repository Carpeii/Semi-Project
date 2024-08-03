package com.mywebapp.dto;

import java.sql.Date;

public class GuestRoomBookingDto {
    // Room Table Fields
    private long roomId;
    private String roomName;
    private String jibunAddress;
    private String streetAddress;
    private String addressDetail;
    private int floor;
    private int usableArea;
    private int roomCount;
    private int livingRoomCount;
    private int toiletCount;
    private int kitchenCount;
    private boolean duplex;
    private boolean elevator;
    private boolean park;
    private String parkDetail;
    private int roomType;
    private int minimumContract;
    private int approve;

    // Booking Table Fields
    private long guestId;
    private Date checkInDate;
    private Date checkOutDate;
    private int bookingStatus;

}
