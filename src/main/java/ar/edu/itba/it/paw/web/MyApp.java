package ar.edu.itba.it.paw.web;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.web.common.CookieService;
import ar.edu.itba.it.paw.web.common.HibernateRequestCycleListener;
import ar.edu.itba.it.paw.web.common.SessionProvider;

@Component
public class MyApp extends WebApplication{
	
	private final SessionFactory sessionFactory;
	
	private CookieService cookieService = new CookieService();
    private SessionProvider sessionProvider;
	
    @Autowired
	public MyApp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return SignupPage.class;
	}
	
	@Override
	public Session newSession(Request request, Response response) {
		return (Session) new BaseSession(request);
	}
	
	public CookieService getCookieService(){
		return cookieService;
	}
	
	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		getRequestCycleListeners().add(new HibernateRequestCycleListener(sessionFactory));
		this.sessionProvider = new SessionProvider(cookieService);
		getDebugSettings().setAjaxDebugModeEnabled(false); 
		mountPage("/index", HomePage.class);
		mountPage("/login", LoginPage.class);
	}

}
