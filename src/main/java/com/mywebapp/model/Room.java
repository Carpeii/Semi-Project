package com.mywebapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Room {
    private long id;
    private String hostId;
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

    private List<RoomImage> roomImageList;
    private RoomOption roomOption;
    private RoomPrice roomPrice;

    public Room() {}

    public Room(long id, String hostId, String roomName, String jibunAddress, String streetAddress,
                String addressDetail,int floor, int usableArea, int roomCount, int livingRoomCount,
                int toiletCount, int kitchenCount, boolean duplex, boolean elevator,
                boolean park, String parkDetail, int roomType, int minimumContract,
                int approve, List<RoomImage> roomImageList, RoomOption roomOption, RoomPrice roomPrice) {
        this.id = id;
        this.hostId = hostId;
        this.roomName = roomName;
        this.jibunAddress = jibunAddress;
        this.streetAddress = streetAddress;
        this.addressDetail = addressDetail;
        this.floor = floor;
        this.usableArea = usableArea;
        this.roomCount = roomCount;
        this.livingRoomCount = livingRoomCount;
        this.toiletCount = toiletCount;
        this.kitchenCount = kitchenCount;
        this.duplex = duplex;
        this.elevator = elevator;
        this.park = park;
        this.parkDetail = parkDetail;
        this.roomType = roomType;
        this.minimumContract = minimumContract;
        this.approve = approve;
        this.roomImageList = roomImageList;
        this.roomOption = roomOption;
        this.roomPrice = roomPrice;
    }
}
