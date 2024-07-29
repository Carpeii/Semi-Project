package com.mywebapp.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class Booking {
    private long id;
    private long guestId;
    private long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private int bookingStatus; // 0: 미확인, 1: 승인, 2: 거절

    private Member member;
    private Room room;

    public Booking() {
    }

    public Booking(long id, long guestId, long roomId, Date checkInDate, Date checkOutDate, int bookingStatus) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingStatus = bookingStatus;
    }
}
