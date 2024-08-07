package com.mywebapp.controllers.host;

import com.mywebapp.dto.RoomDetailDto;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomImage;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@WebServlet("/service/host/roomImageUpdate")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100)  // 100 MB
public class RoomImageUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        RoomDetailDto roomDetailDto = (RoomDetailDto) session.getAttribute("roomDetailDto");

        session.setAttribute("roomDetailDto", roomDetailDto);
        req.getRequestDispatcher("/jsp/service/host/roomImageUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        RoomDetailDto roomDetailDto = (RoomDetailDto) session.getAttribute("roomDetailDto");

        if (roomDetailDto == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/service/host/roomUpdate.jsp");
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

                if (!fileExtension.equals(".jpg") && !fileExtension.equals(".png") && !fileExtension.equals(".jpeg")) {
                    continue; // 이미지 파일이 아니면 무시
                }

                File file = new File(uploadPath);
                part.write(uploadPath);

                RoomImage roomImage = new RoomImage();
                roomImage.setImageName(originFileName);
                roomImage.setSaveFileName(saveFileName);
                roomImage.setImagePath("/upload/" + saveFileName); // 상대 경로 저장
                roomImage.setImageOrder(imageOrder);

                roomImages.add(roomImage);

                roomDetailDto.setImageName(roomImage.getImageName());
                roomDetailDto.setImagePath(roomImage.getImagePath());
                roomDetailDto.setSaveFileName(roomImage.getSaveFileName());
                roomDetailDto.setImageOrder(roomImage.getImageOrder());

                //여러개 올리는 코드였는데 js땜에 못하겠어서 포기
                imageOrder++;
            }
        }

        session.setAttribute("roomDetailDto", roomDetailDto);
        System.out.println(roomDetailDto.getRoomOptions());
        resp.sendRedirect(req.getContextPath() + "/service/host/roomOptionUpdate");
    }
}