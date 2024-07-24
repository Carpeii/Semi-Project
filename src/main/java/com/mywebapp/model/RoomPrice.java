package com.mywebapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomPrice {
    private long roomId;
    private int rentPrice;
    private int longTerm;
    private int longTermDiscount;
    private int earlyCheckIn;
    private int earlyCheckInDiscount;
    private int maintenanceBill;
    private int maintenanceBillDiscount;
    private boolean electricity;
    private boolean water;
    private boolean gas;
    private boolean internet;
    private int cleaningFee;
    private int refundType;

    public RoomPrice() {
    }
    
    
    
    public RoomPrice(long roomId, int rentPrice, int longTerm, int longTermDiscount, int earlyCheckIn, int earlyCheckInDiscount, int maintenanceBill, int maintenanceBillDiscount, boolean electricity, boolean water, boolean gas, boolean internet, int cleaningFee, int refundType) {
        this.roomId = roomId;
        this.rentPrice = rentPrice;
        this.longTerm = longTerm;
        this.longTermDiscount = longTermDiscount;
        this.earlyCheckIn = earlyCheckIn;
        this.earlyCheckInDiscount = earlyCheckInDiscount;
        this.maintenanceBill = maintenanceBill;
        this.maintenanceBillDiscount = maintenanceBillDiscount;
        this.electricity = electricity;
        this.water = water;
        this.gas = gas;
        this.internet = internet;
        this.cleaningFee = cleaningFee;
        this.refundType = refundType;
    }


    //RoomDao - search 에서 호출
	public RoomPrice(long roomId, int rentPrice, int longTermDiscount, int earlyCheckInDiscount) {
		super();
		this.roomId = roomId;
		this.rentPrice = rentPrice;
		this.longTermDiscount = longTermDiscount;
		this.earlyCheckInDiscount = earlyCheckInDiscount;
	}
}