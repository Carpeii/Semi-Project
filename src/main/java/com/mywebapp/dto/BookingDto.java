package com.mywebapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class BookingDto {
    private long id;
    private long guestId;
    private long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private int bookingStatus;

    public BookingDto() {
    }

    public BookingDto(long id, long guestId, long roomId, Date checkInDate, Date checkOutDate, int bookingStatus) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingStatus = bookingStatus;
    }
}
