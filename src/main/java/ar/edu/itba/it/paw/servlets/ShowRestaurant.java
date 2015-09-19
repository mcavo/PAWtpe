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
<<<<<<< HEAD
import ar.edu.itba.it.paw.DAO.UserDAO;
import ar.edu.itba.it.paw.models.Dish;
=======
import ar.edu.itba.it.paw.models.Menu;
>>>>>>> switch de showrest a menurest
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.CalificationService;
import ar.edu.itba.it.paw.services.RestService;

@SuppressWarnings("serial")
public class ShowRestaurant extends HttpServlet {

	private User usr = null;
	private Restaurant rest = null;

	private User usr = null;
	private Restaurant rest = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
<<<<<<< HEAD
		UserManager manager = new SessionUserManager(req);
		String userId = manager.getUser();
		User usr = UserDAO.getUser(userId);

=======
		
		usr = (User) req.getAttribute("user");
		String usrId = "";
		UserManager userManager = new SessionUserManager(req);
		if (userManager.existsUser()) {
			usrId = userManager.getUserId();
		}
		
>>>>>>> switch de showrest a menurest
		String name = req.getParameter("name");
		String street = req.getParameter("srt");
		String number = req.getParameter("numb");
		String neighborhood = req.getParameter("neigh");
		String city = req.getParameter("city");
		String province = req.getParameter("prov");
		String floor = req.getParameter("flr");
		String apartment = req.getParameter("apt");
<<<<<<< HEAD

		rest = RestService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);

		req.setAttribute("rest", rest);

		if (usr != null) {
			req.setAttribute("okToQualify", RestService.canQualify(rest, usr));
		} else {
			req.setAttribute("okToQualify", false);
		}
=======
		
		rest = RestService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);
		
		Menu menu = rest.getMenu();
		//List<Section> sections = menu.getSections();
		req.setAttribute("rest", rest);

		if(usr != null){
			req.setAttribute("okToQualify", CalificationService.canQualify(rest, usrId));
		}else{
			req.setAttribute("okToQualify", false);
		}
		req.getRequestDispatcher("/WEB-INF/jsp/showRestaurant.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String stars = req.getParameter("rating");
		String comments = req.getParameter("comment");
		String usrId = "";
		UserManager userManager = new SessionUserManager(req);
		if (userManager.existsUser()) {
			usrId = userManager.getUserId();
		}
		CalificationService.addCalification(usrId, rest, stars, comments);
		
>>>>>>> switch de showrest a menurest
		req.getRequestDispatcher("/WEB-INF/jsp/showRestaurant.jsp").forward(req, resp);
	}

	

}
