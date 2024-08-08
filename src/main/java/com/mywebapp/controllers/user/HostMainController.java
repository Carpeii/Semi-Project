package com.mywebapp.controllers.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hostMain")
public class HostMainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
    	//header.jsp를 위한 페이지 타입 설정
		req.setAttribute("pageType", "hostMain");
    	
    	req.getRequestDispatcher("/jsp/service/hostMain.jsp").forward(req, resp);
        

    }
}
