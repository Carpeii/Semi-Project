package com.mywebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomListItemDto {
	private Long id; 
	private String imagePath;
	private String imageName;
	private String saveFileName;
	private String roomName;
	private String streetAddress;
	private int rentPrice;
	private String roomOption;

}
