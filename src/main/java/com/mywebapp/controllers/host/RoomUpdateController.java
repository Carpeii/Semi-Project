package com.mywebapp.controllers.host;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dto.RoomDetailDto;
import com.mywebapp.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/service/host/roomUpdate")
public class RoomUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        RoomDao roomDao = new RoomDaoImpl();
        RoomDetailDto roomDetailDto;
        long roomId = Long.parseLong(req.getParameter("roomId"));
        roomDetailDto = roomDao.getRoomById(roomId);

        HttpSession session = req.getSession();
        session.setAttribute("roomDetailDto", roomDetailDto);
        req.getRequestDispatcher("/jsp/service/host/roomUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        RoomDetailDto roomDetailDto = (RoomDetailDto) session.getAttribute("roomDetailDto");

        roomDetailDto.setRoomByRequest(req);
        session.setAttribute("roomDetailDto", roomDetailDto);
        resp.sendRedirect(req.getContextPath() + "/service/host/roomImageUpdate");
    }
}
