package ar.edu.itba.it.paw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.services.RestService;

/**
 * Servlet implementation class RegisterRestaurant
 */
public class RegisterRestaurant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterRestaurant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/registerRestaurant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String[] types = request.getParameterValues("checkboxes");
		String timeFrom = request.getParameter("from");
		String timeTo = request.getParameter("to");
		String street = request.getParameter("street");
		String number = request.getParameter("number");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String floor = request.getParameter("floor");
		String apartment = request.getParameter("apartment");
		String neighborhood = request.getParameter("neighborhood");
		String minimum = request.getParameter("minimum");
		String cost = request.getParameter("cost");

		try {
			RestService.setRestaurant(name, description, types, timeFrom, timeTo, street, number, city, province, floor, apartment, neighborhood, minimum, cost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect(response.encodeRedirectURL("registerrestaurant"));
			return;
		}
		
		response.sendRedirect("homepage");
	}

}
