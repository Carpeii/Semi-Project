package com.mywebapp.controllers.user;

import com.mywebapp.dao.RoomImageDao;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;
import com.mywebapp.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@WebServlet("/service/roomImageUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100)  // 100 MB
public class RoomImageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/service/roomImageUpload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Room room = (Room) session.getAttribute("room");

        if (room == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/service/roomAdd.jsp");
            return;
        }

        List<RoomImage> roomImages = new ArrayList<>();
        Collection<Part> parts = req.getParts();
        int imageOrder = 1;

        String uploadDirPath = getServletContext().getRealPath("/upload/");
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        for (Part part : parts) {
            if (part.getName().equals("imageFiles") && part.getSize() > 0) {
                String originFileName = part.getSubmittedFileName();
                String fileExtension = originFileName.substring(originFileName.lastIndexOf("."));
                String saveFileName = UUID.randomUUID().toString() + fileExtension;
                String uploadPath = uploadDirPath + File.separator + saveFileName;

                File file = new File(uploadPath);
                part.write(uploadPath);

                RoomImage roomImage = new RoomImage();
                roomImage.setImageName(originFileName);
                roomImage.setSaveFileName(saveFileName);
                roomImage.setImagePath("/upload/" + saveFileName); // 상대 경로 저장
                roomImage.setImageOrder(imageOrder);

                roomImages.add(roomImage);
                imageOrder++;
            }
        }

        req.setAttribute("roomImages", roomImages);
        resp.sendRedirect(req.getContextPath() + "/service/roomOptionAdd");
//        req.getRequestDispatcher(req.getContextPath() + "/jsp/service/roomOptionAdd").forward(req, resp);
    }
}