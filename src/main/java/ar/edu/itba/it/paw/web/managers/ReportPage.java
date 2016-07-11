package ar.edu.itba.it.paw.web.managers;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.report.Card;
import ar.edu.itba.it.paw.domain.report.CardReport;
import ar.edu.itba.it.paw.domain.restaurant.OrderRepositoryType;
import ar.edu.itba.it.paw.domain.users.ManagerRepositoryType;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.base.BasePage;

public class ReportPage extends BasePage{

	private static final long serialVersionUID = 1L;

	@SpringBean
	private OrderRepositoryType orderRepo;
	
	@SpringBean
	private ManagerRepositoryType managerRepo;
	
	public ReportPage(){
		
		final IModel<List<CardReport>> reportModel = new LoadableDetachableModel<List<CardReport>>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected List<CardReport> load() {
				List<CardReport> report = new LinkedList<CardReport>();
				report = managerRepo.getReport(BaseSession.get().getUser());				
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
		add(listview);
	}
}
