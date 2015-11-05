package ar.edu.itba.it.paw.services;

public class NumberService {

	public NumberService() { }
	
	public static void validateBiggerThan(String cost) {
		float value = Float.valueOf(cost);
		if (value < 0) {
			throw new IllegalArgumentException(); 
		}
	}
	
	public static void validateMinimum(String minimum) {
		Double value = Double.valueOf(minimum);
		if (value < 0) {
			throw new IllegalArgumentException(); 
		}
	}	
	
	public static void validateMin(Integer num, int min) {
		if (num < min) {
			throw new IllegalArgumentException(); 
		}
	}
	
	public static void validateMax(Integer num, int max) {
		if (num > max) {
			throw new IllegalArgumentException(); 
		}
	}
}
