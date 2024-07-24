package com.mywebapp.dao;

import com.mywebapp.model.Room;
import com.mywebapp.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

    private Connection connection;

    public RoomDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Room room) throws SQLException {
        String sql = "INSERT INTO room (host_id, jibun_address, street_address, floor, usable_area, room_count, " +
                "living_room_count, toilet_count, kitchen_count, duplex, elevator, park, park_detail, " +
                "room_type, minimum_contract, approve) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, room.getHostId());
            stmt.setString(2, room.getJibunAddress());
            stmt.setString(3, room.getStreetAddress());
            stmt.setInt(4, room.getFloor());
            stmt.setInt(5, room.getUsableArea());
            stmt.setInt(6, room.getRoomCount());
            stmt.setInt(7, room.getLivingRoomCount());
            stmt.setInt(8, room.getToiletCount());
            stmt.setInt(9, room.getKitchenCount());
            stmt.setBoolean(10, room.isDuplex());
            stmt.setBoolean(11, room.isElevator());
            stmt.setBoolean(12, room.isPark());
            stmt.setString(13, room.getParkDetail());
            stmt.setInt(14, room.getRoomType());
            stmt.setInt(15, room.getMinimumContract());
            stmt.setInt(16, room.getApprove());
            stmt.executeUpdate();
        }
    }

    public Room read(long id) throws SQLException {
        String sql = "SELECT * FROM room WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                            rs.getLong("id"),
                            rs.getString("host_id"),
                            rs.getString("jibun_address"),
                            rs.getString("street_address"),
                            rs.getInt("floor"),
                            rs.getInt("usable_area"),
                            rs.getInt("room_count"),
                            rs.getInt("living_room_count"),
                            rs.getInt("toilet_count"),
                            rs.getInt("kitchen_count"),
                            rs.getBoolean("duplex"),
                            rs.getBoolean("elevator"),
                            rs.getBoolean("park"),
                            rs.getString("park_detail"),
                            rs.getInt("room_type"),
                            rs.getInt("minimum_contract"),
                            rs.getInt("approve"),
                            // Assuming we need to populate related lists, you need additional queries to fetch these
                            new ArrayList<>(), // Placeholder for roomImageList
                            null, // Placeholder for roomOption
                            null // Placeholder for roomPrice
                    );
                }
                return null;
            }
        }
    }

    public List<Room> findAll() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                rooms.add(new Room(
                        rs.getLong("id"),
                        rs.getString("host_id"),
                        rs.getString("jibun_address"),
                        rs.getString("street_address"),
                        rs.getInt("floor"),
                        rs.getInt("usable_area"),
                        rs.getInt("room_count"),
                        rs.getInt("living_room_count"),
                        rs.getInt("toilet_count"),
                        rs.getInt("kitchen_count"),
                        rs.getBoolean("duplex"),
                        rs.getBoolean("elevator"),
                        rs.getBoolean("park"),
                        rs.getString("park_detail"),
                        rs.getInt("room_type"),
                        rs.getInt("minimum_contract"),
                        rs.getInt("approve"),
                        new ArrayList<>(), // Placeholder for roomImageList
                        null, // Placeholder for roomOption
                        null // Placeholder for roomPrice
                ));
            }
        }
        return rooms;
    }

    public void update(Room room) throws SQLException {
        String sql = "UPDATE room SET host_id = ?, jibun_address = ?, street_address = ?, floor = ?, usable_area = ?, " +
                "room_count = ?, living_room_count = ?, toilet_count = ?, kitchen_count = ?, duplex = ?, elevator = ?, " +
                "park = ?, park_detail = ?, room_type = ?, minimum_contract = ?, approve = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getHostId());
            stmt.setString(2, room.getJibunAddress());
            stmt.setString(3, room.getStreetAddress());
            stmt.setInt(4, room.getFloor());
            stmt.setInt(5, room.getUsableArea());
            stmt.setInt(6, room.getRoomCount());
            stmt.setInt(7, room.getLivingRoomCount());
            stmt.setInt(8, room.getToiletCount());
            stmt.setInt(9, room.getKitchenCount());
            stmt.setBoolean(10, room.isDuplex());
            stmt.setBoolean(11, room.isElevator());
            stmt.setBoolean(12, room.isPark());
            stmt.setString(13, room.getParkDetail());
            stmt.setInt(14, room.getRoomType());
            stmt.setInt(15, room.getMinimumContract());
            stmt.setInt(16, room.getApprove());
            stmt.setLong(17, room.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM room WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
