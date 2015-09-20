package ar.edu.itba.it.paw.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;
import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;

public class UserFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//Ver si esta logueado
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			//User user = UserService.getUserById(userManager.getUserId());
			request.setAttribute("user", userManager.getUser());
		}else{
			request.setAttribute("user", null);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
