package ar.edu.itba.it.paw;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.DAO.UserDAO;
import ar.edu.itba.it.paw.models.Address;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;

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
		request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
		//Cómo conecto esto con el DOM
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = null;
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		
		//Get day
		short day = Integer.valueOf(request.getParameter("day")).shortValue();
		short month = Integer.valueOf(request.getParameter("month")).shortValue();
		int year = Integer.valueOf(request.getParameter("year")).intValue();
		LocalDate birth = LocalDate.of(year, month, day);
		
		String street = request.getParameter("street");
		int number = Integer.valueOf(request.getParameter("number")).intValue();
		int floor = Integer.valueOf(request.getParameter("floor")).intValue();
		String apartment = request.getParameter("apartment");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String neighborhood = request.getParameter("neighborhood");
		Address address = new Address(street, number, city, province, neighborhood);
		address.setFloor(floor);
		address.setApartment(apartment);
		
		user = new User(email, firstName, lastName, birth, false, address);
		try {
			user = UserService.signUp(email, pwd, firstName, lastName, birth, false, address);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserManager userManager = new SessionUserManager(request);
		userManager.setUser(String.valueOf(user.getId()));
		response.sendRedirect("homepage");
	}
}
