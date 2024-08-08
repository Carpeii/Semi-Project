package com.mywebapp.controllers.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mywebapp.dto.RoomDetailDto;
import com.mywebapp.model.Booking;

@WebServlet("/service/bookingInfo")
public class BookingInfoController extends HttpServlet {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 세션 초기화
        if (req.getSession().getAttribute("selectDate") != null) {
            req.getSession().removeAttribute("selectDate");
            req.getSession().removeAttribute("selectEndDate");
            req.getSession().removeAttribute("moveMonth");
            System.out.println("달력 세션삭제");
        }

        // 요청 파라미터 가져오기
        String checkInDateStr = req.getParameter("checkInDate");
        String checkOutDateStr = req.getParameter("checkOutDate");

        // 필수 파라미터 가져오기
        String roomIdParam = req.getParameter("roomId");
        String roomName = req.getParameter("roomName");
        String jibunAddress = req.getParameter("jibunAddress");
        String streetAddress = req.getParameter("streetAddress");
        String addressDetail = req.getParameter("addressDetail");
        String floorParam = req.getParameter("floor");
        String hostName = req.getParameter("hostName");
        String rentPriceParam = req.getParameter("rentPrice");
        String longTermDiscountParam = req.getParameter("longTermDiscount");
        String earlyCheckInDiscountParam = req.getParameter("earlyCheckInDiscount");
        String maintenanceBillParam = req.getParameter("maintenanceBill");
        String cleaningFeeParam = req.getParameter("cleaningFee");

        try {
            // 데이터 변환
            long roomId = Long.parseLong(roomIdParam);
            int floor = Integer.parseInt(floorParam);
            int rentPrice = Integer.parseInt(rentPriceParam);
            int longTermDiscount = Integer.parseInt(longTermDiscountParam);
            int earlyCheckInDiscount = Integer.parseInt(earlyCheckInDiscountParam);
            int maintenanceBill = Integer.parseInt(maintenanceBillParam);
            int cleaningFee = Integer.parseInt(cleaningFeeParam);

            // 날짜 변환
            java.util.Date checkInDateUtil = DATE_FORMAT.parse(checkInDateStr);
            java.util.Date checkOutDateUtil = DATE_FORMAT.parse(checkOutDateStr);

            java.sql.Date checkInDate = new java.sql.Date(checkInDateUtil.getTime());
            java.sql.Date checkOutDate = new java.sql.Date(checkOutDateUtil.getTime());

            // DTO 객체 생성 및 데이터 설정
            RoomDetailDto roomBookingInfo = new RoomDetailDto();
            roomBookingInfo.setBookingInfoByRequest(req);

            Booking booking = new Booking();
            booking.setCheckInDate(checkInDate);
            booking.setCheckOutDate(checkOutDate);

            // 요청 속성에 DTO 설정
            req.setAttribute("roomBookingInfo", roomBookingInfo);
            req.setAttribute("booking", booking);

            // JSP 페이지로 포워딩
            req.getRequestDispatcher("/jsp/service/bookingInfo.jsp").forward(req, resp);

        } catch (NumberFormatException | ParseException e) {
            req.setAttribute("errorMessage", "입력된 데이터 형식이 잘못되었습니다.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}
