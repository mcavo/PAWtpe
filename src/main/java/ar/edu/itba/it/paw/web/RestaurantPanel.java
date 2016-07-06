package ar.edu.itba.it.paw.web;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.StringValidator;

public class RestaurantPanel extends Panel{

	public RestaurantPanel(String id, boolean showName) {
		super(id);
		
		TextField<String> name = new TextField<String>("name");
		//name.setRequired(true);
		//name.setVisible(showName);
		//name.add(new StringValidator.MaximumLengthValidator(NAMELENGTH));
		//add(new ComponentFeedbackPanel("name_error", get("name")));
	}

}
