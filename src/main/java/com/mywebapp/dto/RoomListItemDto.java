package com.mywebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomListItemDto {
	private String imagePath;
	private String imageName;
	private String roomName;
	private String streetAddress;
	private int rentPrice;
	private String roomOption;

}