package eu.telm.controller;


import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import eu.telm.view.AboutView;
import eu.telm.view.DefaultView;
import eu.telm.view.DictionaryView;
import eu.telm.view.ReportView;
import org.springframework.beans.factory.annotation.Autowired;

@Theme("colored")
@SpringUI
public class SiteUI extends UI {
    // we can use either constructor autowiring or field autowiring
    @Autowired
    private SpringViewProvider viewProvider;

    @Override
    protected void init(VaadinRequest request) {
        setTheme("colored");
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName("navigationBar");
        navigationBar.addComponent(createNavigationButton("Strona główna",FontAwesome.HOME, DefaultView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Raport",FontAwesome.DATABASE, ReportView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("Słownik operacji",FontAwesome.BOOK, DictionaryView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("O programie",FontAwesome.INFO, AboutView.VIEW_NAME));
        root.addComponent(navigationBar);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
    }

    private Button createNavigationButton(String caption,FontAwesome fontAwesome, final String viewName) {
        Button button = new Button(caption,fontAwesome );
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }
}

