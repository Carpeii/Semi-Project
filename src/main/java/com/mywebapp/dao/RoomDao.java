package com.mywebapp.dao;

import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.model.RoomOption;
import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

    public long insert(Room room) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long roomId = -1;
        String sql = "INSERT INTO room (host_id, room_name, jibun_address, street_address, address_detail, floor, usable_area, room_count, " +
                "living_room_count, toilet_count, kitchen_count, duplex, elevator, park, park_detail, room_type, " +
                "minimum_contract, approve) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = JdbcUtil.getCon();
            pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, room.getHostId());
            pstmt.setString(2, room.getRoomName());
            pstmt.setString(3, room.getJibunAddress());
            pstmt.setString(4, room.getStreetAddress());
            pstmt.setString(5, room.getAddressDetail());
            pstmt.setInt(6, room.getFloor());
            pstmt.setInt(7, room.getUsableArea());
            pstmt.setInt(8, room.getRoomCount());
            pstmt.setInt(9, room.getLivingRoomCount());
            pstmt.setInt(10, room.getToiletCount());
            pstmt.setInt(11, room.getKitchenCount());
            pstmt.setBoolean(12, room.isDuplex());
            pstmt.setBoolean(13, room.isElevator());
            pstmt.setBoolean(14, room.isPark());
            pstmt.setString(15, room.getParkDetail());
            pstmt.setInt(16, room.getRoomType());
            pstmt.setInt(17, room.getMinimumContract());
            pstmt.setInt(18, room.getApprove());
            int affectedRow = pstmt.executeUpdate();
            if (affectedRow == 0) {
                throw new SQLException("Insert failed");
            }

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                roomId = rs.getLong(1);
                System.out.println(roomId);
            }else {
                throw new SQLException("NO ID");
            }
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }finally {
            JdbcUtil.close(con, pstmt, null);
        }
        return roomId;
    }

    public List<Room> getRoomsByHostId(long hostId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Room> rooms = new ArrayList<>();

        String sql = "SELECT * FROM room WHERE host_id = ? AND approve = 1";

        try {
            con = JdbcUtil.getCon();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, hostId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getLong("id"));
                room.setHostId(rs.getString("host_id"));
                room.setRoomName(rs.getString("room_name"));
                room.setJibunAddress(rs.getString("jibun_address"));
                room.setStreetAddress(rs.getString("street_address"));
                room.setAddressDetail(rs.getString("address_detail"));

                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return rooms;
    }
    //승인여부도 확인해야됨 applove -> 1정상
    public ArrayList<Room> searchRoomList(String searchWord,int viewRecord) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Room> rooms = new ArrayList<Room>();
        try {
            con = JdbcUtil.getCon();
            String sql = "select distinct * from room r "
                    +"LEFT JOIN room_price rp ON r.id = rp.room_id "
                    +"LEFT JOIN room_option ro ON r.id = ro.room_id "
                    +"where (r.approve=1) "
                    +"and (r.jibun_address like ? "
                    +"or r.street_address like ? "
                    +"or r.room_name like ?) "
                    +"order by r.id desc "
                    +"limit 15 offset ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"%"+searchWord+"%");
            pstmt.setString(2,"%"+searchWord+"%");
            pstmt.setString(3,"%"+searchWord+"%");
            pstmt.setInt(4,viewRecord);
            rs = pstmt.executeQuery();
            while(rs.next()) {

                Room room = new Room(rs.getLong("id"),rs.getString("host_id"),
                        rs.getString("room_name"),rs.getString("jibun_address"),
                        rs.getString("street_address"),rs.getString("address_detail"),
                        rs.getInt("floor"),rs.getInt("usable_area"),
                        rs.getInt("room_count"),rs.getInt("living_room_count"),
                        rs.getInt("toilet_count"),rs.getInt("kitchen_count"),
                        rs.getBoolean("duplex"),rs.getBoolean("elevator"),
                        rs.getBoolean("park"),rs.getString("park_detail"),
                        rs.getInt("room_type"),rs.getInt("minimum_contract"),
                        rs.getInt("approve"),

                        getRoomListSampleImage(rs.getLong("id")),

                        new RoomOption(
                                rs.getLong("room_id"),rs.getString("room_option")
                        ),

                        new RoomPrice(
                                rs.getLong("room_id"),rs.getInt("rent_price"),
                                rs.getInt("long_term"),rs.getInt("long_term_discount"),
                                rs.getInt("early_check_in"),rs.getInt("early_check_in_discount"),
                                rs.getInt("maintenance_bill"),rs.getString("maintenance_bill_detail"),
                                rs.getBoolean("electricity"),rs.getBoolean("water"),
                                rs.getBoolean("gas"),rs.getBoolean("internet"),
                                rs.getInt("cleaning_fee"),rs.getInt("refund_type")
                        )
                );

                rooms.add(room);
            }
        }catch(SQLException s) {
            s.printStackTrace();
        }finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return rooms;
    }

    //페이징처리 메소드
    public int searchTotalRecord(String searchWord) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalRecord = 0;
        try {
            con = JdbcUtil.getCon();
            String sql = "SELECT COUNT(*) totalrecord "
                    +"FROM room r "
                    +"LEFT JOIN room_price rp ON r.id = rp.room_id "
                    +"WHERE r.approve = 1 "
                    +"AND (r.jibun_address LIKE ? "
                    +"OR r.street_address LIKE ? "
                    +"OR r.room_name LIKE ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"%"+searchWord+"%");
            pstmt.setString(2,"%"+searchWord+"%");
            pstmt.setString(3,"%"+searchWord+"%");
            rs = pstmt.executeQuery();
            if(rs.next()) {
                totalRecord = rs.getInt("totalrecord");
            }
        }catch(SQLException s) {
            s.printStackTrace();
        }finally {
            JdbcUtil.close(con, pstmt, rs);
        }
        return totalRecord;
    }
    //getRoomList 메소드 RoomImage얻어오는 메소드
    public ArrayList<RoomImage> getRoomListSampleImage(Long id) {
        Connection con = null;
        ArrayList<RoomImage> roomImageList = new ArrayList<RoomImage>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtil.getCon();
            String sql = "select * from room_image where room_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                RoomImage roomImage = new RoomImage(
                        rs.getLong("id"),rs.getLong("room_id"),
                        rs.getString("image_name"),rs.getString("save_file_name"),
                        rs.getString("image_path"),rs.getInt("image_order")
                );
                roomImageList.add(roomImage);
            }
        }catch(SQLException s) {
            s.printStackTrace();
        }finally {
            JdbcUtil.close(con, pstmt, rs);
        }

        return roomImageList;
    }
}
