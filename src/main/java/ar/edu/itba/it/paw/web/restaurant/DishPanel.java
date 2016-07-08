package ar.edu.itba.it.paw.web.restaurant;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import ar.edu.itba.it.paw.domain.restaurant.Dish;

public class DishPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private Dish dish;
	private IModel<String> dishCountModel;
	private TextField<String> dishCountTextField;

	public DishPanel(String id, ListItem<Dish> dishModel) {
		super(id);
		this.dish = dishModel.getModelObject();
		
		add(new Label("name", new PropertyModel<String>(dish, "product")));
		add(new Label("description", new PropertyModel<String>(dish, "description")));
		add(new Label("price", new PropertyModel<String>(dish, "price")));
		
		dishCountModel = new Model<String>();
		dishCountTextField = new TextField<String>("dishCount", dishCountModel); 
		add(dishCountTextField);
		
	}
	
	public Dish getDish() {
		return dish;
	}
	
	public int getDishCount() {
		String dishCount = dishCountTextField.getModelObject();
		if(dishCount==null || dishCount.equals("")){
			return 0;
		}
		int aux;
		try {
			aux =  Integer.valueOf(dishCount);
		} catch ( NumberFormatException e) {
			return -1;
		}
		return aux;
	}

}
