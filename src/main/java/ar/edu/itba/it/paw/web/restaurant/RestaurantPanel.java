package ar.edu.itba.it.paw.web.restaurant;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.joda.time.LocalDate;

import ar.edu.itba.it.paw.domain.restaurant.ClosingPeriod;
import ar.edu.itba.it.paw.domain.restaurant.ClosingPeriodRepositoryType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;


@SuppressWarnings("serial")
public class RestaurantPanel extends Panel{

	@SpringBean
	private ClosingPeriodRepositoryType closingPeriods;
	
	public RestaurantPanel(String id, Restaurant rest) {
		super(id);
		add(new Label("restName", rest.getName()));
		add(new Label("restAddress", rest.getAddress().toString()));
		add(new Label("restSchedule",new StringResourceModel("schedule",this, new Model<Restaurant>(rest))));
		
		String categories = "";
		for(String c : rest.getTypesOfFood()) {
			categories += "<span class='label label-primary'>" + c + "</span> ";
		}
		add(new Label("restCategories", categories).setEscapeModelStrings(false));
		
		add(new Label("restCalification",new StringResourceModel("califications",this, new Model<Restaurant>(rest))).setEscapeModelStrings(false));
		
		ClosingPeriod cp = closingPeriods.getLastClosingPeriod(rest);
		add(new Label("restClose",new StringResourceModel("close",this, new Model<ClosingPeriod>(cp))).setEscapeModelStrings(false).setVisible(cp!=null && !(new LocalDate()).isAfter(new LocalDate(cp.getFrom()))));
	}

}
