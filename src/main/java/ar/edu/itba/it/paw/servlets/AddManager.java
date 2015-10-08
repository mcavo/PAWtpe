package ar.edu.itba.it.paw.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.DAO.impl.UserDAOImpl;
import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.models.Restaurant;
import ar.edu.itba.it.paw.services.RestService;
import ar.edu.itba.it.paw.services.impl.ManagerServiceImpl;

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
		List<Restaurant> rlist;
		List<Credential> clist;
		//rlist = (new RestService()).getAllRestaurants();
		//clist = ManagerServiceImpl.getManagersAvailables();
		//request.setAttribute("rlist", rlist);
		//request.setAttribute("clist", clist);
		request.getRequestDispatcher("/WEB-INF/jsp/addManager.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("manager-mail");
		String restid = request.getParameter("restaurant-id");
		try {
			//if (!ManagerServiceImpl.addManager(mail,restid)) {
				//response.sendRedirect("/PAWTPE/admin/addmanager");
			//}
				response.sendRedirect("/PAWTPE/homepage");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/PAWTPE/homepage");
			return;
		}
		
		
		return;
	}

}
