package com.mywebapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomImage {
    private long id;
    private long roomId;
    private String imageName;
    private String imagePath;
    private int imageOrder;

    public RoomImage(){
    }

    public RoomImage(long id, long roomId, String imageName, String imagePath, int imageOrder) {
        this.id = id;
        this.roomId = roomId;
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.imageOrder = imageOrder;
    }
}
