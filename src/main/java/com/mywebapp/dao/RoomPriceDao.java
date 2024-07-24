package com.mywebapp.dao;

import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;

import java.sql.*;

public class RoomPriceDao {

    private Connection connection;

    public RoomPriceDao(Connection connection) {
        this.connection = connection;
    }

    public void create(RoomPrice roomPrice) throws SQLException {
        String sql = "INSERT INTO room_price (room_id, rent_price, long_term, long_term_discount, early_check_in, " +
                "early_check_in_discount, maintenance_bill, maintenance_bill_discount, electricity, water, gas, " +
                "internet, cleaning_fee, refund_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, roomPrice.getRoomId());
            stmt.setInt(2, roomPrice.getRentPrice());
            stmt.setInt(3, roomPrice.getLongTerm());
            stmt.setInt(4, roomPrice.getLongTermDiscount());
            stmt.setInt(5, roomPrice.getEarlyCheckIn());
            stmt.setInt(6, roomPrice.getEarlyCheckInDiscount());
            stmt.setInt(7, roomPrice.getMaintenanceBill());
            stmt.setInt(8, roomPrice.getMaintenanceBillDiscount());
            stmt.setBoolean(9, roomPrice.isElectricity());
            stmt.setBoolean(10, roomPrice.isWater());
            stmt.setBoolean(11, roomPrice.isGas());
            stmt.setBoolean(12, roomPrice.isInternet());
            stmt.setInt(13, roomPrice.getCleaningFee());
            stmt.setInt(14, roomPrice.getRefundType());
            stmt.executeUpdate();
        }
    }

    public RoomPrice read(long roomId) throws SQLException {
        String sql = "SELECT * FROM room_price WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, roomId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RoomPrice(
                            rs.getLong("room_id"),
                            rs.getInt("rent_price"),
                            rs.getInt("long_term"),
                            rs.getInt("long_term_discount"),
                            rs.getInt("early_check_in"),
                            rs.getInt("early_check_in_discount"),
                            rs.getInt("maintenance_bill"),
                            rs.getInt("maintenance_bill_discount"),
                            rs.getBoolean("electricity"),
                            rs.getBoolean("water"),
                            rs.getBoolean("gas"),
                            rs.getBoolean("internet"),
                            rs.getInt("cleaning_fee"),
                            rs.getInt("refund_type")
                    );
                }
                return null;
            }
        }
    }

    public void update(RoomPrice roomPrice) throws SQLException {
        String sql = "UPDATE room_price SET rent_price = ?, long_term = ?, long_term_discount = ?, early_check_in = ?, " +
                "early_check_in_discount = ?, maintenance_bill = ?, maintenance_bill_discount = ?, electricity = ?, " +
                "water = ?, gas = ?, internet = ?, cleaning_fee = ?, refund_type = ? WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roomPrice.getRentPrice());
            stmt.setInt(2, roomPrice.getLongTerm());
            stmt.setInt(3, roomPrice.getLongTermDiscount());
            stmt.setInt(4, roomPrice.getEarlyCheckIn());
            stmt.setInt(5, roomPrice.getEarlyCheckInDiscount());
            stmt.setInt(6, roomPrice.getMaintenanceBill());
            stmt.setInt(7, roomPrice.getMaintenanceBillDiscount());
            stmt.setBoolean(8, roomPrice.isElectricity());
            stmt.setBoolean(9, roomPrice.isWater());
            stmt.setBoolean(10, roomPrice.isGas());
            stmt.setBoolean(11, roomPrice.isInternet());
            stmt.setInt(12, roomPrice.getCleaningFee());
            stmt.setInt(13, roomPrice.getRefundType());
            stmt.setLong(14, roomPrice.getRoomId());
            stmt.executeUpdate();
        }
    }

    public void delete(long roomId) throws SQLException {
        String sql = "DELETE FROM room_price WHERE room_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, roomId);
            stmt.executeUpdate();
        }
    }
}
