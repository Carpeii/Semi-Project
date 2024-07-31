package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mywebapp.dto.BookingInfoDto;
import com.mywebapp.dto.RoomDetailDto;
import com.mywebapp.dto.RoomDto;
import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.model.RoomOption;
import com.mywebapp.model.RoomPrice;
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
			} else {
				throw new SQLException("NO ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
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
			pstmt.setInt(1, pageSize);
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
				String saveFileName = rs.getString("save_file_name");
				RoomListItemDto dto = new RoomListItemDto(id, imagePath, imageName,saveFileName, roomName, streetAddress, rentPrice, roomOption);
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

	@Override
	public RoomDetailDto getRoomById(long roomId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		RoomDetailDto room = null;
		String sql = "SELECT " +
				"	 m.id, " +
				"    m.name AS host_name, " +
				"    r.room_name, " +
				"    r.jibun_address, " +
				"    r.street_address, " +
				"    r.address_detail, " +
				"    r.floor, " +
				"    r.usable_area, " +
				"    r.room_count, " +
				"    r.living_room_count, " +
				"    r.toilet_count, " +
				"    r.kitchen_count, " +
				"    r.duplex, " +
				"    r.elevator, " +
				"    r.park, " +
				"    r.park_detail, " +
				"    r.room_type, " +
				"    r.minimum_contract, " +
				"    ri.image_name, " +
				"    ri.image_path, " +
				"    ri.save_file_name, " +
				"    ri.image_order, " +
				"    rop.room_option, " +
				"    rp.rent_price, " +
				"    rp.long_term, " +
				"    rp.long_term_discount, " +
				"    rp.early_check_in, " +
				"    rp.early_check_in_discount, " +
				"    rp.maintenance_bill, " +
				"    rp.maintenance_bill_detail, " +
				"    rp.electricity, " +
				"    rp.water, " +
				"    rp.gas, " +
				"    rp.internet, " +
				"    rp.cleaning_fee, " +
				"    rp.refund_type, " +
				"    rv.message, " +
				"    rv.rating, " +
				"    rv.created_at " +
				"FROM member m " +
				"INNER JOIN booking b ON m.id = b.id " +
				"INNER JOIN room r ON b.id = r.id " +
				"INNER JOIN room_image ri ON r.id = ri.room_id " +
				"INNER JOIN room_option rop ON r.id = rop.room_id " +
				"INNER JOIN room_price rp ON r.id = rp.room_id " +
				"INNER JOIN review rv ON r.id = rv.id " +
				"WHERE r.id = ?";

		try {
			con = JdbcUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, roomId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				room = new RoomDetailDto();
				room.setId(rs.getLong("id"));
				room.setHostName(rs.getString("host_name"));
				room.setRoomName(rs.getString("room_name"));
				room.setJibunAddress(rs.getString("jibun_address"));
				room.setStreetAddress(rs.getString("street_address"));
				room.setAddressDetail(rs.getString("address_detail"));
				room.setFloor(rs.getInt("floor"));
				room.setUsableArea(rs.getDouble("usable_area"));
				room.setRoomCount(rs.getInt("room_count"));
				room.setLivingRoomCount(rs.getInt("living_room_count"));
				room.setToiletCount(rs.getInt("toilet_count"));
				room.setKitchenCount(rs.getInt("kitchen_count"));
				room.setDuplex(rs.getBoolean("duplex"));
				room.setElevator(rs.getBoolean("elevator"));
				room.setPark(rs.getBoolean("park"));
				room.setParkDetail(rs.getString("park_detail"));
				room.setRoomType(rs.getString("room_type"));
				room.setMinimumContract(rs.getInt("minimum_contract"));
				room.setImageName(rs.getString("image_name"));
				room.setImagePath(rs.getString("image_path"));
				room.setSaveFileName(rs.getString("save_file_name"));
				room.setImageOrder(rs.getInt("image_order"));
				room.setRoomOption(rs.getString("room_option"));
				room.setRentPrice(rs.getInt("rent_price"));
				room.setLongTerm(rs.getInt("long_term"));
				room.setLongTermDiscount(rs.getInt("long_term_discount"));
				room.setEarlyCheckIn(rs.getInt("early_check_in"));
				room.setEarlyCheckInDiscount(rs.getInt("early_check_in_discount"));
				room.setMaintenanceBill(rs.getInt("maintenance_bill"));
				room.setMaintenanceBillDetail(rs.getString("maintenance_bill_detail"));
				room.setElectricity(rs.getBoolean("electricity"));
				room.setWater(rs.getBoolean("water"));
				room.setGas(rs.getBoolean("gas"));
				room.setInternet(rs.getBoolean("internet"));
				room.setCleaningFee(rs.getInt("cleaning_fee"));
				room.setRefundType(rs.getInt("refund_type"));
				room.setReviewMessage(rs.getString("message"));
				room.setRating(rs.getInt("rating"));
				room.setReviewCreatedAt(rs.getDate("created_at"));
			}
			return room;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	@Override
	public BookingInfoDto getBookingInfoById(long roomId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		BookingInfoDto bookingInfo = null;
		String sql = "SELECT r.room_name, r.jibun_address, r.street_address, r.address_detail, r.floor, " +
				"m.name AS member_name, m.phone, b.check_in_date, b.check_out_date, " +
				"rp.rent_price, rp.long_term_discount, rp.early_check_in_discount, rp.maintenance_bill, rp.cleaning_fee " +
				"FROM room r " +
				"INNER JOIN member m ON r.host_id = m.id " +
				"INNER JOIN booking b ON r.id = b.room_id " +
				"INNER JOIN room_price rp ON r.id = rp.room_id " +
				"WHERE r.id = ?";

		try {
			con = JdbcUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, roomId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bookingInfo = new BookingInfoDto();
				bookingInfo.setRoomName(rs.getString("room_name"));
				bookingInfo.setJibunAddress(rs.getString("jibun_address"));
				bookingInfo.setStreetAddress(rs.getString("street_address"));
				bookingInfo.setAddressDetail(rs.getString("address_detail"));
				bookingInfo.setFloor(rs.getInt("floor"));
				bookingInfo.setMemberName(rs.getString("member_name"));
				bookingInfo.setPhone(rs.getString( "phone"));
				bookingInfo.setCheckInDate(rs.getDate("check_in_date"));
				bookingInfo.setCheckOutDate(rs.getDate("check_out_date"));
				bookingInfo.setRentPrice(rs.getInt("rent_price"));
				bookingInfo.setLongTermDiscount(rs.getInt("long_term_discount"));
				bookingInfo.setEarlyCheckInDiscount(rs.getInt("early_check_in_discount"));
				bookingInfo.setMaintenanceBill(rs.getInt("maintenance_bill"));
				bookingInfo.setCleaningFee(rs.getInt("cleaning_fee"));
			}
			return bookingInfo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}

	@Override
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
	@Override
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
	@Override
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