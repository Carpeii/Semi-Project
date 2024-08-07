package com.mywebapp.controllers.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

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
        if(req.getSession().getAttribute("selectDate") != null) {
        	req.getSession().removeAttribute("selectDate");
        	req.getSession().removeAttribute("selectEndDate");
        	req.getSession().removeAttribute("moveMonth");
        	System.out.println("달력 세션삭제");
        }
        // 폼 파라미터 읽기
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
        
        String checkInDateStr = (String)req.getParameter("checkInDate");
        String checkOutDateStr = (String)req.getParameter("checkOutDate");

        // 디버깅 출력
        System.out.println("DEBUG: roomIdParam = " + roomIdParam);
        System.out.println("DEBUG: roomName = " + roomName);
        System.out.println("DEBUG: jibunAddress = " + jibunAddress);
        System.out.println("DEBUG: streetAddress = " + streetAddress);
        System.out.println("DEBUG: addressDetail = " + addressDetail);
        System.out.println("DEBUG: floorParam = " + floorParam);
        System.out.println("DEBUG: hostName = " + hostName);
        System.out.println("DEBUG: rentPriceParam = " + rentPriceParam);
        System.out.println("DEBUG: longTermDiscountParam = " + longTermDiscountParam);
        System.out.println("DEBUG: earlyCheckInDiscountParam = " + earlyCheckInDiscountParam);
        System.out.println("DEBUG: maintenanceBillParam = " + maintenanceBillParam);
        System.out.println("DEBUG: cleaningFeeParam = " + cleaningFeeParam);
        System.out.println("DEBUG: checkInDateStr = " + checkInDateStr);
        System.out.println("DEBUG: checkOutDateStr = " + checkOutDateStr);
        
     // addressDetail이 null일 경우 빈 문자열로 초기화
        addressDetail = (addressDetail != null) ? addressDetail : "";

        // 유효성 검사 및 오류 처리
        if (roomIdParam != null && !roomIdParam.isEmpty() &&
            roomName != null && !roomName.isEmpty() &&
            jibunAddress != null && !jibunAddress.isEmpty() &&
            streetAddress != null && !streetAddress.isEmpty() &&
            addressDetail != null &&
            floorParam != null && !floorParam.isEmpty() &&
            hostName != null && !hostName.isEmpty() &&
            rentPriceParam != null && !rentPriceParam.isEmpty() &&
            longTermDiscountParam != null && !longTermDiscountParam.isEmpty() &&
            earlyCheckInDiscountParam != null && !earlyCheckInDiscountParam.isEmpty() &&
            maintenanceBillParam != null && !maintenanceBillParam.isEmpty() &&
            cleaningFeeParam != null && !cleaningFeeParam.isEmpty() &&
            checkInDateStr != null && !checkInDateStr.isEmpty() &&
            checkOutDateStr != null && !checkOutDateStr.isEmpty()) {

            try {
                long roomId = Long.parseLong(roomIdParam);
                int floor = Integer.parseInt(floorParam);
                int rentPrice = Integer.parseInt(rentPriceParam);
                int longTermDiscount = Integer.parseInt(longTermDiscountParam);
                int earlyCheckInDiscount = Integer.parseInt(earlyCheckInDiscountParam);
                int maintenanceBill = Integer.parseInt(maintenanceBillParam);
                int cleaningFee = Integer.parseInt(cleaningFeeParam);

                // 날짜 문자열을 java.util.Date 객체로 변환
                java.util.Date checkInDateUtil;
                java.util.Date checkOutDateUtil;
                try {
                    checkInDateUtil = DATE_FORMAT.parse(checkInDateStr);
                    checkOutDateUtil = DATE_FORMAT.parse(checkOutDateStr);
                } catch (ParseException e) {
                    req.setAttribute("errorMessage", "날짜 형식이 잘못되었습니다.");
                    req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
                    return;
                }

                // java.util.Date를 java.sql.Date로 변환
                java.sql.Date checkInDate = new java.sql.Date(checkInDateUtil.getTime());
                java.sql.Date checkOutDate = new java.sql.Date(checkOutDateUtil.getTime());

                // DTO 객체 생성 및 데이터 설정
                RoomDetailDto roomBookingInfo = new RoomDetailDto();
                roomBookingInfo.setId(roomId);
                roomBookingInfo.setRoomName(roomName);
                roomBookingInfo.setJibunAddress(jibunAddress);
                roomBookingInfo.setStreetAddress(streetAddress);
                roomBookingInfo.setAddressDetail(addressDetail);
                roomBookingInfo.setFloor(floor);
                roomBookingInfo.setHostName(hostName);
                roomBookingInfo.setRentPrice(rentPrice);
                roomBookingInfo.setLongTermDiscount(longTermDiscount);
                roomBookingInfo.setEarlyCheckInDiscount(earlyCheckInDiscount);
                roomBookingInfo.setMaintenanceBill(maintenanceBill);
                roomBookingInfo.setCleaningFee(cleaningFee);

                Booking booking = new Booking();
                booking.setCheckInDate(checkInDate);
                booking.setCheckOutDate(checkOutDate);
                // 요청 속성에 DTO 설정
                req.setAttribute("roomBookingInfo", roomBookingInfo);
                req.setAttribute("booking", booking);

                // JSP 페이지로 포워딩
                req.getRequestDispatcher("/jsp/service/bookingInfo.jsp").forward(req, resp);

            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "입력된 숫자 형식이 잘못되었습니다.");
                req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "필수 정보가 누락되었습니다.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}
