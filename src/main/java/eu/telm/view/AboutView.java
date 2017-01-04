package eu.telm.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by kasia on 10.12.16.
 */
@SpringView(name = AboutView.VIEW_NAME)
public class AboutView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "aboutView";
    private String aboutText = "Aplikacja webowa, która będzie umożliwiała zarządzanie danymi personalnymi oraz medycznymi pacjenta. Głównymi funkcjonalnościami projektu będą:\n" +
            "dodanie pacjenta,\n" +
            "wyszukanie pacjenta,\n" +
            "edycja danych personalnych pacjenta,\n" +
            "dodanie badania lub zabiegu wybranemu pacjentowi,\n" +
            "edycja dodanego badania lub zabiegu,\n" +
            "wykonanie dodanego badanie lub zabiegu (dodanie wyniku),\n" +
            "możliwość usunięcia badania lub zabiegu, który nie posiada wyniku.\n" +
            "W zależności od uprawnień zalogowanego użytkownika będzie on mógł wykonywać tylko określone czynności.";

    @PostConstruct
    void init() {
        VerticalLayout layout = new VerticalLayout();
        String authorstext = "Katarzyna Orzechowska \n" +
                "Renata Plucińska \n"+ "Magdalena Uziębło \n";
        Label titleLabel = new Label("Autorzy:");
        titleLabel.addStyleName("title");
        Label authors = new Label(authorstext);
        Label titleLabel2 = new Label("Cel aplikacji:");
        titleLabel2.addStyleName("title");
        Label celLabel = new Label(aboutText);

        layout.addComponent(titleLabel);
        layout.addComponent(authors);
        layout.addComponent(titleLabel2);
        layout.addComponent(celLabel);

        addComponent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
