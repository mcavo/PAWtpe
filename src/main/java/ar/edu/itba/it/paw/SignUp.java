package ar.edu.itba.it.paw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.User;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			response.sendRedirect("homepage");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
		//Cómo conecto esto con el DOM
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		
		//Get address parameters		
		String street = request.getParameter("street");
		String number = request.getParameter("number");
		String floor = request.getParameter("floor");
		String apartment = request.getParameter("apartment");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String neighborhood = request.getParameter("neighborhood");
		
		//Get day
		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String year = request.getParameter("year");

		User user = null;
		try {
			//user = UserServiceImpl.signUp(email, pwd, firstName, lastName, day, month, year, false, street, number, city, province, neighborhood, floor, apartment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response); //Debería distinguirse si el mail está en uso, o hubo una falla de parámetros
			e.printStackTrace();
		}
		
		
		UserManager userManager = new SessionUserManager(request);
		userManager.setUser(user);
		response.sendRedirect("homepage");
	}
}
