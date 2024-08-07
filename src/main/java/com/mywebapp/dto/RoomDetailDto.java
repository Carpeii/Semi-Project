package com.mywebapp.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Getter @Setter
public class RoomDetailDto {
    private long id;
    private long hostId;
    private String hostName;
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
    
    // room_image
    private String imageName;
    private String imagePath;
    private String saveFileName;
    private int imageOrder;
    
    // room_option
    private String roomOptions;
    
    // 
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
    private String reviewMessage;
    private int rating;
    private Date reviewCreatedAt;
    private Date checkInDate;
    private Date checkOutDate;

    public void setRoomByRequest(HttpServletRequest req){
        hostId = Long.parseLong(req.getParameter("hostId"));
        roomName = req.getParameter("roomName");
        jibunAddress = req.getParameter("jibunAddress");
        streetAddress = req.getParameter("streetAddress");
        addressDetail = req.getParameter("addressDetail");
        floor = Integer.parseInt(req.getParameter("floor"));
        usableArea = Integer.parseInt(req.getParameter("usableArea"));
        roomCount = Integer.parseInt(req.getParameter("roomCount"));
        livingRoomCount = Integer.parseInt(req.getParameter("livingRoomCount"));
        toiletCount = Integer.parseInt(req.getParameter("toiletCount"));
        kitchenCount = Integer.parseInt(req.getParameter("kitchenCount"));
        duplex = Boolean.parseBoolean(req.getParameter("duplex"));
        elevator = Boolean.parseBoolean(req.getParameter("elevator"));
        park = Boolean.parseBoolean(req.getParameter("park"));
        parkDetail = req.getParameter("parkDetail");
        roomType = Integer.parseInt(req.getParameter("roomType"));
        minimumContract = Integer.parseInt(req.getParameter("minimumContract"));
        approve = Integer.parseInt(req.getParameter("approve"));
    }
}
