package ar.edu.itba.it.paw.services;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class DateService {

	public static int getDayOfMonth(Date aDate) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(aDate);
	    return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	@SuppressWarnings("deprecation")
	public static int getMonth(Date aDate) {
		return aDate.getMonth()+1;
	}

	@SuppressWarnings("deprecation")
	public static int getYear(Date aDate) {
		return aDate.getYear()+1900;
	}
	
	@SuppressWarnings("deprecation")
	public static Date date(int year, int month, int day) {
		return new Date(year-1900,month-1,day);
	}
	
	public static void validateBirth(int year, int month, int day) {
		Formatter fmt = new Formatter();
		fmt.format("%04d", year);
		String dateToValidate = fmt.toString() + "/";
		fmt.close();
		fmt = new Formatter();
		fmt.format("%02d", month);
		dateToValidate += fmt.toString() + "/";
		fmt.close();
		fmt = new Formatter();
		fmt.format("%02d", day);
		dateToValidate += fmt.toString();
		fmt.close();
		System.out.println(dateToValidate);
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate date;
		date = LocalDate.parse(dateToValidate,sdf);
		ChronoLocalDate today = LocalDate.now();
		if (date.isBefore(today)) {
			throw new IllegalArgumentException("Invalid date");
		}
	}
}
