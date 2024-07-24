package com.mywebapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class ReviewDto {
    private long id;
    private long bookingId;
    private String message;
    private int rating;
    private Timestamp createdAt;

    public ReviewDto() {
    }

    public ReviewDto(long bookingId, String message, int rating, Timestamp createdAt) {
        this.bookingId = bookingId;
        this.message = message;
        this.rating = rating;
        this.createdAt = createdAt;
    }
}
