
package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.RestService;

public class RestaurantList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User usr = (User) req.getAttribute("user");		
		String name = req.getParameter("name");
		String address = req.getParameter("addr");
		
		String filterType = req.getParameter("type");
		//Implementar orderby despues.
		List<Restaurant> rlist;
		if(filterType==null)
			rlist = RestService.getAllRestaurants();
		else
			rlist = RestService.getRestaurants(filterType);

		req.setAttribute("name", name);
		req.setAttribute("rlist", rlist);
		
		req.getRequestDispatcher("/WEB-INF/jsp/restaurantList.jsp").forward(req, resp);
	}
}
