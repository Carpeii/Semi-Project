package com.mywebapp.controllers.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.mywebapp.actions.Action;
import com.mywebapp.actions.SearchAction;
import com.mywebapp.dao.RoomDao;
import com.mywebapp.model.Room;
import com.mywebapp.model.RoomPrice;
@WebServlet("/host/search")
public class SearchController  extends HttpServlet  {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Action action = new SearchAction();
		action.execute(req, resp);
	}

}
