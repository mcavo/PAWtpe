package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.DAO.UserDAO;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.services.ManagerService;
import ar.edu.itba.it.paw.services.RestService;

/**
 * Servlet implementation class AddManager
 */
public class AddManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/addManager.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Credential cred = null;
		List<Restaurant> rlist = null;
		String email = request.getParameter("email");
		try {
			cred = ManagerService.validateEmail(email);
			rlist = RestService.getAllRestaurants();
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/jsp/addManager.jsp"); 
			return;
		}
		request.setAttribute("manager", email);
		request.setAttribute("credential", cred);
		request.setAttribute("rlist", rlist);
		request.getRequestDispatcher("/WEB-INF/jsp/selectRestaurant.jsp").forward(request, response);
	}

}
