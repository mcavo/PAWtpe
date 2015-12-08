package ar.edu.itba.it.paw.validators;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.domain.users.Question;
import ar.edu.itba.it.paw.forms.EditProfileForm;

@Component
public class ProfileValidator implements Validator {

		private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

		@Override
		public boolean supports(Class<?> clazz) {
			return EditProfileForm.class.equals(clazz);
		}

		@Override
		public void validate(Object o, Errors e) {
			EditProfileForm form = (EditProfileForm) o;
			String firstname = form.getFirstname();
			String lastname = form.getLastname();
			// Address address = form.getAddress();
			Question question = form.getQuestion();
			String answer = form.getAnswer();
			String email = form.getEmail();
			int day, month, year, neigh;
			try {
				day = Integer.parseInt(form.getBirthDay());
				month = Integer.parseInt(form.getBirthMonth());
				year = Integer.parseInt(form.getBirthYear());
				neigh = Integer.parseInt(form.getNeigh());
				if(!validateBirth(year, month, day) || neigh < 1 || (answer.length() <= 0 && 40 < answer.length())) {
					e.reject("Error al registrarse");
				}
			} catch (NumberFormatException ex) {
				e.reject("Error al registrarse");
				return;
			}

			if (firstname.length() == 0 || lastname.length() == 0 || email.length() == 0) {
				e.reject("Error al registrarse");
			}
			
			if(!validateMail(email)) {
				e.reject("Error al registrarse");
			}
		}

		private static boolean validateBirth(int year, int month, int day) {
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
			try {
				date = LocalDate.parse(dateToValidate,sdf);
			} catch (DateTimeParseException e) {
				return false;
			}
			ChronoLocalDate today = LocalDate.now();
			return date.isBefore(today);
		}

		public static boolean validateMail(String email) {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (email.length() > 40 || !matcher.matches()) {
				return false;
			}
			return true;
		}
}
