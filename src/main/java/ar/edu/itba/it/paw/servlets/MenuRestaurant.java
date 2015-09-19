package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;
import ar.edu.itba.it.paw.DAO.UserDAO;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.RestService;

public class MenuRestaurant extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserManager manager = new SessionUserManager(req); 
		String userId = manager.getUser(); 
		User usr = UserDAO.getUser(userId);
		
		String name = req.getParameter("name");
		String street = req.getParameter("srt");
		String number = req.getParameter("numb");
		String neighborhood = req.getParameter("neigh");
		String city = req.getParameter("city");
		String province = req.getParameter("prov");
		String floor = req.getParameter("flr");
		String apartment = req.getParameter("apt");
		
		Restaurant rest = RestService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);
		
		Menu menu = rest.getMenu();
		List<Section> sections = menu.getSections();
		req.setAttribute("rest", rest);

		if(usr != null){
			req.setAttribute("okToQualify", RestService.canQualify(rest, usr));
		}else{
			req.setAttribute("okToQualify", false);
		}
		req.getRequestDispatcher("/WEB-INF/jsp/menuRestaurant.jsp").forward(req, resp);
	}
}
