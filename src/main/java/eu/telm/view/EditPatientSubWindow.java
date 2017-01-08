package eu.telm.view;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import eu.telm.model.Patient;
import eu.telm.util.ButtonFactory;
import eu.telm.util.RowFactory;
import eu.telm.util.TextFieldFactory;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renfri on 06.01.2017.
 */
public class EditPatientSubWindow extends Window {

    private Patient patient;
    private TextField imie;
    private TextField nazwisko;
    private TextField pesel;
    private TextField email;
    private DateField birthDate;
    private TextField plec;
    private TextField ulica;
    private TextField miasto;
    private TextField numer;
    private TextField kod;
    private TextField tel;
    public Button save;
    public Button cancel;


    public EditPatientSubWindow(Patient patient) {
        super("Edytuj Pacjenta"); // Set window caption
        setIcon(FontAwesome.USERS);
        center();
        setModal(true);

        VerticalLayout patientData = new VerticalLayout();
        List<HorizontalLayout> horizontalLayouts = new ArrayList<>();
        List<Component> textFields = new ArrayList<>();
        imie = TextFieldFactory.createTextField("Imię", true, textFields);
        nazwisko = TextFieldFactory.createTextField("Nazwisko", true, textFields);
        pesel = TextFieldFactory.createTextField("Pesel", true, textFields);
        birthDate = TextFieldFactory.createDateField("Data urodzenia", true, textFields);
        plec = TextFieldFactory.createTextField("Płeć", true, textFields);
        ulica = TextFieldFactory.createTextField("Ulica", true, textFields);
        miasto = TextFieldFactory.createTextField("Miasto", true, textFields);
        numer = TextFieldFactory.createTextField("Numer domu", true, textFields);
        kod = TextFieldFactory.createTextField("Kod pocztowy", true, textFields);
        tel = TextFieldFactory.createTextField("Numer telefonu", true, textFields);
        email = TextFieldFactory.createTextField("Adres e-mail", true, textFields);
        CheckBox isInsured = TextFieldFactory.createCheckBox("Czy ubezpieczony", true, textFields);
        List<Component> buttonsPatientPanel = new ArrayList<>();
        save = ButtonFactory.createButton("Zapisz", FontAwesome.SAVE, buttonsPatientPanel, "editButton");
        cancel = ButtonFactory.createButton("Anuluj", FontAwesome.CLOSE, buttonsPatientPanel, "searchButton");
        horizontalLayouts = RowFactory.createRowsLayout(textFields);
        horizontalLayouts.forEach(patientData::addComponent);
        patientData.addComponent(RowFactory.createRowLayout(buttonsPatientPanel, "rowOfButtonsPatientPanel"));
        setContent(patientData);

        this.patient = patient;
    }
    public void setCController(Button.ClickListener a){
        this.cancel.addClickListener(a);
    }

    public void setClickController(Button.ClickListener ac){
        this.save.addClickListener(ac);
    }

    public void ustawImie(String a) {
        imie.setValue(a);
    }

    public void ustawNazwisko(String a) {
        nazwisko.setValue(a);
    }

    public void ustawPesel(String a) {
        pesel.setValue(a);
    }

    public void ustawEmail(String a) {
        email.setValue(a);
    }

    public void ustawPlec(String a) {
        plec.setValue(a);
    }

    public void ustawUlice(String a) {
        ulica.setValue(a);
    }

    public void ustawNumer(String a) {
        numer.setValue(a);
    }

    public void ustawMiasto(String a) {
        miasto.setValue(a);
    }

    public void ustawKod(String a) {
        kod.setValue(a);
    }

    public void ustawTelefon(String a) {
        tel.setValue(a);
    }

    public void ustawDateUrodzenia(Date d) {
        birthDate.setValue(d);
    }


    public void Save() {

        patient.setImie(imie.getValue());
        patient.setNazwisko(nazwisko.getValue());
        patient.setPesel(pesel.getValue());
        patient.setEmail(email.getValue());
        patient.setPlec(plec.getValue());
        patient.setUlica(ulica.getValue());
        patient.setNrDomu(numer.getValue());
        patient.setMiasto(miasto.getValue());
        patient.setKodPocztowy(kod.getValue());
        patient.setNrTel(tel.getValue());
        //    patient.setDataUr(birthDate.getValue());

    }

    public Button getSave() {
        return save;
    }

    public void setSave(Button save) {
        this.save = save;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public TextField getImie() {
        return imie;
    }

    public TextField getNazwisko() {
        return nazwisko;
    }

    public TextField getPesel() {
        return pesel;
    }

    public TextField getEmail() {
        return email;
    }

    public DateField getBirthDate() {
        return birthDate;
    }

    public TextField getUlica() {
        return ulica;
    }

    public TextField getPlec() {
        return plec;
    }

    public TextField getMiasto() {
        return miasto;
    }

    public TextField getNumer() {
        return numer;
    }

    public TextField getKod() {
        return kod;
    }

    public TextField getTel() {
        return tel;
    }
}



