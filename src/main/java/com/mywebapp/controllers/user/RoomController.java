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

@WebServlet("/service/roomAdd")
public class RoomController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;

        try {
            con = JdbcUtil.getCon();

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

            RoomDao roomDao = new RoomDao();
            
            if(roomDao.insert(room) != -1){
                long roomId = Long.parseLong(req.getParameter("roomId"));
                req.setAttribute("roomId", roomId);
                req.getRequestDispatcher("/roomImageAdd.jsp").forward(req, resp);
            }else {
                resp.sendRedirect("error.jsp");
            }
        } finally {
            JdbcUtil.close(con);
        }
    }

}
