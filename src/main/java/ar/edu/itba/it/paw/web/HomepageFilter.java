package ar.edu.itba.it.paw.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class HomepageFilter extends OncePerRequestFilter {

	public HomepageFilter() {}

	public void destroy() {
	}
	
	private boolean validateUri(String url) {
		return true;
//		String[] parts = url.split("pawtpe.herokuapp.com");
//		if (parts.length == 2) {
//			return parts[1].equals("/");
//		}
//		return (url.equals("http://localhost:8000/"));
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		System.out.println(req.getContextPath());
		System.out.println(req.getRequestURL().toString());
		if (validateUri(req.getRequestURL().toString())) {
			resp.sendRedirect(req.getContextPath() + "/bin/homepage");
			return ;
		}
		filterChain.doFilter(request, response);		
	}
}