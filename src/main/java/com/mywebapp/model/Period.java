package com.mywebapp.model;

public enum Period {
    MAX_AVAILABLE_DAYS(91);
    
    private final int days;

    Period(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
