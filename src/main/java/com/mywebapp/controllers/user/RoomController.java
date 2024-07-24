package com.mywebapp.controllers.user;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomImageDao;
import com.mywebapp.dao.RoomOptionDao;
import com.mywebapp.dao.RoomPriceDao;
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
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/roomAdd")
public class RoomController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        try {
            connection = JdbcUtil.getCon();

            // Create Room
            Room room = new Room();
            room.setHostId(req.getParameter("hostId"));
            room.setRoomName(req.getParameter("roomName"));
            room.setJibunAddress(req.getParameter("jibunAddress"));
            room.setStreetAddress(req.getParameter("streetAddress"));
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

            RoomDao roomDao = new RoomDao();
            roomDao.create(room);

            // Create RoomImage, RoomOption, RoomPrice if provided
            // Example (repeat for RoomImage, RoomOption, RoomPrice)

            String[] imageNames = req.getParameterValues("imageName");
            String[] imagePaths = req.getParameterValues("imagePath");
            String[] imageOrders = req.getParameterValues("imageOrder");
            if (imageNames != null && imagePaths != null && imageOrders != null) {
                RoomImageDao roomImageDao = new RoomImageDao();
                for (int i = 0; i < imageNames.length; i++) {
                    RoomImage roomImage = new RoomImage();
                    roomImage.setRoomId(room.getId()); // Ensure room ID is set correctly
                    roomImage.setImageName(imageNames[i]);
                    roomImage.setImagePath(imagePaths[i]);
                    roomImage.setImageOrder(Integer.parseInt(imageOrders[i]));
                    roomImageDao.create(roomImage);
                }
            }

            // Handle RoomOption and RoomPrice similarly
            // Example code for RoomOption
            RoomOption roomOption = new RoomOption();
            roomOption.setRoomId(room.getId());
            roomOption.setRoomOption(req.getParameter("roomOption"));
            RoomOptionDao roomOptionDao = new RoomOptionDao();
            roomOptionDao.create(roomOption);

            // Example code for RoomPrice
            RoomPrice roomPrice = new RoomPrice();
            roomPrice.setRoomId(room.getId());
            roomPrice.setRentPrice(Integer.parseInt(req.getParameter("rentPrice")));
            roomPrice.setLongTerm(Integer.parseInt(req.getParameter("longTerm")));
            roomPrice.setLongTermDiscount(Integer.parseInt(req.getParameter("longTermDiscount")));
            roomPrice.setEarlyCheckIn(Integer.parseInt(req.getParameter("earlyCheckIn")));
            roomPrice.setEarlyCheckInDiscount(Integer.parseInt(req.getParameter("earlyCheckInDiscount")));
            roomPrice.setMaintenanceBill(Integer.parseInt(req.getParameter("maintenanceBill")));
            roomPrice.setMaintenanceBillDiscount(Integer.parseInt(req.getParameter("maintenanceBillDiscount")));
            roomPrice.setElectricity(req.getParameter("electricity") != null);
            roomPrice.setWater(req.getParameter("water") != null);
            roomPrice.setGas(req.getParameter("gas") != null);
            roomPrice.setInternet(req.getParameter("internet") != null);
            roomPrice.setCleaningFee(Integer.parseInt(req.getParameter("cleaningFee")));
            roomPrice.setRefundType(Integer.parseInt(req.getParameter("refundType")));
            RoomPriceDao roomPriceDao = new RoomPriceDao();
            roomPriceDao.create(roomPrice);

            resp.sendRedirect("success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        } finally {
            JdbcUtil.close(connection);
        }
    }
}
