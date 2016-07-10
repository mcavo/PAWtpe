package ar.edu.itba.it.paw.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import ar.edu.itba.it.paw.domain.users.Credential;

public class AuthenticationService {

	final private static String validDatysKeys = "passwordValidPeriod";
	private static AuthenticationService instance;
	
	public static AuthenticationService getInstance() {
		if (instance == null) {
			instance = new AuthenticationService();
		}
		return instance;
	}
	
	public boolean userNeedsUpdatePassword(Credential credential) {
		int validDays;
		try {
			validDays = getValidationDays(credential);
			if (credential.getLastPassUpdate() == null) { //TODO: this should be replace when foring BD
				return true;
			}
			int daysSinceLastUpdate = DateService.datesFromDate(credential.getLastPassUpdate());
			return daysSinceLastUpdate >= validDays;
		} catch (IOException e) {
			return false; //TODO: qué retornamos en caso de que falle.
		}
	}
	
	private int getValidationDays(Credential credential) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		if (inputStream != null) {
			properties.load(inputStream);
		}
	
		return Integer.valueOf(properties.getProperty(validDatysKeys));
	}
}
