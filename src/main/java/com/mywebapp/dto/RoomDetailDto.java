package com.mywebapp.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Getter @Setter
public class RoomDetailDto {
    //room
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
    
    // room_price
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

    public void setRoomPriceByRequest(HttpServletRequest req){
        this.rentPrice = Integer.parseInt(req.getParameter("rentPrice"));
        this.longTerm = Integer.parseInt(req.getParameter("longTerm"));
        this.longTermDiscount = Integer.parseInt(req.getParameter("longTermDiscount"));
        this.earlyCheckIn = Integer.parseInt(req.getParameter("earlyCheckIn"));
        this.earlyCheckInDiscount = Integer.parseInt(req.getParameter("earlyCheckInDiscount"));
        this.maintenanceBill = Integer.parseInt(req.getParameter("maintenanceBill"));
        this.maintenanceBillDetail = req.getParameter("maintenanceBillDetail");
        this.electricity = req.getParameter("electricity") != null;
        this.water = req.getParameter("water") != null;
        this.gas = req.getParameter("gas") != null;
        this.internet = req.getParameter("internet") != null;
        this.cleaningFee = Integer.parseInt(req.getParameter("cleaningFee"));
        this.refundType = Integer.parseInt(req.getParameter("refundType"));
    }
    
    public void setBookingInfoByRequest(HttpServletRequest req) {
        this.id = Long.parseLong(req.getParameter("roomId"));
        this.roomName = req.getParameter("roomName");
        this.jibunAddress = req.getParameter("jibunAddress");
        this.streetAddress = req.getParameter("streetAddress");
        this.addressDetail = req.getParameter("addressDetail");
        this.floor = Integer.parseInt(req.getParameter("floor"));
        this.hostName = req.getParameter("hostName");
        this.rentPrice = Integer.parseInt(req.getParameter("rentPrice"));
        this.longTermDiscount = Integer.parseInt(req.getParameter("longTermDiscount"));
        this.earlyCheckInDiscount = Integer.parseInt(req.getParameter("earlyCheckInDiscount"));
        this.maintenanceBill = Integer.parseInt(req.getParameter("maintenanceBill"));
        this.cleaningFee = Integer.parseInt(req.getParameter("cleaningFee"));
    }
}
