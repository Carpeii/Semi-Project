package com.mywebapp.controllers.user;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dto.UserDto;
import com.mywebapp.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/service/hostRoomList")
public class HostRoomListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // Database connection setup (use a connection pool in a real application)
////        String hostId = req.getParameter("hostId");
//        long hostId;
////        if(hostId == null || hostId.isEmpty()) {
////
////            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Host ID is required");
////            return;
////        }
//        System.out.println(hostId);
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        UserDto user = (UserDto) session.getAttribute("user");
        RoomDao roomDao = new RoomDaoImpl();
        List<Room> rooms = roomDao.getRoomsByHostId(user.getId());

        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("/jsp/service/hostRoomList.jsp").forward(req, resp);
    }
}