package eu.telm.view;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import eu.telm.dataBase.DBOperation;
import eu.telm.models.SlownikOperacji;

import javax.annotation.PostConstruct;
import java.sql.SQLException;


/**
 * Created by kasia on 10.12.16.
 */
@SpringView(name = DictionaryView.VIEW_NAME)
public class DictionaryView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "dictionaryView";
    private Grid tabelaOpercaji;

    @PostConstruct
    void init() {
        VerticalLayout form = new VerticalLayout();
        Label titleLabel = new Label("Lista badań oraz zabiegów");
        titleLabel.addStyleName("title");

        tabelaOpercaji = new Grid();
        tabelaOpercaji.setColumns("nazwa", "opis", "typ");
        tabelaOpercaji.setSizeFull();
        tabelaOpercaji.setHeight(300, Unit.PIXELS);
        tabelaOpercaji.addStyleName("tabela");
        DBOperation dbOperation = new DBOperation();
        try {
            tabelaOpercaji.setContainerDataSource(
                    new BeanItemContainer(SlownikOperacji.class, dbOperation.getSlownikOperacji()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        form.addComponent(titleLabel);
        form.addComponent(tabelaOpercaji);
        addComponent(form);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}

