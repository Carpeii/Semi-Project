package com.mywebapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RoomDto {
    private long id;
    private String hostId;
    private String roomName;
    private String jibunAddress;
    private String streetAddress;
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

    private List<RoomImageDto> roomImageDtoList;
    private RoomOptionDto roomOptionDto;
    private RoomPriceDto roomPriceDto;


    public RoomDto() {}

    public RoomDto(long id, String hostId, String roomName ,String jibunAddress, String streetAddress,
                   int floor, int usableArea, int roomCount, int livingRoomCount,
                   int toiletCount, int kitchenCount, boolean duplex, boolean elevator,
                   boolean park, String parkDetail, int roomType, int minimumContract, int approve) {
        this.id = id;
        this.hostId = hostId;
        this.roomName = roomName;
        this.jibunAddress = jibunAddress;
        this.streetAddress = streetAddress;
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
    }
}
