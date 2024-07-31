package com.mywebapp.controllers.user;

import com.mywebapp.dao.BookingDao;
import com.mywebapp.dao.BookingDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/declineBooking")
public class DeclineBookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookingId = Long.parseLong(req.getParameter("bookingId"));
        BookingDao bookingDao = new BookingDaoImpl();
        bookingDao.declineBooking(bookingId);
        resp.getWriter().write("Success");
    }
}