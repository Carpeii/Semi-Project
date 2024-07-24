package com.mywebapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomOptionDto {
    private long roomId;
    private String roomOption;

    public RoomOptionDto() {}

    public RoomOptionDto(long roomId, String roomOption) {
        this.roomId = roomId;
        this.roomOption = roomOption;
    }
}
