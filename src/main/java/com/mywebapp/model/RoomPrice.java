package com.mywebapp.model;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomPrice {
    private long roomId;
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
    private boolean test;
    private int cleaningFee;
    private int refundType;

    // Method to set RoomPrice fields from HttpServletRequest
    public RoomPrice toSetRoomPrice(HttpServletRequest req) {
        this.roomId = Long.parseLong(req.getParameter("roomId"));
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
        return this;
    }
}