package com.mywebapp.dto;

import java.util.ArrayList;

public class RoomDto {
	//room table
	private long id;
	private long hostId;
	private String roomName;
	private String jibunAddress;
	private String streetAddress;
	private String addressDetail;
	private  int floor;
	private int usableArea;
	private int roomCount;
	private int livingRoonCount;
	private int toiletCount;
	private int litchenCount;
	private int duplex;
	private int elevator;
	private int park;
	private String parkDetail;
	private int roomType;
	private int minimumContract;
	private int approve;
	
	
	//room price  table
	// id = room_id
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
	
	
	//room option table
	// id = room_id
	private ArrayList<String> roomOption;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getHostId() {
		return hostId;
	}


	public void setHostId(long hostId) {
		this.hostId = hostId;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public String getJibunAddress() {
		return jibunAddress;
	}


	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}


	public String getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}


	public String getAddressDetail() {
		return addressDetail;
	}


	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}


	public int getFloor() {
		return floor;
	}


	public void setFloor(int floor) {
		this.floor = floor;
	}


	public int getUsableArea() {
		return usableArea;
	}


	public void setUsableArea(int usableArea) {
		this.usableArea = usableArea;
	}


	public int getRoomCount() {
		return roomCount;
	}


	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}


	public int getLivingRoonCount() {
		return livingRoonCount;
	}


	public void setLivingRoonCount(int livingRoonCount) {
		this.livingRoonCount = livingRoonCount;
	}


	public int getToiletCount() {
		return toiletCount;
	}


	public void setToiletCount(int toiletCount) {
		this.toiletCount = toiletCount;
	}


	public int getLitchenCount() {
		return litchenCount;
	}


	public void setLitchenCount(int litchenCount) {
		this.litchenCount = litchenCount;
	}


	public int getDuplex() {
		return duplex;
	}


	public void setDuplex(int duplex) {
		this.duplex = duplex;
	}


	public int getElevator() {
		return elevator;
	}


	public void setElevator(int elevator) {
		this.elevator = elevator;
	}


	public int getPark() {
		return park;
	}


	public void setPark(int park) {
		this.park = park;
	}


	public String getParkDetail() {
		return parkDetail;
	}


	public void setParkDetail(String parkDetail) {
		this.parkDetail = parkDetail;
	}


	public int getRoomType() {
		return roomType;
	}


	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}


	public int getMinimumContract() {
		return minimumContract;
	}


	public void setMinimumContract(int minimumContract) {
		this.minimumContract = minimumContract;
	}


	public int getApprove() {
		return approve;
	}


	public void setApprove(int approve) {
		this.approve = approve;
	}


	public int getRentPrice() {
		return rentPrice;
	}


	public void setRentPrice(int rentPrice) {
		this.rentPrice = rentPrice;
	}


	public int getLongTerm() {
		return longTerm;
	}


	public void setLongTerm(int longTerm) {
		this.longTerm = longTerm;
	}


	public int getLongTermDiscount() {
		return longTermDiscount;
	}


	public void setLongTermDiscount(int longTermDiscount) {
		this.longTermDiscount = longTermDiscount;
	}


	public int getEarlyCheckIn() {
		return earlyCheckIn;
	}


	public void setEarlyCheckIn(int earlyCheckIn) {
		this.earlyCheckIn = earlyCheckIn;
	}


	public int getEarlyCheckInDiscount() {
		return earlyCheckInDiscount;
	}


	public void setEarlyCheckInDiscount(int earlyCheckInDiscount) {
		this.earlyCheckInDiscount = earlyCheckInDiscount;
	}


	public int getMaintenanceBill() {
		return maintenanceBill;
	}


	public void setMaintenanceBill(int maintenanceBill) {
		this.maintenanceBill = maintenanceBill;
	}


	public String getMaintenanceBillDetail() {
		return maintenanceBillDetail;
	}


	public void setMaintenanceBillDetail(String maintenanceBillDetail) {
		this.maintenanceBillDetail = maintenanceBillDetail;
	}


	public boolean isElectricity() {
		return electricity;
	}


	public void setElectricity(boolean electricity) {
		this.electricity = electricity;
	}


	public boolean isWater() {
		return water;
	}


	public void setWater(boolean water) {
		this.water = water;
	}


	public boolean isGas() {
		return gas;
	}


	public void setGas(boolean gas) {
		this.gas = gas;
	}


	public boolean isInternet() {
		return internet;
	}


	public void setInternet(boolean internet) {
		this.internet = internet;
	}


	public int getCleaningFee() {
		return cleaningFee;
	}


	public void setCleaningFee(int cleaningFee) {
		this.cleaningFee = cleaningFee;
	}


	public int getRefundType() {
		return refundType;
	}


	public void setRefundType(int refundType) {
		this.refundType = refundType;
	}


	public ArrayList<String> getRoomOption() {
		return roomOption;
	}


	public void setRoomOption(ArrayList<String> roomOption) {
		this.roomOption = roomOption;
	}
	
	
	
}
