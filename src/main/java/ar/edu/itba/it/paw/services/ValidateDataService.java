package ar.edu.itba.it.paw.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDataService {
	private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

	private static final String[] TYPES_FOOD = {"arabe", "argentina", "armenia", "autor", "china", "deli", "italiana", "japonesa", "mexicana", "norteamericana", "parrilla", "peruana", "vegetariana"};
	
	public ValidateDataService() {
		// TODO Auto-generated constructor stub
	}

	public static void validateMail(String email) throws Exception {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		if (email.length() <= 40 && !matcher.matches()) {
			throw new Exception("Invalid email");
		}
	}
	
	public static boolean validateBirth (LocalDate birth) { 
		LocalDate today = LocalDate.now();
		boolean valid = today.getYear() - birth.getYear() < 120;
		return today.isAfter(birth) && valid;
	}
	
	public static void validateFloor(int i) throws Exception {
		if (i < -1) {
			throw new Exception();
		}
	}
	
	public static void validateApartment(String str) {
		if(str.length() > 1) {
			throw new IllegalArgumentException();
		}
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
	 * @throws Exception 
	 */
	// nombre - apellido - calle - provincia -localidad - barrio. (Campos obligatorios de tipo string) 
	public static void validateStringLength(String str, int max) throws Exception {
		if (!(str.length() > 0 && (str.length() <= max))) {
			throw new IllegalArgumentException("Invalid parameter");
		}
	}
	
	public static void validateInterval(Float from, Float to) throws Exception {
		if (to - from < 0) {
			throw new Exception();
		}
	}
	
	private static String validateType(String type) {
		boolean valid = false;
		for (String validsTypes : TYPES_FOOD) {
			if (type.length() < 30 && validsTypes.equals(type)) {
				valid = true;
			}
		}
		if (!valid) {
			throw new IllegalArgumentException();
		}
		return type;
	}
	
	public static ArrayList<String> validateTypes(String[] types) {
		ArrayList<String> ans = new ArrayList<String>();
		for (String type : types) {
			if (!validateType(type).equals("")) {
				ans.add(type);
			}
		}
		if (ans.size() == 0) {
			throw new IllegalArgumentException();
		}
		return ans;
	}
	
	public static float validateCost(String cost) throws  NumberFormatException, Exception {
		float value = Float.valueOf(cost);
		if (value < 0) {
			throw new IllegalArgumentException(); 
		}
		return value;
	}

	public static float validateMinimum(String minimum) {
		Float value = Float.valueOf(minimum);
		if (value < 0) {
			throw new IllegalArgumentException(); 
		}
		return value;
	}	
}
