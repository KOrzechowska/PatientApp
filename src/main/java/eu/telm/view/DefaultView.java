package eu.telm.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import eu.telm.controller.PatientController;
import eu.telm.model.BadaniaDao;
import eu.telm.model.OperacjeDao;
import eu.telm.model.Patient;
import eu.telm.model.PatientDao;
import eu.telm.util.ButtonFactory;
import eu.telm.util.RowFactory;
import eu.telm.util.TextFieldFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Theme("colored")
@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";
    private PatientDao patientDao;
    private OperacjeDao operacjeDao;
    private BadaniaDao badaniaDao;
    private Button searchPatientButton;
    private Button editPatientButton;
    private UI ui;
    private SearchPatientSubWindow subWindow;
    private Patient patient;
    private PatientController patientController;
    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12;
    private Grid tabelaBadan, tabelaZabiegow;
    private DateField dateField;

    @Autowired
    public DefaultView(PatientDao patientDao, OperacjeDao operacjeDao, BadaniaDao badaniaDao, UI ui){
        this.patientDao = patientDao;
        this.operacjeDao = operacjeDao;
        this.badaniaDao = badaniaDao;
        this.ui = ui;
        patient = new Patient();
        subWindow = new SearchPatientSubWindow(patientDao);
        subWindow.setWidth("80%");
        subWindow.setHeight("80%");
        subWindow.center();
         patientController = new PatientController(subWindow,patient, this );
    }
    @PostConstruct
    void init() {
        VerticalLayout form = new VerticalLayout();
        form.setSpacing(true);
        form.setMargin(true);
        Panel patientDataPanel = new Panel("Dane Pacjenta");
        patientDataPanel.setIcon(FontAwesome.USER_MD);
        patientDataPanel.setSizeFull();
        form.addComponent(patientDataPanel);
        VerticalLayout patientData = new VerticalLayout();
        List<HorizontalLayout> horizontalLayouts = new ArrayList<>();
        List<Component> textFields = new ArrayList<>();
        tf1 = TextFieldFactory.createTextField("Imię", false, textFields);
        tf2 = TextFieldFactory.createTextField("Nazwisko", false, textFields);
        tf3 = TextFieldFactory.createTextField("Pesel", false, textFields);
        dateField = TextFieldFactory.createDateField("Data urodzenia", false, textFields);
        tf5 = TextFieldFactory.createTextField("Płeć", false, textFields);
        tf6 = TextFieldFactory.createTextField("Ulica", false, textFields);
        tf7 = TextFieldFactory.createTextField("Miasto", false, textFields);
        tf8 = TextFieldFactory.createTextField("Numer domu", false, textFields);
        tf9 = TextFieldFactory.createTextField("Kod pocztowy", false, textFields);
        tf10 = TextFieldFactory.createTextField("Numer telefonu", false, textFields);
        tf11 = TextFieldFactory.createTextField("Adres e-mail", false, textFields);
        CheckBox isInsured = TextFieldFactory.createCheckBox("Czy ubezpieczony", false, textFields);
        List<Component> buttonsPatientPanel = new ArrayList<>();
        editPatientButton = ButtonFactory.createButton("Edytuj", FontAwesome.EDIT, buttonsPatientPanel, "editButton");
        searchPatientButton = ButtonFactory.createButton("Szukaj", FontAwesome.SEARCH, buttonsPatientPanel, "searchButton");
        horizontalLayouts = RowFactory.createRowsLayout(textFields);
        horizontalLayouts.forEach(patientData::addComponent);
        patientData.addComponent(RowFactory.createRowLayout(buttonsPatientPanel, "rowOfButtonsPatientPanel"));

        patientDataPanel.setContent(patientData);

        addComponent(form);
        searchPatientButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                ui.addWindow(subWindow);

                subWindow.setClickController(patientController);


            }
        });
        editPatientButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                patientDao.getAll();
            }
        });
        Panel badaniaPanel = new Panel("Badania");
        badaniaPanel.setIcon(FontAwesome.STETHOSCOPE);
        badaniaPanel.setSizeFull();
        form.addComponent(badaniaPanel);
        tabelaBadan = new Grid();

        tabelaBadan.setStyleName("tabelaBadan");
        tabelaBadan.setSizeFull();
        tabelaBadan.setHeightByRows(1);
        //VerticalLayout mainLayout = new VerticalLayout(form, tabelaBadan);
        form.addComponent(tabelaBadan);

        List<Component> buttonListBadanie = new ArrayList<>();
        Button usunBadanie = ButtonFactory.createButton("Usuń", FontAwesome.TRASH, buttonListBadanie, "deleteButton");
        Button edytujBadanie = ButtonFactory.createButton("Edytuj", FontAwesome.EDIT, buttonListBadanie, "editButton");
        Button dodajWynikBadanie = ButtonFactory.createButton("Dodaj wynik", FontAwesome.BAR_CHART, buttonListBadanie, "addResultButton");
        Button dodajBadanie = ButtonFactory.createButton("Dodaj", FontAwesome.STETHOSCOPE, buttonListBadanie, "addButton");

        form.addComponent(RowFactory.createRowLayout(buttonListBadanie, "rowOfButtons"));

        Panel zabiegPanel = new Panel("Zabiegi");
        zabiegPanel.setIcon(FontAwesome.HEARTBEAT);
        zabiegPanel.setSizeFull();
        form.addComponent(zabiegPanel);
        tabelaZabiegow = new Grid();
        tabelaZabiegow.setStyleName("tabelaBadan");
        tabelaZabiegow.setColumns("nazwa","data", "wynik", "uwagi");
        tabelaZabiegow.setSizeFull();
        tabelaZabiegow.setHeight(300, Unit.PIXELS);
        form.addComponent(tabelaZabiegow);

        List<Component> buttonListZabieg = new ArrayList<>();
        Button usunZabieg = ButtonFactory.createButton("Usuń", FontAwesome.TRASH, buttonListZabieg, "deleteButton");
        Button edytujZabieg = ButtonFactory.createButton("Edytuj", FontAwesome.EDIT, buttonListZabieg, "editButton");
        Button dodajWynikZabieg = ButtonFactory.createButton("Dodaj wynik", FontAwesome.BAR_CHART, buttonListZabieg, "addResultButton");
        Button dodajZabieg = ButtonFactory.createButton("Dodaj", FontAwesome.STETHOSCOPE, buttonListZabieg, "addButton");

        form.addComponent(RowFactory.createRowLayout(buttonListZabieg, "rowOfButtons"));
    }

    public DateField getDateField() {
        return dateField;
    }

    public void setDateField(DateField dateField) {
        this.dateField = dateField;
    }

    public TextField getTextFieldImie(){
        return tf1;
    }
    public TextField getTextFieldNazwisko(){
        return tf2;
    }
    public TextField getTextFieldPesel(){return tf3;}
    public TextField getTextFieldDataUr(){return tf4;}
    public TextField getTextFieldPlec(){return tf5;}
    public TextField getTextFieldUlica(){return tf6;}
    public TextField getTextFieldMiasto(){return tf7;}
    public TextField getTextFieldNrDomu(){return tf8;}
    public TextField getTextFieldKodPocztowy(){return tf9;}
    public TextField getTextFieldNrTel(){return tf10;}
    public TextField getTextFieldEmail(){return tf11;}
    public TextField getTextFieldCzyUbezp(){return tf12;}
    public Grid getTabelaBadan(){return tabelaBadan;}

    public BadaniaDao getBadaniaDao(){return badaniaDao;}
    public OperacjeDao getOperacjeDao(){return operacjeDao;}

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}