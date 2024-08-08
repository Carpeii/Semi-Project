package com.mywebapp.controllers.user;

import com.mywebapp.dao.RoomDao;
import com.mywebapp.dao.RoomDaoImpl;
import com.mywebapp.dao.RoomOptionDao;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.model.RoomOption;
import com.mywebapp.util.JdbcUtil;
import org.json.JSONArray;
import org.json.JSONObject;

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

@WebServlet("/service/roomOptionAdd")
public class RoomOptionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/service/roomOptionAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Room room = (Room) session.getAttribute("room");
        List<RoomImage> roomImages = (List<RoomImage>) session.getAttribute("roomImages");

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
        RoomOption roomOption = new RoomOption();
        roomOption.setRoomOptions(optionsJson);

        session.setAttribute("roomOption", roomOption);
        session.setAttribute("roomImages", roomImages);
        session.setAttribute("room", room);

        resp.sendRedirect(req.getContextPath() + "/service/roomPriceAdd");
    }
}



