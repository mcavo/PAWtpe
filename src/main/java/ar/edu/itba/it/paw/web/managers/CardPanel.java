package ar.edu.itba.it.paw.web.managers;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.itba.it.paw.domain.address.Neighborhood;
import ar.edu.itba.it.paw.domain.report.Card;
import ar.edu.itba.it.paw.domain.report.CardReport;

public class CardPanel extends Panel{

	public CardPanel(String id, CardReport report) {
		super(id);
		final IModel<List<Card>> cardModel = new LoadableDetachableModel<List<Card>>() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected List<Card> load() {
//				List<Card> cards = new LinkedList<Card>();
//				cards = report.getCards();				
//				return cards;
				return report.getCards();
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
//						Restaurant r = report.getRest();
//						Neighborhood n = item.getModelObject().getNeighbourhood();
//						setResponsePage(new RestaurantReportPage(r.getId(), n.getId(), report.getFrom(), report.getTo()));
						setResponsePage(new RestaurantReportPage(report.getRest(), item.getModelObject().getNeighbourhood(), report.getFrom(), report.getTo()));
					}

				};
				//reportLink.add(new Label("neighbourhood", item.getModelObject().getNeighbourhood().getName()));
				reportLink.add(new Label("lblNeighbourhood", new StringResourceModel("neighbourhood", this, new Model<Neighborhood>(item.getModelObject().getNeighbourhood()))));
				item.add(reportLink);
				//item.add(new Label("amount", String.valueOf(item.getModelObject().getCant())));
				item.add(new Label("lblAmount", new StringResourceModel("amount", this, new Model<String>(String.valueOf(item.getModelObject().getCant())))));
			}
		};
		add(cardview);
	}
}
