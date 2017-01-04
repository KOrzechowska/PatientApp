package eu.telm.view;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import eu.telm.dataBase.DBOperation;
import eu.telm.models.Pacjent;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by kasia on 10.12.16.
 */
@SpringView(name = ReportView.VIEW_NAME)
public class ReportView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "reportView";
    private Grid tabelaBadan;

    @PostConstruct
    void init() {
        VerticalLayout form = new VerticalLayout();
        Label titleLabel = new Label("Raport dzienny");
        titleLabel.addStyleName("title");

        String subTitle = "Pacjenci, którzy posiadają badanie lub zabieg w dniu: "+ getCurrentDate();
        Label subtitleLabel = new Label(subTitle);

        tabelaBadan = new Grid();
        tabelaBadan.setColumns("imie", "nazwisko", "badanie", "zabieg");
        tabelaBadan.setSizeFull();
        tabelaBadan.setHeight(300, Unit.PIXELS);
        try {
            fillGrid();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        form.addComponent(titleLabel);
        form.addComponent(subtitleLabel);
        form.addComponent(tabelaBadan);
        addComponent(form);
    }

    private void fillGrid() throws SQLException, ParseException {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty("imie", String.class, "");
        container.addContainerProperty("nazwisko", String.class, "");
        container.addContainerProperty("badanie", String.class, "");
        container.addContainerProperty("zabieg", String.class, "");

        DBOperation dbOperation = new DBOperation();
        List<Pacjent> lista = dbOperation.getDailyReport("2016-09-09");
        for (Pacjent pacjent : lista){
            Item newItem = container.getItem(container.addItem());
            newItem.getItemProperty("imie").setValue(pacjent.getImie());
            newItem.getItemProperty("nazwisko").setValue(pacjent.getNazwisko());
            newItem.getItemProperty("badanie").setValue(pacjent.getRealizacjaBadaniaList().get(0).getOperacja().getNazwa());
            newItem.getItemProperty("zabieg").setValue(pacjent.getRealizacjaBadaniaList().get(0).getOperacja().getOpis());
        }
        tabelaBadan.setContainerDataSource(container);
    }

    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
