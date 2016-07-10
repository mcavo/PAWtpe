package ar.edu.itba.it.paw.web.restaurant;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.Dish;
import ar.edu.itba.it.paw.domain.restaurant.Order;
import ar.edu.itba.it.paw.domain.restaurant.OrderRepositoryType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepositoryType;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.exceptions.CreationDishException;
import ar.edu.itba.it.paw.exceptions.LoadOrderException;
import ar.edu.itba.it.paw.web.BaseSession;
import ar.edu.itba.it.paw.web.authentication.LoginPage;
import ar.edu.itba.it.paw.web.base.BasePage;

public class RestaurantMenuPage extends BasePage{
	
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	RestaurantRepositoryType rests;
	
	@SpringBean
	OrderRepositoryType orders;
	
	private Restaurant r;
	
	//private List<DishPanel> dishPanels;
	private List<DishListPanel> dishListPanels;
	
	@SuppressWarnings({ "rawtypes" })
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
		
		add(new Label("sprice", "$ " + String.valueOf(r.getDelamount())));
		
		double tprice = 0;
		add(new Label("tprice", "$ " + String.valueOf(tprice)));
		
		double subprice = 0;
		add(new Label("subprice", "$ " + String.valueOf(subprice)));
		
		User user = BaseSession.get().getUser();
		Label lblCantOrder = new Label("lblCantOrder", getString("can_not_order"));
		boolean ok2order = false;
		if(user != null){
			ok2order = rests.userCanOrder(user, r);
		}
		lblCantOrder.setVisible(ok2order);
		add(lblCantOrder);
		
		dishListPanels = new LinkedList<>();
		
		final List<ListView<Dish>> dishLists = new LinkedList<>();
		
		Form<RestaurantMenuPage> form = new Form<RestaurantMenuPage>("orderForm",
				new CompoundPropertyModel<RestaurantMenuPage>(this)) {

					private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				
				if (!BaseSession.get().isSignedIn()) {
					setResponsePage(new LoginPage());
					return;
				}
				
				HashMap<Dish, Integer> oMap = new HashMap<>();
				Integer orderId = -1;
				for(DishListPanel dlp : dishListPanels) {
					for(DishPanel dp : dlp.getDishPanel()) {
						int dishCount = dp.getDishCountt();
						Dish dish = dp.getDish();
						oMap.put(dish, dishCount);
						System.out.println(dish.getProduct() + ": "+dishCount);
					}
				}
				
				try {
					orderId = orders.sendOrder(BaseSession.get().getUser(), r, oMap);
					if(orderId<0) {
						error(new StringResourceModel("min_cost",this, new Model<Restaurant>(r)));
						return;
					}
					System.out.println("orderId" + orderId);
				} catch (CreationDishException e) {
					return;
				}
				Order o = null;
				try {
					for(Order io : orders.getHistory(r)) {
						if (io.getId() == orderId) {
							o = io;
						}
					}
					if(o!=null)
						success(new StringResourceModel("success_order",this, new Model<Order>(o)));
				} catch (LoadOrderException e) {
					e.printStackTrace();
				}
				
				setResponsePage(getPage());
				
			}
			
		};
		
		List<Dish> dishes = r.getMenu();
		Map<String,List<Dish>> map = new HashMap<>();
		for(Dish d : dishes) {
			if(map.containsKey(d.getSection())) {
				map.get(d.getSection()).add(d);
			} else {
				List<Dish> l = new LinkedList<>();
				l.add(d);
				map.put(d.getSection(), l);
			}
		}
		
		List<List<Dish>> list = new LinkedList<>();
		
		for(List<Dish> l : map.values()) {
			list.add(l);
		}
		
		form.add(createDishListList("dishListList", list));
		
		form.add(new Button("orderButton", new ResourceModel("orderButton")));
		add(form);
		add(new FeedbackPanel("feedback"));
		
		
	}	
	
	private ListView<List<Dish>> createDishListList(String id, List<List<Dish>> dishes) {
		return new ListView<List<Dish>>(id, dishes) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<List<Dish>> item) {
				DishListPanel dishPanel = new DishListPanel("dishListPanel", item.getModelObject());
				dishListPanels.add(dishPanel);
				item.add(dishPanel);
			}
		};
	}

}
