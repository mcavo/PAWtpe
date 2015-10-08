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
import ar.edu.itba.it.paw.services.impl.ManagerServiceImpl;

public class Homepage extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Restaurant> weekRests = RestService.getLastWeekRestaurants();
		for(Restaurant rest : weekRests) {
			rest.setCalifications(CalificationServiceImpl.getCalificationsByRestId(rest.getId()));
			HashMap<Integer,Calification> map = CalificationServiceImpl.getCalificationsByRestId(rest.getId());
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
