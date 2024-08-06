package com.mywebapp.controllers.user;

import com.mywebapp.dao.*;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.model.RoomOption;
import com.mywebapp.model.RoomPrice;
import com.mywebapp.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/service/roomPriceAdd")
public class RoomPriceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/service/roomPriceAdd.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int rentPrice = Integer.parseInt(req.getParameter("rentPrice"));
        int longTerm = Integer.parseInt(req.getParameter("longTerm"));
        int longTermDiscount = Integer.parseInt(req.getParameter("longTermDiscount"));
        int earlyCheckIn = Integer.parseInt(req.getParameter("earlyCheckIn"));
        int earlyCheckInDiscount = Integer.parseInt(req.getParameter("earlyCheckInDiscount"));
        int maintenanceBill = Integer.parseInt(req.getParameter("maintenanceBill"));
        String maintenanceBillDetail = req.getParameter("maintenanceBillDetail");
        boolean electricity = req.getParameter("electricity") != null;
        boolean water = req.getParameter("water") != null;
        boolean gas = req.getParameter("gas") != null;
        boolean internet = req.getParameter("internet") != null;
        int cleaningFee = Integer.parseInt(req.getParameter("cleaningFee"));
        int refundType = Integer.parseInt(req.getParameter("refundType"));

        HttpSession session = req.getSession();
        Room room = (Room) session.getAttribute("room");
        List<RoomImage> roomImages = (List<RoomImage>) session.getAttribute("roomImages");
        RoomOption roomOption = (RoomOption) session.getAttribute("roomOption");

        RoomPrice roomPrice = new RoomPrice();
//        roomPrice.setRoomId(roomId);
        roomPrice.setRentPrice(rentPrice);
        roomPrice.setLongTerm(longTerm);
        roomPrice.setLongTermDiscount(longTermDiscount);
        roomPrice.setEarlyCheckIn(earlyCheckIn);
        roomPrice.setEarlyCheckInDiscount(earlyCheckInDiscount);
        roomPrice.setMaintenanceBill(maintenanceBill);
        roomPrice.setMaintenanceBillDetail(maintenanceBillDetail);
        roomPrice.setElectricity(electricity);
        roomPrice.setWater(water);
        roomPrice.setGas(gas);
        roomPrice.setInternet(internet);
        roomPrice.setCleaningFee(cleaningFee);
        roomPrice.setRefundType(refundType);

        RoomDao roomDao = new RoomDaoImpl();
        RoomImageDao roomImageDao = new RoomImageDao();
        RoomOptionDao roomOptionDao = new RoomOptionDao();
        RoomPriceDao roomPriceDao = new RoomPriceDao();

        long roomId = roomDao.insert(room);
        roomOption.setRoomId(roomId);
        roomPrice.setRoomId(roomId);

        for (RoomImage roomImage : roomImages) {
            roomImage.setRoomId(roomId);
            try {
                roomImageDao.insert(roomImage);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            roomOptionDao.insert(roomOption);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        roomPriceDao.insert(roomPrice);

        resp.sendRedirect(req.getContextPath() + "/jsp/service/hostMain.jsp");
    }
}
