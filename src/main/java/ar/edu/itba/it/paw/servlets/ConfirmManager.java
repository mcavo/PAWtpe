package ar.edu.itba.it.paw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.models.Credential;
import ar.edu.itba.it.paw.services.ManagerService;

/**
 * Servlet implementation class ConfirmManager
 */
public class ConfirmManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Credential managerCredential;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void setManagerCredential(Credential credential) {
    	this.managerCredential = credential;
    }
    
    public Credential getManagerCredential() {
    	return this.managerCredential;
    }
    
    public ConfirmManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String street = request.getParameter("srt");
		String number = request.getParameter("numb");
		String neighborhood = request.getParameter("neigh");
		String city = request.getParameter("city");
		String province = request.getParameter("prov");
		String floor = request.getParameter("flr");
		String apartment = request.getParameter("apt");
		
		try {
			ManagerService.SetManager(managerCredential, name, street, number, neighborhood, city, province, floor, apartment);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/jsp/addManager.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
