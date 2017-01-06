package eu.telm.util;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.ComboBox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by kasia on 29.12.16.
 */
public class TextFieldFactory {

    public static TextField createTextField(String label, boolean enabled, List<Component> components){
        TextField textField = new TextField(label);
        textField.setEnabled(enabled);
        textField.setStyleName("myTextField");
        components.add(textField);
        return textField;
    }

    public static DateField createDateField(String label, boolean enabled, List<Component> components){
        DateField dateField = new DateField(label);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        java.util.Date date = new java.util.Date();
        dateField.setValue(date);
        dateField.setStyleName("myTextField");
        dateField.setDateFormat("yyyy-MM-dd");
        dateField.setEnabled(enabled);
        components.add(dateField);
        return dateField;
    }

    public static CheckBox createCheckBox(String label, boolean enabled, List<Component> components){
        CheckBox isInsured = new CheckBox(label);
        isInsured.setEnabled(enabled);
        components.add(isInsured);
        return isInsured;
    }

    public static ComboBox createComboBox(String label, boolean enabled, List<Component> components){
        ComboBox comboBox = new ComboBox(label);
        comboBox.setEnabled(enabled);
        comboBox.setStyleName("myTextField");
        components.add(comboBox);
        return comboBox;
    }
}
