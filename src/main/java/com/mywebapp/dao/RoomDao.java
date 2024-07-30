package com.mywebapp.dao;

import java.util.List;

import com.mywebapp.dto.RoomDetailDto;
import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.model.Room;

public interface RoomDao {
	long insert(Room room);
	List<RoomListItemDto> findAllRoomListItems(int offset, int pageSize);
	int getTotalRoomCount();
	RoomDetailDto getRoomById(int roomId);
}
