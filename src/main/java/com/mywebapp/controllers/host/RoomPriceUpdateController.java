package com.mywebapp.controllers.host;

import com.mywebapp.dao.*;
import com.mywebapp.dto.RoomDetailDto;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.model.RoomOption;
import com.mywebapp.model.RoomPrice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/service/host/roomPriceUpdate")
public class RoomPriceUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        RoomDetailDto roomDetailDto = (RoomDetailDto) session.getAttribute("roomDetailDto");

        session.setAttribute("roomDetailDto", roomDetailDto);
        req.getRequestDispatcher("/jsp/service/host/roomPriceUpdate.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        RoomDetailDto roomDetailDto = (RoomDetailDto) session.getAttribute("roomDetailDto");

        roomDetailDto.setRoomPriceByRequest(req);

        RoomDao roomDao = new RoomDaoImpl();
        roomDao.updateRoomDetail(roomDetailDto);

        resp.sendRedirect(req.getContextPath() + "/jsp/service/hostMain.jsp");
    }
}
