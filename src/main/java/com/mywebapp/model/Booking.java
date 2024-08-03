package com.mywebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@ToString
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Booking {
    private long id;
    private long guestId;
    private long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private int bookingStatus; // 0: 미확인, 1: 승인, 2: 거절

    private Member member;
    private Room room;
}
