package eu.telm.view;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import eu.telm.model.Operacja;
import eu.telm.model.OperacjeDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;


/**
 * Created by kasia on 10.12.16.
 */
@SpringView(name = DictionaryView.VIEW_NAME)
public class DictionaryView extends VerticalLayout implements View {
    public static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
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
        OperacjeDao operacjeDao = (OperacjeDao)context.getBean("operacjeDao");
        tabelaOpercaji.setContainerDataSource(
                new BeanItemContainer(Operacja.class, operacjeDao.getAll()));
        form.addComponent(titleLabel);
        form.addComponent(tabelaOpercaji);
        addComponent(form);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}

