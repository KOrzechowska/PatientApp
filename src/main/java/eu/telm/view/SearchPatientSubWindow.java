package eu.telm.view;


import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import eu.telm.controller.PatientController;
import eu.telm.model.Patient;
import eu.telm.model.PatientDao;
import eu.telm.util.ButtonFactory;
import eu.telm.util.RowFactory;
import eu.telm.util.TextFieldFactory;
import org.springframework.util.StringUtils;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kasia on 13.11.16.
 */
public class SearchPatientSubWindow extends Window {
    private final PatientDao repo;
    private final Grid grid;
    private final Button getSelectedPatientButton;
    private Button createNewPatientButton;
    private Patient patient;
    private TextField tf1, tf2;
    private DateField dateField;
    private Button search, clear;

    public SearchPatientSubWindow(PatientDao patientDao) {
        super("Wyszukiwanie pacjenta"); // Set window caption
        setIcon(FontAwesome.USERS);
        center();
        setModal(true);
        grid = new Grid();
        this.repo = patientDao;

        VerticalLayout mainLayout = new VerticalLayout();
        setContent(mainLayout);
        // panel do wyszukania
        Panel searchCriteria = new Panel("Kryteria wyszukania");
        searchCriteria.setIcon(FontAwesome.SEARCH);
        searchCriteria.setSizeFull();
        mainLayout.addComponent(searchCriteria);
        VerticalLayout searchInputs = new VerticalLayout();
        List<HorizontalLayout> horizontalLayouts = new ArrayList<>();
        List<Component> textFields = new ArrayList<>();
        tf1 = TextFieldFactory.createTextField("Imię", true, textFields);
        tf2 = TextFieldFactory.createTextField("Nazwisko", true, textFields);
        dateField = TextFieldFactory.createDateField("Data urodzenia", true, textFields);
        dateField.clear();
        dateField.setRangeEnd(new Date()); // tylko daty ur do dziś
        horizontalLayouts = RowFactory.createRowsLayout(textFields);
        horizontalLayouts.forEach(searchInputs::addComponent);
        List<Component> buttonList = new ArrayList<>();
        search = ButtonFactory.createButton("Wyszukaj", FontAwesome.SEARCH, buttonList, "searchButton");
        clear = ButtonFactory.createButton("Wyczyść", FontAwesome.RUBLE, buttonList, "deleteButton");
        searchInputs.addComponent(RowFactory.createRowLayout(buttonList, "rowOfButtonsPatientPanel"));
        searchCriteria.setContent(searchInputs);
        mainLayout.addComponent(grid);
        List<Component> buttonsListResults = new ArrayList<>();
        getSelectedPatientButton = ButtonFactory.createButton("Wybierz", FontAwesome.HAND_GRAB_O,buttonsListResults, "addResultButton");
        getSelectedPatientButton.setEnabled(false);
        createNewPatientButton = ButtonFactory.createButton("Dodaj Pacjenta", FontAwesome.USER_PLUS,buttonsListResults, "addButton");
        createNewPatientButton.setEnabled(false);
        mainLayout.addComponent(RowFactory.createRowLayout(buttonsListResults, "rowOfButtonsPatientPanel"));

        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        grid.setHeight(300, Unit.PIXELS);
        grid.setWidth(100,Unit.PERCENTAGE);
        grid.setColumns("id", "nazwisko", "imie", "pesel", "email");

        clear.addClickListener(e-> clearSearchInputs());
        search.addClickListener(e -> listCustomers());
        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                getSelectedPatientButton.setEnabled(false);
            } else {
                getSelectedPatientButton.setEnabled(true);
            }
        });

    }

    public void setClickController(Button.ClickListener ac){
        this.getSelectedPatientButton.addClickListener(ac);
    }

    public void setCController(Button.ClickListener ac){this.createNewPatientButton.addClickListener(ac);}
    public Button getCreateNewPatientButton(){return createNewPatientButton;}
    public Button getGetSelectedPatientButton(){
        return this.getSelectedPatientButton;
    }
    public Patient getSelectedPatient(){
        return (Patient) grid.getSelectedRow();
    }

    private void listCustomers(){
        createNewPatientButton.setEnabled(true);
        if(tf1.isEmpty() && tf2.isEmpty() && dateField.isEmpty()){ // jeśli nie wypełniono kryterium
            grid.setContainerDataSource(
                    new BeanItemContainer(Patient.class, repo.getAll()));
        }else{
            System.out.println("WYSZUKAJ\t"+tf1.getValue()+ tf2.getValue()+ dateField.getValue());
            grid.setContainerDataSource(
                    new BeanItemContainer(Patient.class, repo.findByCriteriums(tf1.getValue(), tf2.getValue(), dateField.getValue())));
        }
    }


    private void clearSearchInputs(){
        tf1.clear();
        tf2.clear();
        dateField.clear();
        grid.getContainerDataSource().removeAllItems();
        createNewPatientButton.setEnabled(false);
    }
}

