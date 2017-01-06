package eu.telm.view;

import com.vaadin.annotations.Theme;
import com.vaadin.event.Action;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import eu.telm.controller.PatientController;
import eu.telm.controller.TestController;
import eu.telm.model.*;
import eu.telm.util.ButtonFactory;
import eu.telm.util.DialogWindow;
import eu.telm.util.RowFactory;
import eu.telm.util.TextFieldFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Theme("colored")
@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
    public static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    public static final String VIEW_NAME = "";
    private PatientDao patientDao;
    private OperacjeDao operacjeDao;
    private BadaniaDao badaniaDao;
    private Button searchPatientButton;
    private Button editPatientButton;
    private UI ui;
    private SearchPatientSubWindow subWindow;
    private AddTestSubWindow subWindowAddTest;
    private Patient patient;
    private PatientController patientController;
    private TestController testController;
    private TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12;
    private Grid tabelaBadan, tabelaZabiegow;
    private DateField dateField;

    @Autowired
    public DefaultView(OperacjeDao operacjeDao, BadaniaDao badaniaDao, UI ui){
        this.patientDao =  (PatientDao)context.getBean("patientDao");
        this.operacjeDao = operacjeDao;
        this.badaniaDao = badaniaDao;
        this.ui = ui;
        patient = new Patient();
        subWindow = new SearchPatientSubWindow(patientDao);
        subWindow.setWidth("80%");
        subWindow.setHeight("80%");
        subWindow.center();
        patientController = new PatientController(subWindow,patient, this );
        subWindowAddTest = new AddTestSubWindow();
        subWindowAddTest.setWidth("80%");
        subWindowAddTest.setHeight("80%");
        subWindowAddTest.center();
        testController = new TestController(subWindowAddTest, patient,this);
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
                /*Patient patient = new Patient();
                patient.setImie("Janusz");
                patient.setNazwisko("Noga");
                patient.setPesel("61111111111");
                patient.setDataUr(java.sql.Date.valueOf("2016-09-09"));
                patient.setCzyUbezp(true);
                patient.setEmail("noga@wp.pl");
                patient.setKodPocztowy("05-152");
                patient.setMiasto("Czosnów");
                patient.setUlica("Warszawska");
                patient.setNrDomu("34");
                patient.setNrTel("512345678");
                patient.setPlec("K");
                patientDao = (PatientDao)context.getBean("patientDao");
                patientDao.save(patient);*/
                ui.addWindow(subWindow);

                subWindow.setClickController(patientController);


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
        //tabelaBadan.setColumns("operacja.nazwa","data", "wynik", "uwagi");

        tabelaBadan.addColumn("nazwa", String.class);
        tabelaBadan.addColumn("data", String.class);
        tabelaBadan.addColumn("wynik", String.class);
        tabelaBadan.addColumn("uwagi", String.class);
        tabelaBadan.addColumn("id", Long.class);
        Grid.Column column = tabelaBadan.getColumn("id");
        column.setHidden(true);

        /*final BeanItemContainer<Realizacje> ds = new BeanItemContainer<Realizacje>(Realizacje.class, realizacjeList);
        ds.addNestedContainerBean("operacja");
        tabelaBadan.setContainerDataSource(
                new BeanItemContainer(Realizacje.class, badaniaDao.findByPatient_Id(10L))
        );*/
        //VerticalLayout mainLayout = new VerticalLayout(form, tabelaBadan);
        form.addComponent(tabelaBadan);

        List<Component> buttonListBadanie = new ArrayList<>();
        Button usunBadanie = ButtonFactory.createButton("Usuń", FontAwesome.TRASH, buttonListBadanie, "deleteButton");
        Button edytujBadanie = ButtonFactory.createButton("Edytuj", FontAwesome.EDIT, buttonListBadanie, "editButton");
        Button dodajWynikBadanie = ButtonFactory.createButton("Dodaj wynik", FontAwesome.BAR_CHART, buttonListBadanie, "addResultButton");
        Button dodajBadanie = ButtonFactory.createButton("Dodaj", FontAwesome.STETHOSCOPE, buttonListBadanie, "addButton");
        ButtonFactory.setEnabledButtons(buttonListBadanie, 3, false);
        form.addComponent(RowFactory.createRowLayout(buttonListBadanie, "rowOfButtons"));

        dodajBadanie.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ui.addWindow(subWindowAddTest);
                subWindowAddTest.setClickController(testController);

            }
        });

        Panel zabiegPanel = new Panel("Zabiegi");
        zabiegPanel.setIcon(FontAwesome.HEARTBEAT);
        zabiegPanel.setSizeFull();
        form.addComponent(zabiegPanel);
        tabelaZabiegow = new Grid();
        tabelaZabiegow.setStyleName("tabelaBadan");
        tabelaZabiegow.addColumn("nazwa", String.class);
        tabelaZabiegow.addColumn("data", String.class);
        tabelaZabiegow.addColumn("wynik", String.class);
        tabelaZabiegow.addColumn("uwagi", String.class);
        tabelaZabiegow.addColumn("id", Long.class);
        tabelaZabiegow.setSizeFull();
        tabelaZabiegow.setHeight(300, Unit.PIXELS);
        Grid.Column column2 = tabelaZabiegow.getColumn("id");
        column2.setHidden(true);
        form.addComponent(tabelaZabiegow);

        List<Component> buttonListZabieg = new ArrayList<>();
        Button usunZabieg = ButtonFactory.createButton("Usuń", FontAwesome.TRASH, buttonListZabieg, "deleteButton");
        Button edytujZabieg = ButtonFactory.createButton("Edytuj", FontAwesome.EDIT, buttonListZabieg, "editButton");
        Button dodajWynikZabieg = ButtonFactory.createButton("Dodaj wynik", FontAwesome.BAR_CHART, buttonListZabieg, "addResultButton");
        Button dodajZabieg = ButtonFactory.createButton("Dodaj", FontAwesome.STETHOSCOPE, buttonListZabieg, "addButton");
        ButtonFactory.setEnabledButtons(buttonListZabieg, 3, false);
        form.addComponent(RowFactory.createRowLayout(buttonListZabieg, "rowOfButtons"));


        tabelaBadan.addSelectionListener(e -> {
            enableButtonsUnderTable(e, buttonListBadanie);
        });
        tabelaZabiegow.addSelectionListener(e -> {
            enableButtonsUnderTable(e, buttonListZabieg);
        });

        usunBadanie.addClickListener(e-> {
            deleteFromTable(tabelaBadan, buttonListBadanie);});
        usunZabieg.addClickListener(e->{
            deleteFromTable(tabelaZabiegow, buttonListZabieg);
        });

    }

    public void fillTables(Grid tabelaBadan, Grid tabelaZabiegow, BadaniaDao badaniaDao, long id){
        badaniaDao = (BadaniaDao) context.getBean("badaniaDao");
        List<Realizacje> realizacjeList = badaniaDao.findByPatient_Id(id);
        List<Object[]> realizacjeTable = new ArrayList<>();
        List<Object[]> zabiegiTable = new ArrayList<>();
        for(Realizacje realizacje : realizacjeList){
            if(realizacje.getOperacja().getTyp()== Operacja.typ.BADANIE)
                realizacjeTable.add(new Object[]{realizacje.getOperacja().getNazwa(),
                        realizacje.getData().toString(), realizacje.getWynik(), realizacje.getUwagi(),realizacje.getId()});
            if(realizacje.getOperacja().getTyp()== Operacja.typ.ZABIEG)
                zabiegiTable.add(new Object[]{realizacje.getOperacja().getNazwa(),
                        realizacje.getData().toString(), realizacje.getWynik(), realizacje.getUwagi(), realizacje.getId()});
        }
        //tabelaBadan.getContainerDataSource().removeAllItems();
        //tabelaZabiegow.getContainerDataSource().removeAllItems();
        //if(tabelaBadan.getContainerDataSource().size()>0 || tabelaZabiegow.getContainerDataSource().size()>0 )
        // tabelaBadan.addColumn("id", Long.class);
        tabelaBadan.getContainerDataSource().removeAllItems();
        for(Object[] objects : realizacjeTable)
            tabelaBadan.addRow(objects);
        //tabelaBadan.removeColumn("id");
        ///tabelaZabiegow.addColumn("id", Long.class);
        tabelaZabiegow.getContainerDataSource().removeAllItems();
        for(Object[] objects : zabiegiTable)
            tabelaZabiegow.addRow(objects);
        //tabelaZabiegow.removeColumn("id");
    }

    public void deleteFromTable(Grid tabela, List<Component> buttonsUnderTabel){
        Object selected = ((Grid.SingleSelectionModel)
                tabela.getSelectionModel()).getSelectedRow();
        if (selected != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date data = new Date();
            Date dataBadania = null;
            try {
                dataBadania = dateFormat.parse(tabela.getContainerDataSource().getItem(selected)
                        .getItemProperty("data").toString());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            // sprawdzenie czy badanie ma wynik i czy jest odpowiednia data
            if(tabela.getContainerDataSource().getItem(selected)
                    .getItemProperty("wynik").getValue()==null && dataBadania.compareTo(data)>-1) {
                DialogWindow dialogWindow = new DialogWindow("Potwierdź", FontAwesome.INFO, "Potwierdź swoją decyzję o usunięciu " +
                        "rekordu, proszę", "Usuń", FontAwesome.TRASH, "Wróć", FontAwesome.BACKWARD);
                ui.addWindow(dialogWindow);
                // klikniecie USUN
                dialogWindow.getButtons().get(0).addListener(listener->{
                    Notification.show("Usunięto rekord z badaniem: " +
                            tabela.getContainerDataSource().getItem(selected)
                                    .getItemProperty("nazwa"));
                    badaniaDao.delete(Long.parseLong(tabela.getContainerDataSource().getItem(selected)
                            .getItemProperty("id").toString()));
                    tabela.getContainerDataSource().removeItem(selected);
                    ButtonFactory.setEnabledButtons(buttonsUnderTabel, 3, false);
                    dialogWindow.close();
                });
                dialogWindow.getButtons().get(1).addListener(listener->{
                    dialogWindow.close();
                });

            }else{
                Notification.show("Nie usuwamy badań, które już wykonano\t");
            }
        }
    }

    public void enableButtonsUnderTable(SelectionEvent e, List<Component> buttonsUnderTable){
        if (e.getSelected().isEmpty()) {
            ButtonFactory.setEnabledButtons(buttonsUnderTable, 3, false);
        } else {
            System.out.println("zaznaczył");
            ButtonFactory.setEnabledButtons(buttonsUnderTable, 3, true);
        }
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

    public Grid getTabelaZabiegow() {
        return tabelaZabiegow;
    }

    public BadaniaDao getBadaniaDao(){return badaniaDao;}
    public OperacjeDao getOperacjeDao(){return operacjeDao;}

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}