package ar.edu.itba.it.paw.web.restaurant;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import ar.edu.itba.it.paw.domain.restaurant.Dish;

public class DishPanel extends Panel {
	
	private static final long serialVersionUID = 1L;
	
	private static final List<String> dishCountList = Arrays.asList(new String[] {
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" });
	
	private Dish dish;
	private String dishCount = "0";
	
	public DishPanel(String id, ListItem<Dish> dishModel) {
		super(id);
		this.dish = dishModel.getModelObject();
		
		add(new Label("name", dish.getProduct()));
		add(new Label("description", dish.getDescription()));
		add(new Label("price", String.valueOf(dish.getPrice())));
		
		dishCount = "0";
		add(new DropDownChoice<String>("dishCount", new PropertyModel<String>(this,"dishCount"),dishCountList)
				.setRequired(true));	

	}
	
	public Dish getDish() {
		return dish;
	}
	
	public int getDishCount() {
		System.out.println(dishCount);
		if(dishCount==null || dishCount.equals(""))
			return 0;
		return Integer.parseInt(dishCount);
	}

}
