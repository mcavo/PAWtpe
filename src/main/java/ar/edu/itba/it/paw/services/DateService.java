package ar.edu.itba.it.paw.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateService {

	final private static long CHANGE_TO_DAYS = 86400000; // 24 * 60 * 60 * 1000
	
	public static int getDayOfMonth(Date aDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(aDate);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date aDate) {
		Calendar date = new GregorianCalendar();
	    date.setTime(aDate);
		return date.get(Calendar.MONTH);
	}

	public static int getYear(Date aDate) {
		Calendar date = new GregorianCalendar();
	    date.setTime(aDate);
		return date.get(Calendar.YEAR);
	}

	public static Date date(int year, int month, int day) {
		Calendar date = new GregorianCalendar(year, month - 1, day);
	    return date.getTime();
	}

	public static boolean validateBirth(int year, int month, int day) {
		try {
			Calendar cal = Calendar.getInstance(); 
			cal.setLenient(false);
			cal.set(year, month - 1, day);
			cal.getTime();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static int datesFromDate(Date date) {
		long timeSinceLastChange = new Date().getTime() - date.getTime();
		return (int) (timeSinceLastChange / CHANGE_TO_DAYS);
	}
}
