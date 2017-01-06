package eu.telm.view;

import com.sun.jndi.toolkit.dir.SearchFilter;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import eu.telm.model.PatientDao;
import eu.telm.util.ButtonFactory;
import eu.telm.util.RowFactory;
import eu.telm.util.TextFieldFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magda on 05.01.2017.
 */
public class AddTestSubWindow extends Window {
    private Button addTestButton;
    private SearchFilter nameSearchPanel;
    private ComboBox nameComboBox;
    private DateField dateField;
    private TextField resultTextField, commentsTextField;

    public AddTestSubWindow() {
        super(" Dodaj badanie"); // Set window caption
        setIcon(FontAwesome.PLUS_SQUARE);
        center();
        setModal(true);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        List<Component> textFields = new ArrayList<>();
        nameComboBox = TextFieldFactory.createComboBox("Nazwa", true, textFields);
        dateField = TextFieldFactory.createDateField("Data", true, textFields);
        resultTextField = TextFieldFactory.createTextField("Wynik", true, textFields);
        resultTextField.setHeight("60");
        commentsTextField = TextFieldFactory.createTextField("Uwagi", true, textFields);
        commentsTextField.setHeight("60");
        addTestButton = ButtonFactory.createButton("Dodaj", FontAwesome.STETHOSCOPE, "addButton");
        setContent(mainLayout);

        mainLayout.addComponent(RowFactory.createVerticalRowLayout(textFields));
        mainLayout.addComponent(addTestButton);
        mainLayout.setComponentAlignment(addTestButton,Alignment.TOP_RIGHT);
    }


    public void setClickController(Button.ClickListener ac){
        this.addTestButton.addClickListener(ac);
    }

    public SearchFilter getNameSearchPanel() {
        return nameSearchPanel;
    }

    public void setNameSearchPanel(SearchFilter nameSearchPanel) {
        this.nameSearchPanel = nameSearchPanel;
    }

    public ComboBox getNameComboBox() {
        return nameComboBox;
    }

    public void setNameComboBox(ComboBox nameComboBox) {
        this.nameComboBox = nameComboBox;
    }

    public DateField getDateField() {
        return dateField;
    }

    public void setDateField(DateField dateField) {
        this.dateField = dateField;
    }

    public TextField getResultTextField() {
        return resultTextField;
    }

    public void setResultTextField(TextField resultTextField) {
        this.resultTextField = resultTextField;
    }

    public TextField getCommentsTextField() {
        return commentsTextField;
    }

    public void setCommentsTextField(TextField commentsTextField) {
        this.commentsTextField = commentsTextField;
    }

    public Button getAddTestButton() {
        return addTestButton;
    }

    public void setAddTestButton(Button addTestButton) {
        this.addTestButton = addTestButton;
    }
}
