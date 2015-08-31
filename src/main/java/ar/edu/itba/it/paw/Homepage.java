package ar.edu.itba.it.paw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Homepage extends HttpServlet{

	private String email;
	private String pwd;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			System.out.println("en el if");
			//email = userManager.getEmail();
		}else{
			email = request.getParameter("email");
			pwd = request.getParameter("pwd");
			if(!DBManager.getInstance().verifyUser(email, pwd)){
				response.sendRedirect(response.encodeRedirectURL("login"));
				return;
			}
			userManager.setUser(DBManager.getInstance().getUserId(email, pwd), pwd);
		}
		//response.setContentType("text/html");*/
		request.setAttribute("email", email);
		request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setPwd(String pwd){
		this.pwd = pwd;
	}

}
