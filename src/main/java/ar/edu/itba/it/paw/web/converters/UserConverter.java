package ar.edu.itba.it.paw.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepository;

@Component
public class UserConverter implements Converter<String, User> {

	private UserRepository users;
	
	@Autowired
	public UserConverter(UserRepository users) {
		this.users = users;
	}
	
	public User convert(String arg0) {
		return users.getUserById(Integer.valueOf(arg0)); 
	}
}
