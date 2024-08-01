package test;

import java.util.HashMap;
import java.util.Map;

public class pagination {
	public Map<String, Integer> calculatePagination(int pageNum, int pageSize, int totalCount) {
		Map<String, Integer> paginationInfo = new HashMap<>();
		int pageCount = (int) Math.ceil((double) totalCount / pageSize);
		int startPage = ((pageNum - 1) / 10) * 10 + 1;
		int endPage  = Math.min(startPage + 9, pageCount);
		
		paginationInfo.put("pageCount", pageCount);
		paginationInfo.put("startPage", startPage);
		paginationInfo.put("endPage", endPage);
		paginationInfo.put("pageNum", pageNum);
		
		return paginationInfo;
	}
	
	public int offSet(int viewRecord) {
		return 0;
	}

}
