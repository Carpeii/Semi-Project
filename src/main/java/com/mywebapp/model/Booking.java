package com.mywebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Booking {
    private long id;
    private long guestId;
    private long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private int bookingStatus;

    private Member member;
    private Room room;
    
	public Booking(Date checkInDate, Date checkOutDate) {
		super();
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

    
}
