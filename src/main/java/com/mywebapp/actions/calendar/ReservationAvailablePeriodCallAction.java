package com.mywebapp.actions.calendar;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebapp.actions.Action;
import com.mywebapp.service.BookingService;

public class ReservationAvailablePeriodCallAction implements Action {
    private static final String CALENDAR_URL = "/test/popup.jsp";
    private final BookingService bookingService;

    public ReservationAvailablePeriodCallAction() {
        this.bookingService = new BookingService();
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            long roomId = parseRoomId(req);
            LocalDate selectDate = parseSelectDate(req);

            int dateDiff = bookingService.calculateAvailablePeriod(selectDate, roomId);

            req.setAttribute("dateDiff", dateDiff);
            req.getRequestDispatcher(CALENDAR_URL).forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long parseRoomId(HttpServletRequest req) {
        return Long.parseLong(req.getParameter("roomId"));
    }

    private LocalDate parseSelectDate(HttpServletRequest req) {
        String dateString = req.getParameter("selectDate");
        return LocalDate.parse(dateString);
    }
}
