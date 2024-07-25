package com.mywebapp.dao;

import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomPriceDao {

    public int insert(RoomPrice roomPrice) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = JdbcUtil.getCon();
            String sql = "INSERT INTO room_price (room_id, rent_price, long_term, long_term_discount, early_check_in, early_check_in_discount, maintenance_bill, maintenance_bill_detail, electricity, water, gas, internet, cleaning_fee, refund_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, roomPrice.getRoomId());
            pstmt.setInt(2, roomPrice.getRentPrice());
            pstmt.setInt(3, roomPrice.getLongTerm());
            pstmt.setInt(4, roomPrice.getLongTermDiscount());
            pstmt.setInt(5, roomPrice.getEarlyCheckIn());
            pstmt.setInt(6, roomPrice.getEarlyCheckInDiscount());
            pstmt.setInt(7, roomPrice.getMaintenanceBill());
            pstmt.setString(8, roomPrice.getMaintenanceBillDetail());
            pstmt.setBoolean(9, roomPrice.isElectricity());
            pstmt.setBoolean(10, roomPrice.isWater());
            pstmt.setBoolean(11, roomPrice.isGas());
            pstmt.setBoolean(12, roomPrice.isInternet());
            pstmt.setInt(13, roomPrice.getCleaningFee());
            pstmt.setInt(14, roomPrice.getRefundType());

            return pstmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        finally {
            JdbcUtil.close(connection, pstmt, null);
        }
    }

//    public

}
