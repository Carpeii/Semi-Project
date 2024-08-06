package com.mywebapp.service;

import java.util.List;
import java.util.Map;

import com.mywebapp.dto.RoomListItemDto;

public interface RoomService {
	List<RoomListItemDto> getRoomList(int pageNum, int pageSize, int approve);
	int getTotalRoomCount(int approve);
	Map<String, Object> calculatePagination(int pageNum, int pageSize, int totalCount);

}
