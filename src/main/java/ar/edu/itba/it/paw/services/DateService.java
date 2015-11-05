package ar.edu.itba.it.paw.services;

import java.util.Calendar;
import java.util.Date;

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
}
