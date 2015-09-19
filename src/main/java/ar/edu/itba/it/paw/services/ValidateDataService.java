package ar.edu.itba.it.paw.services;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDataService {
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	
	public ValidateDataService() {
		// TODO Auto-generated constructor stub
	}

	public static boolean validateMail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return email.length() <= 40 && matcher.matches();
	}
	
	public static boolean validateBirth (LocalDate birth) { 
		LocalDate today = LocalDate.now();
		boolean valid = today.getYear() - birth.getYear() < 120;
		return today.isAfter(birth) && valid;
	}
	
	public static boolean validateFloor(int i) {
		return i >= -1;
	}
	
	public static boolean validateApartment(String str) {
		return str.length() <= 1;
	}
	
	public static boolean validatePsw(String psw) {
		return psw.length() >= 8 && (psw.length() <= 16);
	}
	
	/**
	 * This method validates that str has longitued longer than 0 and shorter than the max. 
	 * It's used to validate obligatory fields with a maximun length. 
	 * @param str
	 * @param max
	 * @return
	 */
	// nombre - apellido - calle - provincia -localidad - barrio. (Campos obligatorios de tipo string) 
	public static boolean validateStringLength(String str, int max) {
		return str.length() > 0 && (str.length() <= max);
	}
}
