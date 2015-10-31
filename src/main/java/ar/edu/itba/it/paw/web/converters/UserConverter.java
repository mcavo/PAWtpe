package ar.edu.itba.it.paw.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.models.User;
import ar.edu.itba.it.paw.services.UserService;

@Component
public class UserConverter implements Converter<String, User> {

	private UserService users;
	
	@Autowired
	public UserConverter(UserService users) {
		this.users = users;
	}
	
	public User convert(String arg0) {
		return users.get(Integer.valueOf(arg0)); 
	}
}
