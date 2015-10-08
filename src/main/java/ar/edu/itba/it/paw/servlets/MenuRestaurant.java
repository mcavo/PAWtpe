package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;
import ar.edu.itba.it.paw.models.Dish;
import ar.edu.itba.it.paw.models.Menu;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.models.Section;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.RestService;
import ar.edu.itba.it.paw.services.impl.CalificationServiceImpl;
import ar.edu.itba.it.paw.services.impl.OrderServiceImpl;

public class MenuRestaurant extends HttpServlet {

	private User usr = null;
	private Restaurant rest = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String street = req.getParameter("srt");
		String number = req.getParameter("numb");
		String neighborhood = req.getParameter("neigh");
		String city = req.getParameter("city");
		String province = req.getParameter("prov");
		String floor = req.getParameter("flr");
		String apartment = req.getParameter("apt");

		/*Restaurant rest = RestService.getRestaurant(name, street, number, neighborhood, city, province, floor,
				apartment);
		rest.setCalifications(CalificationServiceImpl.getCalificationsByRestId(rest.getId()));
		req.setAttribute("rest", rest);
		*/req.getRequestDispatcher("/WEB-INF/jsp/menuRestaurant.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

		/*Restaurant rest = RestService.getRestaurant(name, street, number, neighborhood, city, province, floor, apartment);
		
		Enumeration en = req.getParameterNames();
		HashMap<Dish, String> map = new HashMap<Dish, String>();
		while (en.hasMoreElements()) {
			Object objOri = en.nextElement();
			String param = (String) objOri;
			String value = req.getParameter(param);
			
			Dish d = OrderServiceImpl.getDishByRestIdName(rest.getId(),param);
			if(d != null)
				map.put(d,value);	
		}
		if(!OrderServiceImpl.sendOrder(usr.getId(), rest, map));
		req.setAttribute("rest", rest);
		*/req.getRequestDispatcher("/WEB-INF/jsp/showRestaurant.jsp").forward(req, resp);

	}

}