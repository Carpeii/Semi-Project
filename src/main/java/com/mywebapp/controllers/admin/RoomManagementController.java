package com.mywebapp.controllers.admin;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dto.RoomListItemDto;
import com.mywebapp.dto.UserDto;
import com.mywebapp.service.RoomService;
import com.mywebapp.service.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/roomManagement")
public class RoomManagementController extends HttpServlet {
    private RoomService roomService = new RoomServiceImpl();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("user");

        if (dto.getMemberType() == 3) {
            RoomDao roomDao = new RoomDaoImpl();
            int approve = 0; // 승인전

            // 페이지 번호 처리
            String pNum = req.getParameter("pageNum");
            int pageNum = 1;
            if (pNum != null) {
                pageNum = Integer.parseInt(pNum);
            }
            int pageSize = 10; // 한 페이지에 보여줄 항목 수

            // RoomServiceImpl의 메서드들 호출
            List<RoomListItemDto> roomList = roomService.getRoomList(pageNum, pageSize, approve);
            int totalCount = roomService.getTotalRoomCount(approve);
            Map<String, Object> paginationInfo = roomService.calculatePagination(pageNum, pageSize, totalCount);

            // request 객체를 통해 guestMain.jsp로 데이터들 전달
            req.setAttribute("roomList", roomList);
            req.setAttribute("pageCount", paginationInfo.get("pageCount"));
            req.setAttribute("startPage", paginationInfo.get("startPage"));
            req.setAttribute("endPage", paginationInfo.get("endPage"));
            req.setAttribute("pageNum", paginationInfo.get("pageNum"));

            req.getRequestDispatcher("/jsp/admin/roomManagement.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/jsp/admin/loginMain.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        UserDto dto = (UserDto) session.getAttribute("user");

        if (dto == null || dto.getMemberType() != 3) {
            // 권한이 없는 사용자는 로그인 페이지로 리다이렉트
            resp.sendRedirect("/jsp/auth/loginMain.jsp");
            return;
        }

        String action = req.getParameter("action");
        long roomId = Long.parseLong(req.getParameter("roomId"));

        RoomDao roomDao = new RoomDaoImpl();

        switch (action) {
            case "approve":
                roomDao.updateRoomApproveStatus(roomId, 1);
                break;
            case "decline":
                roomDao.updateRoomApproveStatus(roomId, 2);
                break;
            default:
                // 유효하지 않은 액션 처리
                resp.sendRedirect("/admin/roomManagement");
                return;
        }
        // 작업 완료 후 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/admin/roomManagement");
    }
}