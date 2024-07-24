package com.mywebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomListItemDto {
	private String image_path;
	private String image_name;
	private String room_name;
	private String street_address;
	private int rent_price;
	private String room_option;

}
