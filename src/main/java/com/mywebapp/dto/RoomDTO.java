package com.mywebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
	  private Long id;
	    private Long hostId;
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

}
