package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.Order;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.RestService;
import ar.edu.itba.it.paw.services.impl.ManagerServiceImpl;
import ar.edu.itba.it.paw.services.impl.OrderServiceImpl;

@SuppressWarnings("serial")
public class ShowOrders extends HttpServlet {
	
	private User usr = null;
	private Restaurant rest = null;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Chequear si soy manager -> Homepage
		usr = (User) req.getAttribute("user");
		rest = ManagerServiceImpl.getRestByManager(usr);
		
		req.setAttribute("rest", rest);
		List<Order> olist = OrderServiceImpl.getHistoryOrder(rest);
		req.setAttribute("olist", olist);
		req.getRequestDispatcher("/WEB-INF/jsp/showOrders.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
