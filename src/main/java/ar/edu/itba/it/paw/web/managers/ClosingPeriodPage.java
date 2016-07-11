package ar.edu.itba.it.paw.web.managers;

import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator.MaximumLengthValidator;

import ar.edu.itba.it.paw.domain.restaurant.ClosingPeriod;
import ar.edu.itba.it.paw.domain.restaurant.ClosingPeriodRepositoryType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.users.ManagerRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.exceptions.NoRestaurantException;
import ar.edu.itba.it.paw.services.DateService;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.base.BasePage;

public class ClosingPeriodPage extends BasePage {

	private static final long serialVersionUID = 1L;
	
	private Restaurant restaurant;
	
	private Date from;
	private Integer fromDay;
	private Integer fromMonth;
	private Integer fromYear;
	
	private Date to; 
	private Integer toDay;
	private Integer toMonth;
	private Integer toYear;
	
	private String description;
	
	@SpringBean
	private ManagerRepositoryType managers;
	
	@SpringBean
	private ClosingPeriodRepositoryType closingPeriods;
	
	public ClosingPeriodPage() {
		
		User user = BaseSession.get().getUser();
		System.out.println(user == null);
		
		BaseSession session = BaseSession.get();
		
		try {
			restaurant = managers.getRestaurant(session.getUser());
		} catch (NoRestaurantException e) {
			e.printStackTrace();
			return;
		}
		
		Form<ClosingPeriodPage> form = new Form<ClosingPeriodPage>("cpForm", new CompoundPropertyModel<ClosingPeriodPage>(this)) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				
				if (!DateService.validateBirth(fromYear, fromMonth, fromDay)) {
					error(getString("cp.from.failed.invalidDate"));
					return;
				}
				
				if (!DateService.validateBirth(toYear, toMonth, toDay)) {
					error(getString("cp.to.failed.invalidDate"));
					return;
				}
				
				from = DateService.date(fromYear, fromMonth, fromDay);
				to = DateService.date(toYear, toMonth, toDay);
				
				if(from.after(to)) {
					error(getString("cp.failed.invalidRangeDate"));
					return;
				}
				
				ClosingPeriod cp = new ClosingPeriod(from, to, restaurant);
				
				cp.setDescription(description);
				
				success(getString("cp.success.creation"));
				closingPeriods.add(cp);
				setResponsePage(this.getPage());
				
			}
		};
		
		form.add(new TextArea<String>("description").add(new MaximumLengthValidator(Restaurant.DESCRIPTIO_MAX_LENGTH)).setRequired(true));
		
		form.add(new TextField<Integer>("fromDay")
				.add(new RangeValidator<Integer>(1, 31))
				.setRequired(true));
		form.add(new TextField<Integer>("fromMonth")
				.add(new RangeValidator<Integer>(1, 12))
				.setRequired(true));
		form.add(new TextField<Integer>("fromYear")
				.add(new RangeValidator<Integer>(1900, null)) //TODO: this should change for future years 
				.setRequired(true));
		
		form.add(new TextField<Integer>("toDay")
				.add(new RangeValidator<Integer>(1, 31))
				.setRequired(true));
		form.add(new TextField<Integer>("toMonth")
				.add(new RangeValidator<Integer>(1, 12))
				.setRequired(true));
		form.add(new TextField<Integer>("toYear")
				.add(new RangeValidator<Integer>(1900, null)) //TODO: this should change for future years 
				.setRequired(true));
		
		form.add(new Button("submit", new ResourceModel("cp.submit.label")));
		form.add(new FeedbackPanel("feedback"));
		
		add(form);
		
		final IModel<List<ClosingPeriod>> cpModel = new LoadableDetachableModel<List<ClosingPeriod>>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected List<ClosingPeriod> load() {
				return closingPeriods.getClosingHistorial(restaurant);
			}
		};

		ListView<ClosingPeriod> listview = new PropertyListView<ClosingPeriod>("cps",
				cpModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<ClosingPeriod> item) {
				ClosingPeriodPanel cpPanel = new ClosingPeriodPanel("cpPanel", item.getModelObject());
				item.add(cpPanel);
			}

		};
		add(listview);
		
		add(new Label("restName",restaurant.getName()));
			
	}

}
