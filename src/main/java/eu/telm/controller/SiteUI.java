package eu.telm.controller;


import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import eu.telm.util.ButtonFactory;
import eu.telm.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Theme("colored")
@SpringUI
public class SiteUI extends UI {
    // we can use either constructor autowiring or field autowiring
    @Autowired
    private SpringViewProvider viewProvider;
    final CssLayout navigationBar = new CssLayout();
    private Button logOut;
    @Override
    protected void init(VaadinRequest request) {
        setTheme("colored");
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(false);
        root.setSpacing(true);
        setContent(root);

        logOut = createNavigationButton("Wyloguj",FontAwesome.PAUSE, SimpleLoginView.NAME);

        navigationBar.addStyleName("navigationBar");
        navigationBar.addComponent(createNavigationButton("Strona główna",FontAwesome.HOME, DefaultView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Raport",FontAwesome.DATABASE, ReportView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Słownik operacji",FontAwesome.BOOK, DictionaryView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("O programie",FontAwesome.INFO, AboutView.VIEW_NAME));
        navigationBar.addComponent(logOut);

        root.addComponent(navigationBar);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView(SimpleLoginView.NAME, SimpleLoginView.class);
        navigator.addProvider(viewProvider);

        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof SimpleLoginView;

                if (!isLoggedIn && !isLoginView) {
                    logOut.setVisible(false);
                    // Redirect to login view always if a user has not yet
                    // logged in
                    getNavigator().navigateTo(SimpleLoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {

                    // If someone tries to access to login view while logged in,
                    // then cancel
                    return false;
                }

                if(isLoggedIn) logOut.setVisible(true);
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeListener.ViewChangeEvent event) {

            }
        });
    }

    private Button createNavigationButton(String caption,FontAwesome fontAwesome, final String viewName) {
        Button button = new Button(caption,fontAwesome );
        button.addClickListener(event -> {
            if(caption.equals("Wyloguj")){
                getSession().setAttribute("user", null);
                getUI().getNavigator().navigateTo(viewName);
            }else
            getUI().getNavigator().navigateTo(viewName);
        });
        return button;
    }
}

