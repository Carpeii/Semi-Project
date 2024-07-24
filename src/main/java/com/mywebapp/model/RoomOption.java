package com.mywebapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomOption {
    private long roomId;
    private String roomOption;

    public RoomOption() {}

    public RoomOption(long roomId, String roomOption) {
        this.roomId = roomId;
        this.roomOption = roomOption;
    }
}
