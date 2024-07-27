package com.mywebapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.service.RoomServiceImpl;

public class RoomServiceImplTest {

    @Mock
    private RoomDao roomDao;

    @InjectMocks
    private RoomServiceImpl roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRoomList() {
        // Arrange
        int pageNum = 1;
        int pageSize = 10;
        int offset = (pageNum - 1) * pageSize;
        List<RoomListItemDto> mockRoomList = Arrays.asList(
            new RoomListItemDto("path1", "name1", "room1", "address1", 1000, "option1"),
            new RoomListItemDto("path2", "name2", "room2", "address2", 2000, "option2")
        );

        when(roomDao.findAllRoomListItems(offset, pageSize)).thenReturn(mockRoomList);

        // Act
        List<RoomListItemDto> roomList = roomService.getRoomList(pageNum, pageSize);

        // Assert
        assertEquals(mockRoomList, roomList);
    }

    @Test
    void testGetTotalRoomCount() {
        // Arrange
        int expectedCount = 100;
        when(roomDao.getTotalRoomCount()).thenReturn(expectedCount);

        // Act
        int totalCount = roomService.getTotalRoomCount();

        // Assert
        assertEquals(expectedCount, totalCount);
    }

    @Test
    void testCalculatePagination() {
        // Arrange
        int pageNum = 5;
        int pageSize = 10;
        int totalCount = 95;

        Map<String, Object> expectedPaginationInfo = new HashMap<>();
        expectedPaginationInfo.put("pageCount", 10);
        expectedPaginationInfo.put("startPage", 1);
        expectedPaginationInfo.put("endPage", 10);
        expectedPaginationInfo.put("pageNum", pageNum);

        // Act
        Map<String, Object> paginationInfo = roomService.calculatePagination(pageNum, pageSize, totalCount);

        // Assert
        assertEquals(expectedPaginationInfo, paginationInfo);
    }
}
