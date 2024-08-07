package com.mywebapp.dao;

import com.mywebapp.dto.BookingInfoDto;
import com.mywebapp.dto.GuestRoomBookingDto;
import com.mywebapp.dto.RoomDetailDto;
import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.model.RoomOption;
import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface RoomDao {
	long insert(Room room);
	List<RoomListItemDto> findAllRoomListItems(int offset, int pageSize, int approve);
	int getTotalRoomCount(int approve);
	RoomDetailDto getRoomById(long roomId);
	List<Room> getRoomsByHostId(long hostId);
	ArrayList<Room> searchRoomList(String searchWord,int viewRecord);
	int searchTotalRecord(String searchWord);
	List<GuestRoomBookingDto> getRoomsByGuestIdWithStatus(long guestId);

	void updateRoomApproveStatus(long roomId, int approve);
	void updateRoomDetail(RoomDetailDto roomDetailDto);
}