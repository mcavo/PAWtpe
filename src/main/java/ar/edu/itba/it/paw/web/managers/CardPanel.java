package ar.edu.itba.it.paw.web.managers;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.report.Card;
import ar.edu.itba.it.paw.domain.report.CardReport;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.web.restaurant.RestaurantReportPage;

public class CardPanel extends Panel{

	public CardPanel(String id, CardReport report) {
		super(id);
		final IModel<List<Card>> cardModel = new LoadableDetachableModel<List<Card>>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected List<Card> load() {
				List<Card> cards = new LinkedList<Card>();
				cards = report.getCards();				
				return cards;
			}
		};
	
		ListView<Card> cardview = new PropertyListView<Card>("cards", cardModel) {
			private static final long serialVersionUID = 1L;
	
			@Override
			protected void populateItem(ListItem<Card> item) {
				Link<Card> reportLink = new Link<Card>("viewReport",
						item.getModel()) {

					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						Restaurant r = report.getRest();
						Neighborhood n = item.getModelObject().getNeighbourhood();
						setResponsePage(new RestaurantReportPage(r.getId(), n.getId()));
					}

				};
				reportLink.add(new Label("neighbourhood", item.getModelObject().getNeighbourhood().getName()));
				item.add(reportLink);
				//item.add(new Label("neighbourhood", item.getModelObject().getNeighbourhood().getName()));
				item.add(new Label("amount", String.valueOf(item.getModelObject().getCant())));
			}
		};
		add(cardview);
	}
}
