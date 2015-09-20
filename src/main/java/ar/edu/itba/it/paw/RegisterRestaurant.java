package ar.edu.itba.it.paw;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.DAO.UserDAO;
import ar.edu.itba.it.paw.models.User;

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
		User user = null;
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		//Get day
		short day = Integer.getInteger(request.getParameter("day")).shortValue();
		short month = Integer.getInteger(request.getParameter("month")).shortValue();
		int year = Integer.getInteger(request.getParameter("year")).shortValue();
		LocalDate birth = LocalDate.of(year, month, day);
//		boolean isManager = request.getParameter("pwd");
//		String phoneNumber = request.getParameter("pwd");
//		Address address = request.getParameter("pwd");
		user = new User(name, null, birth);
		user.setEmail(email);
		try {
			UserDAO.getInstance().setUser(user, pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserManager userManager = new SessionUserManager(request);
		userManager.setUser(String.valueOf(user.getId()));
		response.sendRedirect("homepage");
	}

}
