package com.mywebapp.controllers.user;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.model.Room;
import com.mywebapp.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/service/roomAdd")
public class RoomAddController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/service/roomAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // Create Room
        Room room = new Room();
        room.setHostId(req.getParameter("hostId"));
        room.setRoomName(req.getParameter("roomName"));
        room.setJibunAddress(req.getParameter("jibunAddress"));
        room.setStreetAddress(req.getParameter("streetAddress"));
        room.setAddressDetail(req.getParameter("addressDetail"));
        room.setFloor(Integer.parseInt(req.getParameter("floor")));
        room.setUsableArea(Integer.parseInt(req.getParameter("usableArea")));
        room.setRoomCount(Integer.parseInt(req.getParameter("roomCount")));
        room.setLivingRoomCount(Integer.parseInt(req.getParameter("livingRoomCount")));
        room.setToiletCount(Integer.parseInt(req.getParameter("toiletCount")));
        room.setKitchenCount(Integer.parseInt(req.getParameter("kitchenCount")));
        room.setDuplex(req.getParameter("duplex") != null);
        room.setElevator(req.getParameter("elevator") != null);
        room.setPark(req.getParameter("park") != null);
        room.setParkDetail(req.getParameter("parkDetail"));
        room.setRoomType(Integer.parseInt(req.getParameter("roomType")));
        room.setMinimumContract(Integer.parseInt(req.getParameter("minimumContract")));
        room.setApprove(Integer.parseInt(req.getParameter("approve")));

        HttpSession session = req.getSession();
        session.setAttribute("room", room);

        resp.sendRedirect(req.getContextPath() + "/service/roomImageUpload");
    }
}
