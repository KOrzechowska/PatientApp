package eu.telm.view;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import eu.telm.model.Patient;
import eu.telm.util.ButtonFactory;

import java.sql.Date;

/**
 * Created by Renfri on 06.01.2017.
 */
public class EditPatientSubWindow extends Window {
    //  private final TextField filter;
    private String a;
    private Button SaveButton;
    private Patient patient;
    private TextField imie = new TextField("Imie");
    private TextField nazwisko = new TextField("Nazwisko");
    private TextField pesel = new TextField("Pesel");
    private TextField email = new TextField("Email");
    private PopupDateField birthDate = new PopupDateField("Data urodzenia");
    private TextField plec = new TextField("Plec");
    private TextField ulica = new TextField("Ulica");
    private TextField miasto = new TextField("Miasto");
    private TextField numer = new TextField("Numer domu");
    private TextField kod = new TextField("Kod Pocztowy");
    private TextField tel = new TextField("Numer telefonu");
    public Button save;
    public Button cancel;


    public EditPatientSubWindow(Patient patient) {
        super("Edytuj Pacjenta"); // Set window caption
        setIcon(FontAwesome.USERS);
        center();
        setModal(true);


        save = ButtonFactory.createButton("Zapisz", FontAwesome.SAVE, "saveButton");
        cancel = ButtonFactory.createButton("Anuluj zmiany", FontAwesome.CLOSE, "cancel");
        HorizontalLayout rzad1 = new HorizontalLayout(imie, nazwisko, pesel, birthDate);
        rzad1.setSpacing(true);
        rzad1.setMargin(true);
        HorizontalLayout rzad2 = new HorizontalLayout(plec, ulica, miasto, numer);
        rzad2.setSpacing(true);
        rzad2.setMargin(true);
        HorizontalLayout rzad3 = new HorizontalLayout(kod, tel, email);
        rzad3.setSpacing(true);
        rzad3.setMargin(true);
        HorizontalLayout rzad4 = new HorizontalLayout(save, cancel);
        rzad4.setSpacing(true);
        rzad4.setSpacing(true);
        VerticalLayout main = new VerticalLayout(rzad1, rzad2, rzad3, rzad4);
        main.setSpacing(true);
        main.setMargin(true);


        // filter = new TextField();
        setContent(main);

        this.patient = patient;
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

    public void Anuluj() {
        close();
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

}



