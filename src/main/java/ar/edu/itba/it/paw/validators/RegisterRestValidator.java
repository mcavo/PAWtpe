package ar.edu.itba.it.paw.validators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ar.edu.itba.it.paw.forms.RegisterRestForm;
import ar.edu.itba.it.paw.forms.SignupForm;
import ar.edu.itba.it.paw.services.ValidateDataService;

@Component
public class RegisterRestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SignupForm.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors e) {
		RegisterRestForm form = (RegisterRestForm) o;
		List<String> validTypes;
		int numberV;
		float costV;
		float minimumPurchase;
		int neigh;
		Set<Integer> validNeighs;
			
		String[] from_split = form.getFrom().split(":");
		if(from_split.length != 2)
			e.reject("Horario de apertura inválido");
		else { 
			try {
				int from_hour = Integer.valueOf(from_split[0]);
				int from_min = Integer.valueOf(from_split[1]);
				if(!validateHour(from_hour) || !validateMin(from_min))
					e.reject("Horario de apertura inválido");
			} catch (NumberFormatException err) {
				e.reject("Horario de apertura inválido");
			}
		}
			
		String[] to_split = form.getTo().split(":");
		if(to_split.length != 2)
			e.reject("Horario de cierre inválido");
		else { 
			try {
				int to_hour = Integer.valueOf(to_split[0]);
				int to_min = Integer.valueOf(to_split[1]);
				if(!validateHour(to_hour) || !validateMin(to_min))
					e.reject("Horario de cierre inválido");
			} catch (NumberFormatException err) {
				e.reject("Horario de cierre inválido");
			}
		}
			
		String[] deliveryfrom_split = form.getDeliveryfrom().split(":");
		if(deliveryfrom_split.length != 2)
			e.reject("Horario de apertura de delivery inválido");
		else { 
			try {
				int deliveryfrom_hour = Integer.valueOf(deliveryfrom_split[0]);
				int deliveryfrom_min = Integer.valueOf(deliveryfrom_split[1]);
				if(!validateHour(deliveryfrom_hour) || !validateMin(deliveryfrom_min))
					e.reject("Horario de apertura de delivery inválido");
			} catch (NumberFormatException err) {
				e.reject("Horario de apertura de delivery inválido");
			}
		}
			
		String[] deliveryto_split = form.getDeliveryto().split(":");
		if(deliveryto_split.length != 2)
			e.reject("Horario de cierre de delivery inválido");
		else { 
			try {
				int deliveryto_hour = Integer.valueOf(deliveryto_split[0]);
				int deliveryto_min = Integer.valueOf(deliveryto_split[1]);
				if(!validateHour(deliveryto_hour) || !validateMin(deliveryto_min))
					e.reject("Horario de cierre de delivery inválido");
			} catch (NumberFormatException err) {
				e.reject("Horario de cierre de delivery inválido");
			}
		}
			
		if (!form.getFloor().isEmpty()) {
			try {
				int floorV = Integer.valueOf(form.getFloor());
				ValidateDataService.validateFloor(floorV);
			} catch (NumberFormatException err) {
				e.reject("Piso inválido");
			} catch (Exception e1) {
				e.reject("Piso inválido");
			}
		}
		
		if (!form.getFloor().isEmpty()) {
			try {
				int floorV = Integer.valueOf(form.getFloor());
				ValidateDataService.validateFloor(floorV);
			} catch (NumberFormatException err) {
				e.reject("Piso inválido");
			} catch (Exception e1) {
				e.reject("Piso inválido");
			}
		}
		try {
			numberV = Integer.valueOf(form.getNumber());
			neigh = Integer.valueOf(form.getNeigh());
			validNeighs = new HashSet<Integer>();
			for (String s : form.getDelneigh()) {
				validNeighs.add(Integer.parseInt(s));
			}
			ValidateDataService.validateStringLength(form.getName(), 30);
			if (form.getDescription() != null && !form.getDescription().isEmpty()) {
				ValidateDataService.validateStringLength(form.getDescription(), 500);
			}
			ValidateDataService.validateStringLength(form.getStreet(), 30);
			ValidateDataService.validateStringLength(form.getCity(), 30);
			ValidateDataService.validateStringLength(form.getProv(), 30);
			if (form.getApartment() != null && !form.getApartment().isEmpty()) {
				ValidateDataService.validateApartment(form.getApartment());
			}
			validTypes = ValidateDataService.validateTypes(form.getTfood());
			costV = ValidateDataService.validateCost(form.getDelamount());
			minimumPurchase = ValidateDataService.validateMinimum(form.getMinamount());
		} catch (Exception er) {
			e.reject("Error al agregar el restaurante");
		}

	}

	private boolean validateMin(int min) {
		return min>=0 && min<60;
	}

	private boolean validateHour(int hour) {
		return hour>=0 && hour<24;
	}
}
