package com.mywebapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.util.PaginationUtil;

public class RoomServiceImpl implements RoomService {
	
	// RoomDaoImpl 불러오기
	private RoomDao roomDao = new RoomDaoImpl();

	/* 한 페이지에 보여줄 방 목록 */
	@Override
	public List<RoomListItemDto> getRoomList(int pageNum, int pageSize, int approve) {
        // Use PaginationUtil to calculate the offset
		int offset = PaginationUtil.calculateOffset(pageNum, pageSize);
		return roomDao.findAllRoomListItems(offset, pageSize, approve);
	}

	/* 전체 방의 수 */
	@Override
	public int getTotalRoomCount(int approve) {
		return roomDao.getTotalRoomCount(approve);
	}
	
	@Override
	public Map<String, Object> calculatePagination(int pageNum, int pageSize, int totalCount) {
		return PaginationUtil.calculatePagination(pageNum, pageSize, totalCount);
	}


	
	




	
}
