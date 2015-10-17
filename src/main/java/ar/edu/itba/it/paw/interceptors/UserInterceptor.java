package ar.edu.itba.it.paw.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ar.edu.itba.it.paw.SessionUserManager;
import ar.edu.itba.it.paw.UserManager;

public class UserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = (HttpServletRequest) req;
		UserManager userManager = new SessionUserManager(request);
		if (userManager.existsUser()) {
			//User user = UserService.getUserById(userManager.getUserId());
			request.setAttribute("user", userManager.getUser());
		}else{
			request.setAttribute("user", null);
		}
		return true;
	}

}
