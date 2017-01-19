package eu.telm.view;


import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import eu.telm.controller.PatientController;
import eu.telm.model.Patient;
import eu.telm.model.PatientDao;
import eu.telm.util.ButtonFactory;
import org.springframework.util.StringUtils;

import java.awt.event.ActionListener;

/**
 * Created by kasia on 13.11.16.
 */
public class SearchPatientSubWindow extends Window {
    private final PatientDao repo;
    private final Grid grid;
    private final TextField filter;
    private final Button getSelectedPatientButton;
    private Button createNewPatientButton;
    private Patient patient;

    public SearchPatientSubWindow(PatientDao patientDao) {
        super("Wyszukiwanie pacjenta"); // Set window caption
        setIcon(FontAwesome.USERS);
        center();
        setModal(true);


        grid = new Grid();
        getSelectedPatientButton = ButtonFactory.createButton("Wybierz", FontAwesome.HAND_GRAB_O, "searchButton");
        getSelectedPatientButton.setVisible(false);
        filter = new TextField();
        this.repo = patientDao;
        HorizontalLayout actions = new HorizontalLayout(filter, getSelectedPatientButton);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid);
        setContent(mainLayout);
        createNewPatientButton = ButtonFactory.createButton("Dodaj Pacjenta", FontAwesome.USER_PLUS, "addButton");
        createNewPatientButton.setVisible(false);
        mainLayout.addComponent(createNewPatientButton);
        // Configure layouts and components
        actions.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);

        grid.setHeight(300, Unit.PIXELS);
        grid.setWidth(100,Unit.PERCENTAGE);
        grid.setColumns("id", "nazwisko", "imie", "pesel", "email");

        filter.setInputPrompt("Nazwisko");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.addTextChangeListener(e -> listCustomers(e.getText()));

        // Connect selected Customer to editor or hide if none is selected
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                getSelectedPatientButton.setVisible(false);
            } else {
                getSelectedPatientButton.setVisible(true);
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

    private void listCustomers(String text) {

        if (StringUtils.isEmpty(text)) {
            createNewPatientButton.setVisible(false);
            grid.setContainerDataSource(
                    new BeanItemContainer(Patient.class, repo.getAll()));
        } else {
            createNewPatientButton.setVisible(true);
            grid.setContainerDataSource(new BeanItemContainer(Patient.class,
                    repo.findByNazwiskoStartsWithIgnoreCase(text)));
        }
    }
}

