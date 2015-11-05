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
		try {
			List<String> validTypes;
			int floorV = -1;
			int numberV;
			float costV;
			float minimumPurchase;
			float from;
			float to;
			int neigh;
			float deliveryfrom, deliveryto;
			Set<Integer> validNeighs;

			from = Float.valueOf(form.getFrom().replace(':', '.'));
			to = Float.valueOf(form.getTo().replace(':', '.'));
			deliveryfrom = Float.valueOf(form.getDeliveryfrom().replace(':', '.'));
			deliveryto = Float.valueOf(form.getDeliveryto().replace(':', '.'));
			if (!form.getFloor().isEmpty()) {
				floorV = Integer.valueOf(form.getFloor());
				ValidateDataService.validateFloor(floorV);
			}
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
			ValidateDataService.validateInterval(from, to);
			ValidateDataService.validateInterval(deliveryfrom, deliveryto);
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
			e.reject("Error al registar el restaurante");
			return;
		}

	}
}
