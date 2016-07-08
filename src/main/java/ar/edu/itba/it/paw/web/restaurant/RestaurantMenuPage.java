package ar.edu.itba.it.paw.web.restaurant;

import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.Dish;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.base.BasePage;

public class RestaurantMenuPage extends BasePage{
	
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	RestaurantRepositoryType rests;
	
	private Restaurant r;
	
	private List<DishPanel> dishPanels;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RestaurantMenuPage(int id){
		this.r = rests.getById(id);
		
		add(new Label("name", r.getName()));
		add(new Label("score", String.valueOf(r.getScore())));
		add(new Label("count", String.valueOf(r.getCountComments())));
		
		Link menuLink = new Link("menuLink"){
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {

			}
			
		};
		add(menuLink.setVisible(true));
		
		Link infoLink = new Link("infoLink"){

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new RestaurantPage(r));
			}
			
		};
		add(infoLink.setVisible(true));
		
		add(new Label("sprice", String.valueOf(r.getDelamount())));
		
		double tprice = 0;
		add(new Label("tprice", String.valueOf(tprice)));
		
		double subprice = 0;
		add(new Label("subprice", String.valueOf(subprice)));
		
		User user = BaseSession.get().getUser();
		Label lblCantOrder = new Label("lblCantOrder", getString("can_not_order"));
		boolean ok2order = false;
		if(user != null){
			ok2order = rests.userCanOrder(user, r);
		}
		lblCantOrder.setVisible(ok2order);
		add(lblCantOrder);
		
		dishPanels = new LinkedList<>();
		
		final List<ListView<Dish>> dishLists = new LinkedList<>();
		
		Form<RestaurantMenuPage> form = new Form<RestaurantMenuPage>("orderForm",
				new CompoundPropertyModel<RestaurantMenuPage>(this)) {

					private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				
			}
			
		};
		
		List<Dish> dishes = r.getMenu();
		
		form.add(createDishList("dishList", dishes));
		
		//form.add(new Button("orderButton", new ResourceModel("orderButton")));
		add(form);
		
		
	}

	private ListView<Dish> createDishList(String id, List<Dish> dishes) {
		return new ListView<Dish>(id, dishes) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<Dish> item) {
				DishPanel dishPanel = new DishPanel("dishPanel", item);
				dishPanels.add(dishPanel);
				item.add(dishPanel);
			}
		};
	}

}
