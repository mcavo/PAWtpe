package ar.edu.itba.it.paw.web.managers;

import java.text.SimpleDateFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.itba.it.paw.domain.restaurant.ClosingPeriod;

public class ClosingPeriodPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public ClosingPeriodPanel(String id, ClosingPeriod cp) {

		super(id);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		add(new Label("period", sdf.format(cp.getFrom()) + " - " + sdf.format(cp.getTo())));
		add(new Label("reason", cp.getDescription()));
		
		add(new Label("ptdate", cp.getToString()));

	}
}
