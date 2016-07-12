package ar.edu.itba.it.paw.web.managers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

import ar.edu.itba.it.paw.domain.report.CardReport;
import ar.edu.itba.it.paw.domain.restaurant.OrderRepositoryType;
import ar.edu.itba.it.paw.domain.users.ManagerRepositoryType;
import ar.edu.itba.it.paw.services.DateService;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.base.BasePage;

public class ReportPage extends BasePage{

	private static final long serialVersionUID = 1L;

	@SpringBean
	private OrderRepositoryType orderRepo;
	
	@SpringBean
	private ManagerRepositoryType managerRepo;
	
	private Date from;
	private Date to;
	private Integer from_day;
	private Integer from_month;
	private Integer from_year;
	private Integer to_day;
	private Integer to_month;
	private Integer to_year;
	private boolean showAll;
	
	
	public ReportPage(){
		initialize();
		
	}

	public ReportPage(Date d_from, Date d_to) {
		this.from = d_from;
		this.to = d_to;
		initialize();
	}
	
	private void initialize(){
		Form<ReportPage> form = new Form<ReportPage>("filterDateForm", new CompoundPropertyModel<ReportPage>(this)) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				if (!DateService.validateBirth(from_year, from_month, from_day)) {
					error(getString("invalid_date"));
					return;
				}
				if (!DateService.validateBirth(to_year, to_month, to_day)) {
					error(getString("invalid_date"));
					return;
				}
				
				from = DateService.date(from_year, from_month, from_day);
				to = DateService.date(to_year, to_month, to_day);
				setResponsePage(new ReportPage(from,to));
			}
		};
		form.add(new TextField<Integer>("from_day")
				.add(new RangeValidator<Integer>(1, 31))
				.setRequired(true));
		form.add(new TextField<Integer>("from_month")
				.add(new RangeValidator<Integer>(1, 12))
				.setRequired(true));
		form.add(new TextField<Integer>("from_year")
				.add(new RangeValidator<Integer>(1900, null)) //TODO: this should change for future years 
				.setRequired(true));
		form.add(new TextField<Integer>("to_day")
				.add(new RangeValidator<Integer>(1, 31))
				.setRequired(true));
		form.add(new TextField<Integer>("to_month")
				.add(new RangeValidator<Integer>(1, 12))
				.setRequired(true));
		form.add(new TextField<Integer>("to_year")
				.add(new RangeValidator<Integer>(1900, null)) //TODO: this should change for future years 
				.setRequired(true));
		form.add(new FeedbackPanel("feedback"));
		form.add(new Button("find"));
		add(form);
		
		showAll = true;
		if(this.from == null || this.to == null){
			showAll = false;
		}
		final IModel<List<CardReport>> reportModel = new LoadableDetachableModel<List<CardReport>>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected List<CardReport> load() {
				List<CardReport> report = new LinkedList<CardReport>();
				if(showAll){
					report = managerRepo.getReport(BaseSession.get().getUser(), from, to);				
				}
				return report;
			}
		};
		
		ListView<CardReport> listview = new PropertyListView<CardReport>("report", reportModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<CardReport> item) {
				item.add(new Label("restName", item.getModelObject().getRest().getName()));
				item.add(new Label("restAddress", item.getModelObject().getRest().getAddress().toString()));
				item.add(new CardPanel("cardPanel", item.getModelObject()));
			};

		};
		
		listview.setVisible(showAll);
		add(listview);
	}
}
