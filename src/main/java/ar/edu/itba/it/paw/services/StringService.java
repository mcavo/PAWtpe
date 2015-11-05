package ar.edu.itba.it.paw.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringService {

	private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	
	public StringService() {}

	public static void validateMail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		if (email.length() > 40 || !matcher.matches()) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void validateMaximumLength(String string, int length) {
		if (string.length() > length) {
			throw new IllegalArgumentException("Too long string:" + String.valueOf(length));
		}
	}
}





