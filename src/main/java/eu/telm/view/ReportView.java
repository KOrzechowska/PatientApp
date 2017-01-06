package eu.telm.view;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import eu.telm.model.BadaniaDao;
import eu.telm.model.Realizacje;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kasia on 10.12.16.
 */
@SpringView(name = ReportView.VIEW_NAME)
public class ReportView extends VerticalLayout implements View {
    public static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
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
        tabelaBadan.setColumns("imie", "nazwisko", "nazwa", "opis");
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
        container.addContainerProperty("nazwa", String.class, "");
        container.addContainerProperty("opis", String.class, "");

        BadaniaDao badaniaDao = (BadaniaDao)context.getBean("badaniaDao");
        List<Realizacje> lista = badaniaDao.findByDate(new java.util.Date());
        for (Realizacje realizacje : lista){
            Item newItem = container.getItem(container.addItem());
            newItem.getItemProperty("imie").setValue(realizacje.getPatient().getImie());
            newItem.getItemProperty("nazwisko").setValue(realizacje.getPatient().getNazwisko());
            newItem.getItemProperty("nazwa").setValue(realizacje.getOperacja().getNazwa());
            newItem.getItemProperty("opis").setValue(realizacje.getOperacja().getOpis());
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
