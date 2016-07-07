//package ar.edu.itba.it.paw.web.common.validators;
//
//import java.util.Date;
//
//import org.apache.wicket.util.lang.Classes;
//import org.apache.wicket.validation.IValidatable;
//import org.apache.wicket.validation.IValidator;
//import org.apache.wicket.validation.ValidationError;
//
//public class DateValidator implements IValidator<Date> {
//
//	private Date firstDate;
//	private Date lastDate;
//	
//	public DateValidator(Date firstDate, Date lastDate) {
//		this.firstDate = firstDate;
//		this.lastDate = lastDate;
//	}
//
//	public DateValidator(Date firstDate) {
//		this(firstDate, new Date());
//	}
//	
//	@Override
//	public void validate(IValidatable<Date> validatable) {
//		 value = validatable.getValue();
//		if (value % divisor != 0)
//		{
//			ValidationError error = new ValidationError();
//			error.addMessageKey(resourceKey());
//			error.setVariable("divisor", divisor);
//			validatable.error(error);
//		}
//	}
//	
//	protected String resourceKey()	{
//		return Classes.simpleName(DivisibleValidator.class);
//	}
//}
