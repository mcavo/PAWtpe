package ar.edu.itba.it.paw.services;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;

public class TimeService {

	public static String getString(LocalTime time) { 
		return time.toString(DateTimeFormat.forPattern("HH:mm"));
	}

	// TODO: REMOVED IF NOT USED
	public static boolean validateTimeRange(LocalTime begin, LocalTime end) {
		return begin.isBefore(end);
	}
	
	public static String getTimeString(int hour, int minutes) {
		LocalTime time = new LocalTime(hour, minutes);
		return getString(time);
	}
	
	public static boolean validateTimeInRange(LocalTime beginRange, LocalTime endRange, LocalTime timeToValidate) {
		if(beginRange.isAfter(endRange)) {
			LocalTime beginOfDay = new LocalTime("00:00");
			LocalTime endOfDay = new LocalTime("23:59");
			return !((timeToValidate.isAfter(beginOfDay) && timeToValidate.isBefore(endRange)) 
					|| (timeToValidate.isAfter(beginRange) && timeToValidate.isBefore(endOfDay)));
		}
		return !(timeToValidate.isAfter(beginRange) && timeToValidate.isBefore(endRange));	
	}
	
}
