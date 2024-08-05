package com.mywebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RoomImage {
    private long id;
    private long roomId;
    private String imageName;
    private String saveFileName;
    private String imagePath;
    private int imageOrder;
}
