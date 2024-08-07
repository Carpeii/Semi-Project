package com.mywebapp.util;

import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {

    public static int calculateOffset(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }

    public static Map<String, Object> calculatePagination(int pageNum, int pageSize, int totalCount) {
        Map<String, Object> paginationInfo = new HashMap<>();

        // Calculate the total number of pages
        int pageCount = (int) Math.ceil((double) totalCount / pageSize);

        // Calculate the start page for pagination controls
        int startPage = ((pageNum - 1) / 10) * 10 + 1;

        // Calculate the end page, ensuring it does not exceed the total number of pages
        int endPage = Math.min(startPage + 9, pageCount);

        // Put pagination details into the map
        paginationInfo.put("pageCount", pageCount);
        paginationInfo.put("startPage", startPage);
        paginationInfo.put("endPage", endPage);
        paginationInfo.put("pageNum", pageNum);

        return paginationInfo;
    }
}
