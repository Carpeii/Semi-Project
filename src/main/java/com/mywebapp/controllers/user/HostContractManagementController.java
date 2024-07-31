package com.mywebapp.controllers.user;

import com.mywebapp.dao.BookingDao;
import com.mywebapp.dao.BookingDaoImpl;
import com.mywebapp.model.Booking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/service/hostContractManagement")
public class HostContractManagementController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookingDao bookingDao = new BookingDaoImpl();
        long roomId= Long.parseLong(req.getParameter("roomId"));
        List<Booking> bookings = bookingDao.getBookingsByRoomId(roomId, 0);
        req.setAttribute("bookings", bookings);
        req.getRequestDispatcher("/jsp/service/hostContractManagement.jsp").forward(req, resp);
    }
}
