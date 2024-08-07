package com.mywebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Room {
    private long id;
    private long hostId;
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

    public void setRoomByRequest(HttpServletRequest req){
        id = Long.parseLong(req.getParameter("id"));
        hostId = Long.parseLong(req.getParameter("hostId"));
        roomName = req.getParameter("roomName");
        jibunAddress = req.getParameter("jibunAddress");
        streetAddress = req.getParameter("streetAddress");
        addressDetail = req.getParameter("addressDetail");
        floor = Integer.parseInt(req.getParameter("floor"));
        usableArea = Integer.parseInt(req.getParameter("usableArea"));
        roomCount = Integer.parseInt(req.getParameter("roomCount"));
        livingRoomCount = Integer.parseInt(req.getParameter("livingRoomCount"));
        toiletCount = Integer.parseInt(req.getParameter("toileCount"));
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
