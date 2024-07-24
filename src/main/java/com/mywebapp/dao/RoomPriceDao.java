package com.mywebapp.dao;

import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomPriceDao {

    public void create(RoomPrice roomPrice) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = JdbcUtil.getCon();
            String sql = "INSERT INTO room_price (room_id, rent_price, long_term, long_term_discount, early_check_in, early_check_in_discount, maintenance_bill, maintenance_bill_detail, electricity, water, gas, internet, cleaning_fee, refund_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, roomPrice.getRoomId());
            stmt.setInt(2, roomPrice.getRentPrice());
            stmt.setInt(3, roomPrice.getLongTerm());
            stmt.setInt(4, roomPrice.getLongTermDiscount());
            stmt.setInt(5, roomPrice.getEarlyCheckIn());
            stmt.setInt(6, roomPrice.getEarlyCheckInDiscount());
            stmt.setInt(7, roomPrice.getMaintenanceBill());
            stmt.setInt(8, roomPrice.getMaintenanceBillDetail());
            stmt.setBoolean(9, roomPrice.isElectricity());
            stmt.setBoolean(10, roomPrice.isWater());
            stmt.setBoolean(11, roomPrice.isGas());
            stmt.setBoolean(12, roomPrice.isInternet());
            stmt.setInt(13, roomPrice.getCleaningFee());
            stmt.setInt(14, roomPrice.getRefundType());
            stmt.executeUpdate();
        } finally {
            JdbcUtil.close(connection, stmt, null);
        }
    }
}
