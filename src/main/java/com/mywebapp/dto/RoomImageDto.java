package com.mywebapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomImageDto {
    private long id;
    private long roomId;
    private String imageName;
    private String saveFileName;
    private String imagePath;
    private int imageOrder;

    public RoomImageDto(){
    }

    public RoomImageDto(long id, long roomId, String imageName, String saveFileName, String imagePath, int imageOrder) {
        this.id = id;
        this.roomId = roomId;
        this.imageName = imageName;
        this.saveFileName = saveFileName;
        this.imagePath = imagePath;
        this.imageOrder = imageOrder;
    }
}
