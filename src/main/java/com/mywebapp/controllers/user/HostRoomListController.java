package com.mywebapp.controllers.user;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dto.MemberDto;
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
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        MemberDto user = (MemberDto) session.getAttribute("user");
        RoomDao roomDao = new RoomDaoImpl();
        List<Room> rooms = roomDao.getRoomsByHostId(user.getId());

        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("/jsp/service/hostRoomList.jsp").forward(req, resp);
    }
}