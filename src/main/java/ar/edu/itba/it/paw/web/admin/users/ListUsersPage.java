package ar.edu.itba.it.paw.web.admin.users;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepositoryType;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.base.BasePage;

@SuppressWarnings("serial")
public class ListUsersPage extends BasePage {

	@SpringBean private UserRepositoryType users;
	
	public ListUsersPage() {
		
		if (loggedUser == null || !loggedUser.getIsAdmin()) {
			setResponsePage(new HomePage());
		}
		
		add(new Label("title", new ResourceModel("title")));
		
		final IModel<List<User>> ordersModel = new LoadableDetachableModel<List<User>>() {

			@Override
			protected List<User> load() {
				return users.getAll();
			}
		};
		
		ListView<User> listview = new PropertyListView<User>("users", ordersModel) {

			@Override
			protected void populateItem(ListItem<User> item) {
				item.add(new UserPanel("userPanel", item.getModelObject()));
			}

		};
		add(listview);
	}

}
