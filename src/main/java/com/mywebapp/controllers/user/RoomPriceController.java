package com.mywebapp.controllers.user;

import com.mywebapp.dao.RoomPriceDao;
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

@WebServlet("/service/roomPriceAdd")
public class RoomPriceController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;

        try {
            con = JdbcUtil.getCon();
            con.setAutoCommit(false);

            long roomId = Long.parseLong(req.getParameter("roomId"));
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

            RoomPrice roomPrice = new RoomPrice();
            roomPrice.setRoomId(roomId);
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

            RoomPriceDao roomPriceDao = new RoomPriceDao();
            roomPriceDao.insert(roomPrice);

            con.commit();
            resp.sendRedirect(req.getContextPath() + "/jsp/service/hostMain.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            JdbcUtil.close(con);
        }
    }
}
