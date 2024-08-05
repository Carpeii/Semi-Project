package com.mywebapp.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GuestRoomBookingDto {
    // Room Table Fields
    private long roomId;
    private String roomName;
    private String jibunAddress;
    private String streetAddress;
    private String addressDetail;
    private int floor;
    
    // Booking Table Fields
    private long guestId;
    private Date checkInDate;
    private Date checkOutDate;
    private int bookingStatus;
    
    //room_price
    private int rentPrice;

}
