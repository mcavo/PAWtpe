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
import ar.edu.itba.it.paw.services.CalificationService;
import ar.edu.itba.it.paw.services.RestService;

public class Homepage extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//response.setContentType("text/html");*/

		List<Restaurant> weekRests = RestService.getLastWeekRestaurants();
		for(Restaurant rest : weekRests) {
			rest.setCalifications(CalificationService.getCalificationsByRestId(rest.getId()));
			HashMap<Integer,Calification> map = CalificationService.getCalificationsByRestId(rest.getId());
			rest.setCalifications(map);
		}
		request.setAttribute("weekRests", weekRests);
		request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
