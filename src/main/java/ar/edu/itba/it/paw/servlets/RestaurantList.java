
package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.Calification;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.RestService;
import ar.edu.itba.it.paw.services.impl.CalificationServiceImpl;

public class RestaurantList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		String filterType = req.getParameter("type");
		//Implementar orderby despues.
		List<Restaurant> rlist;
		if(filterType==null)
			rlist = (new RestService()).getAllRestaurants();
		else
			rlist = (new RestService()).getRestaurants(filterType);
		for(Restaurant rest : rlist) {
			rest.setCalifications(CalificationServiceImpl.getCalificationsByRestId(rest.getId()));
			HashMap<Integer,Calification> map = CalificationServiceImpl.getCalificationsByRestId(rest.getId());
			rest.setCalifications(map);
		}
		req.setAttribute("name", name);
		req.setAttribute("rlist", rlist);
		
		req.getRequestDispatcher("/WEB-INF/jsp/restaurantList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
