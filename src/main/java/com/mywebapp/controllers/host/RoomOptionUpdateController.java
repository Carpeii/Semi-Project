package com.mywebapp.controllers.host;

import com.mywebapp.dto.RoomDetailDto;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.model.RoomOption;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/service/host/roomOptionUpdate")
public class RoomOptionUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        RoomDetailDto roomDetailDto = (RoomDetailDto) session.getAttribute("roomDetailDto");

        session.setAttribute("roomDetailDto", roomDetailDto);
        req.getRequestDispatcher("/jsp/service/host/roomOptionUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        RoomDetailDto roomDetailDto = (RoomDetailDto) session.getAttribute("roomDetailDto");

        String optionsJson = req.getParameter("optionsJson");

        // JSON 파싱
        JSONObject jsonObject = new JSONObject(optionsJson);
        JSONArray jsonArray = jsonObject.getJSONArray("options");

        // 배열을 문자열로 변환
        StringBuilder optionsString = new StringBuilder();
        for (int i = 0; i < jsonArray.length(); i++) {
            if (i > 0) {
                optionsString.append(", ");
            }
            optionsString.append(jsonArray.getString(i));
        }
//        RoomOption roomOption = new RoomOption();
//        roomOption.setRoomOptions(optionsJson);
        roomDetailDto.setRoomOptions(optionsJson);
        session.setAttribute("roomDetailDto", roomDetailDto);
        resp.sendRedirect(req.getContextPath() + "/jsp/service/roomOptionAdd.jsp");
    }
}



