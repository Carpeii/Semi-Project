package com.mywebapp.model;

public enum RoomAction {
    APPROVE(1),
    DECLINE(2);

    private final int statusCode;

    RoomAction(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static RoomAction fromString(String action) {
        for (RoomAction roomAction : RoomAction.values()) {
            if (roomAction.name().equalsIgnoreCase(action)) {
                return roomAction;
            }
        }
        return null; // 유효하지 않은 액션 값 처리
    }
}