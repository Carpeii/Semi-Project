package com.mywebapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomOption {
    private long roomId;
    private String roomOptions;

    public RoomOption() {}

    public RoomOption(long roomId, String roomOptions) {
        this.roomId = roomId;
        this.roomOptions = roomOptions;
    }
}
