package com.mywebapp.model;

public enum BookingStatus {
    AVAILABLE(1),
    UNAVAILABLE(0);
    
    private final int status;

    BookingStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static BookingStatus fromStatus(int status) {
        for (BookingStatus bs : values()) {
            if (bs.getStatus() == status) {
                return bs;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}
