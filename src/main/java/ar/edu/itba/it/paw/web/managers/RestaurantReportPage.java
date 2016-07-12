package ar.edu.itba.it.paw.web.managers;

import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.users.ManagerRepositoryType;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.base.BasePage;

public class RestaurantReportPage extends BasePage{

	@SpringBean
	private ManagerRepositoryType managerRepo;
	
	public RestaurantReportPage(Restaurant rest, Neighborhood neigh, Date from, Date to) {
		add(new Label("restName", rest.getName()));
		add(new Label("neighName", neigh.getName()));
		final IModel<List<DetailCard>> reportDetailModel = new LoadableDetachableModel<List<DetailCard>>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected List<DetailCard> load() {
				return managerRepo.getReportDetail(BaseSession.get().getUser(), rest.getId(), neigh.getId(), from, to);				
			}
		};
		
		ListView<DetailCard> listview = new PropertyListView<DetailCard>("details", reportDetailModel) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<DetailCard> item) {
				item.add(new Label("day", String.valueOf(item.getModelObject().getDay())));
				item.add(new Label("hour", String.valueOf(item.getModelObject().getHour())));
				item.add(new Label("cant", String.valueOf(item.getModelObject().getAmount())));
			};

		};
		add(listview);
	}

}
