package com.mywebapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dto.RoomListItemDto;

public class RoomServiceImpl implements RoomService {
	
	private RoomDao roomDao = new RoomDaoImpl();

	/* 한 페이지에 보여줄 방 목록 */
	@Override
	public List<RoomListItemDto> getRoomList(int pageNum, int pageSize) {
		int offset = (pageNum - 1) * pageSize;
		return roomDao.findAllRoomListItems(offset, pageSize);
	}

	/* 전체 방의 수 */
	@Override
	public int getTotalRoomCount() {
		return roomDao.getTotalRoomCount();
	}

	@Override
	public Map<String, Object> calculatePagination(int pageNum, int pageSize, int totalCount) {
		Map<String, Object> paginationInfo = new HashMap<>();
		int pageCount = (int) Math.ceil((double) totalCount / pageSize);
		int startPage = ((pageNum - 1) / 10) * 10 + 1;
		int endPage = Math.min(startPage + 9, pageCount);
		
		paginationInfo.put("pageCount", pageCount);
		paginationInfo.put("startPage", startPage);
		paginationInfo.put("endPage", endPage);
		paginationInfo.put("pageNum", pageNum);
		
		return paginationInfo;
	}
	
}
