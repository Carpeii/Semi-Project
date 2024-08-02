package com.mywebapp.controllers.user;

import com.mywebapp.dao.RoomOptionDao;
import com.mywebapp.model.RoomOption;
import com.mywebapp.util.JdbcUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/service/roomOptionAdd")
public class RoomOptionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;

        try {
            con = JdbcUtil.getCon();
            con.setAutoCommit(false);

            long roomId = Long.parseLong(req.getParameter("roomId"));
            String optionsJson = req.getParameter("optionsJson");

            System.out.println("Room ID: " + roomId);
            System.out.println("Options JSON: " + optionsJson);

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
            roomOption.setRoomId(roomId);
            roomOption.setRoomOptions(optionsJson);

            RoomOptionDao roomOptionDao = new RoomOptionDao();
            roomOptionDao.insert(con, roomOption); // Pass connection to the DAO method

            con.commit();
            resp.sendRedirect(req.getContextPath() + "/jsp/service/roomPriceAdd.jsp");

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



