package ar.edu.itba.it.paw.services;

public class ValidateDataService {

	public ValidateDataService() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean validateMail(String email) {
		return false;
	}

	public static boolean validateDate (String date) {
		return false;
	}
	
	public static boolean validateBirth (String birth) {
		if (!ValidateDataService.validateDate(birth)) {
			return false;
		} 
		return false;
	}
}
