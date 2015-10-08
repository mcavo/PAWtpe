package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;
import ar.edu.itba.it.paw.DAO.impl.UserDAOImpl;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.RestService;
import ar.edu.itba.it.paw.services.impl.CalificationServiceImpl;

@SuppressWarnings("serial")
public class ShowRestaurant extends HttpServlet {

	private User usr = null;
	private Restaurant rest = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		usr = (User) req.getAttribute("user");
		/*String usrId = "";
		UserManager userManager = new SessionUserManager(req);
		if (userManager.existsUser()) {
			usrId = userManager.getUserId();
		}*/
		
		String name = req.getParameter("name");
		String street = req.getParameter("srt");
		String number = req.getParameter("numb");
		String neighborhood = req.getParameter("neigh");
		String city = req.getParameter("city");
		String province = req.getParameter("prov");
		String floor = req.getParameter("flr");
		String apartment = req.getParameter("apt");

		/*rest = RestService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);
		rest.setCalifications(CalificationServiceImpl.getCalificationsByRestId(rest.getId()));

		req.setAttribute("rest", rest);

		if(usr != null){
			req.setAttribute("okToQualify", CalificationServiceImpl.canQualify(rest, usr.getId()));
		}else{
			req.setAttribute("okToQualify", false);
		}

		*/req.getRequestDispatcher("/WEB-INF/jsp/showRestaurant.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		usr = (User) req.getAttribute("user");
		
		String stars = req.getParameter("rating");
		String comments = req.getParameter("comment");
		/*String usrId = "";
		UserManager userManager = new SessionUserManager(req);
		*/
		String name = req.getParameter("name");
		String street = req.getParameter("srt");
		String number = req.getParameter("numb");
		String neighborhood = req.getParameter("neigh");
		String city = req.getParameter("city");
		String province = req.getParameter("prov");
		String floor = req.getParameter("flr");
		String apartment = req.getParameter("apt");

		//Restaurant rest = RestService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);
		//rest.setCalifications(CalificationServiceImpl.getCalificationsByRestId(rest.getId()));

		/*if (userManager.existsUser()) {
			usrId = userManager.getUserId();
		}*/
		//CalificationServiceImpl.addCalification(usr.getId(), rest, stars, comments);
		//req.setAttribute("rest", rest);
		req.getRequestDispatcher("/WEB-INF/jsp/showRestaurant.jsp").forward(req, resp);
	}
}
