package com.mywebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mywebapp.dto.GuestRoomBookingDto;
import com.mywebapp.dto.RoomDetailDto;
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

			pstmt.setLong(1, room.getHostId());
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

	/* approve에 따라 보여줄 방 리스트 + 페이징 처리*/
	@Override
	public List<RoomListItemDto> findAllRoomListItems(int offset, int pageSize, int approve) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RoomListItemDto> roomList = new ArrayList<RoomListItemDto>();

		// LIMIT은 페이지 크기(한 페이지에 보여줄 데이터의 수)
		// OFFSET은 데이터베이스에서 시작할 위치 (OFFSET은 0부터 시작)
		String sql = "SELECT r.id, " +
				"       ri.image_path, " +
				"       ri.image_name, " +
				"       ri.save_file_name, " +
				"       r.room_name, " +
				"       r.street_address, " +
				"       rp.rent_price, " +
				"       ro.room_options " +
				"FROM room r " +
				"INNER JOIN room_image ri ON r.id = ri.room_id " +
				"INNER JOIN room_option ro ON r.id = ro.room_id " +
				"INNER JOIN room_price rp ON r.id = rp.room_id " +
				"WHERE r.approve = ? " +
				"LIMIT ? OFFSET ?";

		try {
			con = JdbcUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, approve);
			pstmt.setInt(2, pageSize);
			pstmt.setInt(3, offset);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("id");
				String imagePath = rs.getString("image_path");
				String imageName = rs.getString("image_name");
				String saveFileName = rs.getString("save_file_name");
				String roomName = rs.getString("room_name");
				String streetAddress = rs.getString("street_address");
				int rentPrice = rs.getInt("rent_price");
				String roomOption = rs.getString("room_options");

				RoomListItemDto dto = new RoomListItemDto(id, imagePath, imageName, saveFileName, roomName, streetAddress, rentPrice, roomOption);
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

	/* room 테이블에서 전체 행 수를 계산해 반환 + approve 추가*/
	@Override
	public int getTotalRoomCount(int approve) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) " +
				"FROM room r " +
				"INNER JOIN room_image ri ON r.id = ri.room_id " +
				"INNER JOIN room_option ro ON r.id = ro.room_id " +
				"INNER JOIN room_price rp ON r.id = rp.room_id " +
				"WHERE r.approve = ?";
		try {
			con = JdbcUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, approve);
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
				"    r.id as room_id, " +
				"    m.id host_id, " +
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
				"	  r.approve," +
				"    ri.image_name, " +
				"    ri.image_path, " +
				"    ri.save_file_name, " +
				"    ri.image_order, " +
				"    rop.room_options, " +
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
				"    rp.refund_type " +
				"FROM " +
				"    room r " +
				"INNER JOIN " +
				"    member m ON r.host_id = m.id " +
				"INNER JOIN " +
				"    room_image ri ON r.id = ri.room_id " +
				"INNER JOIN " +
				"    room_option rop ON r.id = rop.room_id " +
				"INNER JOIN " +
				"    room_price rp ON r.id = rp.room_id " +
				"WHERE r.id = ?";


		try {
			con = JdbcUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, roomId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				room = new RoomDetailDto();
				room.setId(rs.getLong("room_id"));
				room.setHostId(rs.getLong("host_id"));
				room.setHostName(rs.getString("host_name"));
				room.setRoomName(rs.getString("room_name"));
				room.setJibunAddress(rs.getString("jibun_address"));
				room.setStreetAddress(rs.getString("street_address"));
				room.setAddressDetail(rs.getString("address_detail"));
				room.setFloor(rs.getInt("floor"));
				room.setUsableArea(rs.getInt("usable_area"));
				room.setRoomCount(rs.getInt("room_count"));
				room.setLivingRoomCount(rs.getInt("living_room_count"));
				room.setToiletCount(rs.getInt("toilet_count"));
				room.setKitchenCount(rs.getInt("kitchen_count"));
				room.setDuplex(rs.getBoolean("duplex"));
				room.setElevator(rs.getBoolean("elevator"));
				room.setPark(rs.getBoolean("park"));
				room.setParkDetail(rs.getString("park_detail"));
				room.setRoomType(rs.getInt("room_type"));
				room.setMinimumContract(rs.getInt("minimum_contract"));
				room.setApprove(rs.getInt("approve"));
				room.setImageName(rs.getString("image_name"));
				room.setImagePath(rs.getString("image_path"));
				room.setSaveFileName(rs.getString("save_file_name"));
				room.setImageOrder(rs.getInt("image_order"));
				room.setRoomOptions(rs.getString("room_options"));
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
				room.setHostId(rs.getLong("host_id"));
				room.setRoomName(rs.getString("room_name"));
				room.setJibunAddress(rs.getString("jibun_address"));
				room.setStreetAddress(rs.getString("street_address"));
				room.setAddressDetail(rs.getString("address_detail"));

				rooms.add(room);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return rooms;
	}

	//승인여부도 확인해야됨 approve -> 1정상
	@Override
	public ArrayList<Room> searchRoomList(String searchWord, int viewRecord) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Room> rooms = new ArrayList<Room>();
		try {
			con = JdbcUtil.getCon();
			String sql = "select distinct * from room r "
					+ "LEFT JOIN room_price rp ON r.id = rp.room_id "
					+ "LEFT JOIN room_option ro ON r.id = ro.room_id "
					+ "where (r.approve=1) "
					+ "and (r.jibun_address like ? "
					+ "or r.street_address like ? "
					+ "or r.room_name like ?) "
					+ "order by r.id desc "
					+ "limit 15 offset ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchWord + "%");
			pstmt.setString(2, "%" + searchWord + "%");
			pstmt.setString(3, "%" + searchWord + "%");
			pstmt.setInt(4, viewRecord);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Room room = new Room(rs.getLong("id"), rs.getLong("host_id"),
						rs.getString("room_name"), rs.getString("jibun_address"),
						rs.getString("street_address"), rs.getString("address_detail"),
						rs.getInt("floor"), rs.getInt("usable_area"),
						rs.getInt("room_count"), rs.getInt("living_room_count"),
						rs.getInt("toilet_count"), rs.getInt("kitchen_count"),
						rs.getBoolean("duplex"), rs.getBoolean("elevator"),
						rs.getBoolean("park"), rs.getString("park_detail"),
						rs.getInt("room_type"), rs.getInt("minimum_contract"),
						rs.getInt("approve"),

						getRoomListSampleImage(rs.getLong("id")),

						new RoomOption(
								rs.getLong("room_id"), rs.getString("room_options")
						),

						new RoomPrice(
								rs.getLong("room_id"), rs.getInt("rent_price"),
								rs.getInt("long_term"), rs.getInt("long_term_discount"),
								rs.getInt("early_check_in"), rs.getInt("early_check_in_discount"),
								rs.getInt("maintenance_bill"), rs.getString("maintenance_bill_detail"),
								rs.getBoolean("electricity"), rs.getBoolean("water"),
								rs.getBoolean("gas"), rs.getBoolean("internet"),
								rs.getInt("cleaning_fee"), rs.getInt("refund_type")
						)
				);

				rooms.add(room);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
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
					+ "FROM room r "
					+ "LEFT JOIN room_price rp ON r.id = rp.room_id "
					+ "WHERE r.approve = 1 "
					+ "AND (r.jibun_address LIKE ? "
					+ "OR r.street_address LIKE ? "
					+ "OR r.room_name LIKE ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchWord + "%");
			pstmt.setString(2, "%" + searchWord + "%");
			pstmt.setString(3, "%" + searchWord + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt("totalrecord");
			}
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
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
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				RoomImage roomImage = new RoomImage(
						rs.getLong("id"), rs.getLong("room_id"),
						rs.getString("image_name"), rs.getString("save_file_name"),
						rs.getString("image_path"), rs.getInt("image_order")

				);
				roomImageList.add(roomImage);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}

		return roomImageList;
	}

	@Override
	public List<GuestRoomBookingDto> getRoomsByGuestIdWithStatus(long guestId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestRoomBookingDto> rooms = new ArrayList<>();

		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT "
		            + "    b.id AS bookingId, "
		            + "    r.id AS roomId, "
		            + "    r.room_name AS roomName, "
		            + "    r.jibun_address AS jibunAddress, "
		            + "    r.street_address AS streetAddress, "
		            + "    r.address_detail AS addressDetail, "
		            + "    r.floor AS floor, "
		            + "    b.check_in_date AS checkInDate, "
		            + "    b.check_out_date AS checkOutDate, "
		            + "    rp.rent_price AS rentPrice, "
		            + "    b.booking_status AS bookingStatus "
		            + "FROM "
		            + "    booking b "
		            + "    JOIN room r ON b.room_id = r.id "
		            + "    JOIN room_price rp ON r.id = rp.room_id "
		            + "WHERE "
		            + "    b.guest_id = ? "
		            + "ORDER BY "
		            + "    b.id DESC";

			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, guestId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GuestRoomBookingDto room = new GuestRoomBookingDto();
				room.setRoomId(rs.getLong("roomId"));
				room.setRoomName(rs.getString("roomName"));
				room.setJibunAddress(rs.getString("jibunAddress"));
				room.setStreetAddress(rs.getString("streetAddress"));
				room.setAddressDetail(rs.getString("addressDetail"));
				room.setFloor(rs.getInt("floor"));
				room.setCheckInDate(rs.getDate("checkInDate"));
				room.setCheckOutDate(rs.getDate("checkOutDate"));
				room.setRentPrice(rs.getInt("rentPrice"));
				room.setBookingStatus(rs.getInt("bookingStatus"));

				rooms.add(room);
			}
			return rooms;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}

	@Override
	public void updateRoomApproveStatus(long roomId, int approve) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getCon();
			String sql = "UPDATE room SET approve = ? WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, approve);
			pstmt.setLong(2, roomId);
			int updatedRows = pstmt.executeUpdate();

			if (updatedRows > 0) {
				System.out.println("Room with id " + roomId + " has been updated to approve status " + approve);
			} else {
				System.out.println("No room found with id " + roomId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}

	@Override
	public void updateRoomDetail(RoomDetailDto roomDetailDto) {
		Connection con = null;
		PreparedStatement pstmtRoom = null;
		PreparedStatement pstmtImage = null;
		PreparedStatement pstmtOption = null;
		PreparedStatement pstmtPrice = null;

		try {
			con = JdbcUtil.getCon();
			con.setAutoCommit(false);

			// room 테이블
			String sqlRoom = "UPDATE room SET " +
					"room_name = ?, " +
					"jibun_address = ?, " +
					"street_address = ?, " +
					"address_detail = ?, " +
					"floor = ?, " +
					"usable_area = ?, " +
					"room_count = ?, " +
					"living_room_count = ?, " +
					"toilet_count = ?, " +
					"kitchen_count = ?, " +
					"duplex = ?, " +
					"elevator = ?, " +
					"park = ?, " +
					"park_detail = ?, " +
					"room_type = ?, " +
					"minimum_contract = ? " +
					"WHERE id = ?";

			pstmtRoom = con.prepareStatement(sqlRoom);
			pstmtRoom.setString(1, roomDetailDto.getRoomName());
			pstmtRoom.setString(2, roomDetailDto.getJibunAddress());
			pstmtRoom.setString(3, roomDetailDto.getStreetAddress());
			pstmtRoom.setString(4, roomDetailDto.getAddressDetail());
			pstmtRoom.setInt(5, roomDetailDto.getFloor());
			pstmtRoom.setInt(6, roomDetailDto.getUsableArea());
			pstmtRoom.setInt(7, roomDetailDto.getRoomCount());
			pstmtRoom.setInt(8, roomDetailDto.getLivingRoomCount());
			pstmtRoom.setInt(9, roomDetailDto.getToiletCount());
			pstmtRoom.setInt(10, roomDetailDto.getKitchenCount());
			pstmtRoom.setBoolean(11, roomDetailDto.isDuplex());
			pstmtRoom.setBoolean(12, roomDetailDto.isElevator());
			pstmtRoom.setBoolean(13, roomDetailDto.isPark());
			pstmtRoom.setString(14, roomDetailDto.getParkDetail());
			pstmtRoom.setInt(15, roomDetailDto.getRoomType());
			pstmtRoom.setInt(16, roomDetailDto.getMinimumContract());
			pstmtRoom.setLong(17, roomDetailDto.getId());

			int roomUpdate = pstmtRoom.executeUpdate();
			System.out.println("룸 완료" + roomUpdate);

			// 2. room_image 테이블 업데이트
			String sqlImage = "UPDATE room_image SET " +
					"image_name = ?, " +
					"image_path = ?, " +
					"save_file_name = ? " +
					"WHERE room_id = ?";

			pstmtImage = con.prepareStatement(sqlImage);
			pstmtImage.setString(1, roomDetailDto.getImageName());
			pstmtImage.setString(2, roomDetailDto.getImagePath());
			pstmtImage.setString(3, roomDetailDto.getSaveFileName());
			pstmtImage.setLong(4, roomDetailDto.getId());

			int imageUpdate = pstmtImage.executeUpdate();

			// 3. room_option 테이블 업데이트
			String sqlOption = "UPDATE room_option SET " +
					"room_options = ? " +
					"WHERE room_id = ?";
			pstmtOption = con.prepareStatement(sqlOption);
			pstmtOption.setString(1, roomDetailDto.getRoomOptions());
			pstmtOption.setLong(2, roomDetailDto.getId());

			int optionUpdate = pstmtOption.executeUpdate();


			// 4. room_price 테이블 업데이트
			String sqlPrice = "UPDATE room_price SET " +
					"rent_price = ?, " +
					"long_term = ?, " +
					"long_term_discount = ?, " +
					"early_check_in = ?, " +
					"early_check_in_discount = ?, " +
					"maintenance_bill = ?, " +
					"maintenance_bill_detail = ?, " +
					"electricity = ?, " +
					"water = ?, " +
					"gas = ?, " +
					"internet = ?, " +
					"cleaning_fee = ?, " +
					"refund_type = ? " +
					"WHERE room_id = ?";
			pstmtPrice = con.prepareStatement(sqlPrice);
			pstmtPrice.setInt(1, roomDetailDto.getRentPrice());
			pstmtPrice.setInt(2, roomDetailDto.getLongTerm());
			pstmtPrice.setInt(3, roomDetailDto.getLongTermDiscount());
			pstmtPrice.setInt(4, roomDetailDto.getEarlyCheckIn());
			pstmtPrice.setInt(5, roomDetailDto.getEarlyCheckInDiscount());
			pstmtPrice.setInt(6, roomDetailDto.getMaintenanceBill());
			pstmtPrice.setString(7, roomDetailDto.getMaintenanceBillDetail());
			pstmtPrice.setBoolean(8, roomDetailDto.isElectricity());
			pstmtPrice.setBoolean(9, roomDetailDto.isWater());
			pstmtPrice.setBoolean(10, roomDetailDto.isGas());
			pstmtPrice.setBoolean(11, roomDetailDto.isInternet());
			pstmtPrice.setInt(12, roomDetailDto.getCleaningFee());
			pstmtPrice.setInt(13, roomDetailDto.getRefundType());
			pstmtPrice.setLong(14, roomDetailDto.getId());

			int priceUpdate = pstmtPrice.executeUpdate();

			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (con != null) {
				try {
					con.rollback(); // 트랜잭션 롤백
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
		}finally {
			JdbcUtil.close(con, pstmtRoom, null);
			JdbcUtil.close(null, pstmtImage, null);
			JdbcUtil.close(null, pstmtOption, null);
			JdbcUtil.close(null, pstmtPrice, null);
		}
	}
}