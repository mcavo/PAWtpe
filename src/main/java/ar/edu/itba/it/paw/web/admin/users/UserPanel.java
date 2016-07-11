package ar.edu.itba.it.paw.web.admin.users;

import java.util.Locale;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.ocpsoft.prettytime.PrettyTime;

import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.domain.users.UserRepositoryType;

@SuppressWarnings("serial")
public class UserPanel extends Panel {
	
	@SpringBean private UserRepositoryType users;
	private Boolean blocked;
	
	public UserPanel(String id, User user) {
		super(id);
		
		Form<UserPanel> form = new Form<UserPanel>("userPanelForm", new CompoundPropertyModel<UserPanel>(this)) {
			@Override
			protected void onSubmit() {
				users.blockUser(user.getId(), blocked);
			}
		};
		blocked = user.getBlock();
		System.out.println(blocked);
		form.add(new Label("userName", new StringResourceModel("user_name", this, new Model<User>(user))));
		PrettyTime prettyTime = new PrettyTime(new Locale("es"));
		String lastConnection =  user.getLastConnection() != null ?  prettyTime.format(user.getLastConnection()) : " - ";
		form.add(new Label("lastConnection", lastConnection));
		
		form.add(new CheckBox("blocked", new PropertyModel<Boolean>(this, "blocked")));
		add(form);
	}
}
