package com.mywebapp.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class Review {
    private long id;
    private long bookingId;
    private String message;
    private int rating;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    public Review() {
    }

    public Review(long bookingId, String message, int rating, Timestamp createdAt) {
        this.bookingId = bookingId;
        this.message = message;
        this.rating = rating;
        this.createdAt = createdAt;
    }
}
