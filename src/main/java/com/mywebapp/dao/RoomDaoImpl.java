package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.model.Room;
import com.mywebapp.util.JdbcUtil;

public class RoomDaoImpl implements RoomDao {
	@Override
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
	            // PreparedStatement.RETURN_GENERATED_KEYS란?
	            // 데이터베이스에서 AUTO_INCREMENT와 같은 자동 생성 키를 가진 열에 대해 사용
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
	    
	    /* 게스트 페이지에서 보여줄 방 리스트 + 페이징 처리*/
		@Override
	    public List<RoomListItemDto> findAllRoomListItems(int offset, int pageSize) {
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	
	    	// LIMIT은 페이지 크기(한 페이지에 보여줄 데이터의 수)
	    	// OFFSET은 데이터베이스에서 시작할 위치 (OFFSET은 0부터 시작)
	        String sql = "SELECT r.id, ri.image_path, ri.image_name, r.room_name, r.street_address, rp.rent_price, ro.room_option " +
	                "FROM room r " +
	                "INNER JOIN room_image ri ON r.id = ri.room_id " +
	                "INNER JOIN room_option ro ON r.id = ro.room_id " +
	                "INNER JOIN room_price rp ON r.id = rp.room_id " + 
	                "LIMIT ? OFFSET ?"; 
	                
	                
	        try {
	        	con = JdbcUtil.getCon();
	        	pstmt = con.prepareStatement(sql);
	        	pstmt.setInt(1,  pageSize);
	        	pstmt.setInt(2, offset);
	        	rs = pstmt.executeQuery();
	        	
	        	List<RoomListItemDto> roomList = new ArrayList<RoomListItemDto>();
	        	while (rs.next()) {
	        		Long id = rs.getLong("id");
					String imagePath = rs.getString("image_path");
					String imageName = rs.getString("image_name");
					String roomName = rs.getString("room_name");
					String streetAddress = rs.getString("street_address");
					int rentPrice = rs.getInt("rent_price");
					String roomOption = rs.getString("room_option");
					RoomListItemDto dto = new RoomListItemDto(id, imagePath, imageName, roomName, streetAddress, rentPrice, roomOption);
	        		roomList.add(dto);
	        	}
	        	return roomList;
	        } catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	        

	    }
	    
	    /* room 테이블에서 전체 행 수를 계산해 반환 */
		@Override
	    public int getTotalRoomCount() {
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	String sql = "SELECT COUNT(*) FROM room";
	    	try {
	    		con = JdbcUtil.getCon();
	    		pstmt = con.prepareStatement(sql);
	    		rs = pstmt.executeQuery();
	    		if (rs.next()) {
	    			return rs.getInt(1); // 결과의 첫 번째 열(전체 행 수)을 정수로 반환
	    		}
	    		return 0; // 결과가 없다면 0을 반환
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	            return 0; // 예외 발생 시 기본값으로 0을 반환
	        } finally {
	            JdbcUtil.close(con, pstmt, rs); 
	        }
	    }
}
