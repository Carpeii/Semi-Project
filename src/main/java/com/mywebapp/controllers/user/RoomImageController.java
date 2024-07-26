package com.mywebapp.controllers.user;

import com.mywebapp.dao.RoomImageDao;
import com.mywebapp.model.RoomImage;
import com.mywebapp.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.UUID;

@WebServlet("/service/roomImageUpload")
public class RoomImageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;

        try {
            con = JdbcUtil.getCon();
            con.setAutoCommit(false);

            long roomId = Long.parseLong(req.getParameter("roomId"));
            Collection<Part> parts = req.getParts();
            RoomImageDao romDao = new RoomImageDao();
            int imageOrder = 1;

            for (Part part : parts) {
                if(part.getName().equals("imageFiles") && part.getSize() > 0) {
                    String originFileName = part.getSubmittedFileName();
                    String fileExtension = originFileName.substring(originFileName.lastIndexOf("."));
                    String saveFileName = UUID.randomUUID().toString() + "." + fileExtension;
                    String uploadPath = getServletContext().getRealPath("/upload/") + saveFileName;

                    File file = new File(uploadPath);
                    part.write(uploadPath);
                    RoomImage roomImage = new RoomImage();
                    roomImage.setRoomId(roomId);
                    roomImage.setImageName(originFileName);
                    roomImage.setSaveFileName(saveFileName);
                    roomImage.setImagePath(uploadPath);
                    roomImage.setImageOrder(imageOrder);

                    imageOrder++;
                }
                con.commit();
                req.setAttribute("roomId", roomId);
                req.getRequestDispatcher("/jsp/service/roomOptionAdd.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if(con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            resp.sendError(500);
        }finally {
            JdbcUtil.close(con);
        }
    }
}
