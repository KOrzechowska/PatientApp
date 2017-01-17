package eu.telm.controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import eu.telm.model.BadaniaDao;
import eu.telm.model.PatientDao;
import eu.telm.model.Realizacje;
import eu.telm.model.Patient;
import eu.telm.util.Validator;
import eu.telm.view.DefaultView;
import eu.telm.view.SearchPatientSubWindow;
import eu.telm.view.EditPatientSubWindow;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by kasia on 13.11.16.
 */
public class PatientController implements Button.ClickListener, FieldEvents.BlurListener{
    private SearchPatientSubWindow subWindow;
    private DefaultView defaultView;
    private Patient model;
    private EditPatientSubWindow editWindow;
    private EditPatientSubWindow addWindow;


    public PatientController(SearchPatientSubWindow subWindow, EditPatientSubWindow editWindow, Patient model , DefaultView defaultView){
        this. subWindow = subWindow;
        this.defaultView = defaultView;
        this. model = model;
        this.editWindow = editWindow;
    }

    public void updatePatient(){
        model.setId(subWindow.getSelectedPatient().getId());
        System.out.println(model.getId());
        model.setImie(subWindow.getSelectedPatient().getImie());
        model.setNazwisko(subWindow.getSelectedPatient().getNazwisko());
        model.setPesel(subWindow.getSelectedPatient().getPesel());
        model.setPlec(subWindow.getSelectedPatient().getPlec());
        model.setEmail(subWindow.getSelectedPatient().getEmail());
        model.setNrTel(subWindow.getSelectedPatient().getNrTel());
        model.setDataUr(subWindow.getSelectedPatient().getDataUr());
        model.setUlica(subWindow.getSelectedPatient().getUlica());
        model.setMiasto(subWindow.getSelectedPatient().getMiasto());
        model.setNrDomu(subWindow.getSelectedPatient().getNrDomu());
        model.setKodPocztowy(subWindow.getSelectedPatient().getKodPocztowy());
        model.setCzyUbezp(subWindow.getSelectedPatient().isCzyUbezp());
        //System.out.println(model.getImie());
    }

    /**
     * funkcja aktualizująca pacjenta wartościami
     * pól z okna edytowania
     */
    public void updateEdittedPatient(){

        System.out.println(model.getId());
        model.setImie(editWindow.getImie().getValue());
        model.setNazwisko(editWindow.getNazwisko().getValue());
        model.setPesel(editWindow.getPesel().getValue());
        model.setPlec(editWindow.getPlec().getValue());
        model.setEmail(editWindow.getEmail().getValue());
        model.setNrTel(editWindow.getTel().getValue());
        model.setDataUr(editWindow.getBirthDate().getValue());
        System.out.println("Data ur z updateP:\t"+model.getDataUr());
        model.setUlica(editWindow.getUlica().getValue());
        model.setMiasto(editWindow.getMiasto().getValue());
        model.setNrDomu(editWindow.getNumer().getValue());
        model.setKodPocztowy(editWindow.getKod().getValue());
        model.setCzyUbezp(editWindow.getCzyUbezpieczony().getValue());

    }

    public void fillEditWindow(){
        System.out.println("KONTROLER\t"+model.getImie());
        editWindow.ustawImie(model.getImie());
        editWindow.ustawNazwisko(model.getNazwisko());
        editWindow.ustawPesel(model.getPesel());
        editWindow.ustawEmail(model.getEmail());
        editWindow.ustawPlec(model.getPlec());
        editWindow.ustawNumer(model.getNrDomu());
        editWindow.ustawMiasto(model.getMiasto());
        editWindow.ustawUlice(model.getUlica());
        editWindow.ustawKod(model.getKodPocztowy());
        editWindow.ustawTelefon(model.getNrTel());
        editWindow.ustawDateUrodzenia(model.getDataUr());
        editWindow.ustawCzyUbezpieczony(model.isCzyUbezp());
    }

    public void updateNewPatient() {

        System.out.println(model.getId());
        model.setImie(addWindow.getImie().getValue());
        model.setNazwisko(addWindow.getNazwisko().getValue());
        model.setPesel(addWindow.getPesel().getValue());
        model.setPlec(addWindow.getPlec().getValue());
        model.setEmail(addWindow.getEmail().getValue());
        model.setNrTel(addWindow.getTel().getValue());
        model.setUlica(addWindow.getUlica().getValue());
        model.setMiasto(addWindow.getMiasto().getValue());
        model.setNrDomu(addWindow.getNumer().getValue());
        model.setKodPocztowy(addWindow.getKod().getValue());
        model.setDataUr(addWindow.getBirthDate().getValue());
        model.setCzyUbezp(addWindow.getCzyUbezpieczony().getValue());
    }

    public void btnCLick(Button.ClickEvent ce) {
        Object source = ce.getSource();
        if (source == addWindow.getCancel()) {
            addWindow.close();
        }
        if (source == addWindow.getSave()) {
            updateNewPatient();
            defaultView.fillPatientPanel(model);
            if (addWindow.Waliduj()==0){
            PatientDao patientDao = (PatientDao) DefaultView.context.getBean("patientDao");
            model.setId(patientDao.save(model));
            addWindow.close();
            BadaniaDao badaniaDao = (BadaniaDao)DefaultView.context.getBean("badaniaDao");
            defaultView.getDodajBadanieButton().setEnabled(true);
            defaultView.getDodajZabiegButton().setEnabled(true);
            defaultView.fillTables(defaultView.getTabelaBadan(), defaultView.getTabelaZabiegow(), badaniaDao,model.getId());
            System.out.println("ID\t"+model.getId());
            defaultView.setPatient(model);}

            if (addWindow.Waliduj()==1){
                Notification.show("Wpisz brakujące dane");
            }

            if (addWindow.Waliduj()==2){
                Notification.show("Popraw Pesel");
            }
        }
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        Object source = clickEvent.getSource();

        if(source == subWindow.getGetSelectedPatientButton()){
            updatePatient();
            subWindow.close();
            defaultView.getTextFieldImie().setValue(model.getImie());
            defaultView.getTextFieldNazwisko().setValue(model.getNazwisko());
            defaultView.getTextFieldPesel().setValue(model.getPesel());
            defaultView.getTextFieldPlec().setValue(model.getPlec());
            defaultView.getTextFieldUlica().setValue(model.getUlica());
            defaultView.getTextFieldMiasto().setValue(model.getMiasto());
            defaultView.getDateField().setValue(model.getDataUr());
            defaultView.getTextFieldKodPocztowy().setValue(model.getKodPocztowy());
            defaultView.getCheckBoxCzyUbezpieczony().setValue(model.isCzyUbezp());
            //System.out.println(model.getId());
            BadaniaDao badaniaDao = (BadaniaDao)DefaultView.context.getBean("badaniaDao");
            defaultView.getDodajBadanieButton().setEnabled(true);
            defaultView.getDodajZabiegButton().setEnabled(true);
            defaultView.fillTables(defaultView.getTabelaBadan(), defaultView.getTabelaZabiegow(), badaniaDao,model.getId());

        }

        if (source == editWindow.getCancel()){
            System.out.println("ZAMKNIJ");
            editWindow.close();
        }
        if (source == editWindow.getSave()){
            updateEdittedPatient();
            defaultView.fillPatientPanel(model);
            if (editWindow.Waliduj()==0){
            PatientDao patientDao = (PatientDao)DefaultView.context.getBean("patientDao");
            patientDao.update(model);
            editWindow.close();
            }
            if(editWindow.Waliduj()==1){
                Notification.show("Wpisz brakujace dane");
            }
            if(editWindow.Waliduj()==2){
                Notification.show("Popraw Pesel");
            }


        }
        if (source == subWindow.getCreateNewPatientButton()) {
            UI ui = defaultView.getUI();
            model = new Patient();
            addWindow = new EditPatientSubWindow(model);
            addWindow.setCaption("Dodaj nowego pacjenta");
            addWindow.setFocusController(this);
            ui.addWindow(addWindow);
            subWindow.close();
            addWindow.cancel.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    btnCLick(clickEvent);
                }
            });
            addWindow.save.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    btnCLick(clickEvent);
                }
            });


        }
    }

    public void setModel(Patient model) {
        this.model = model;
    }


    @Override
    public void blur(FieldEvents.BlurEvent blurEvent) {
        if(editWindow!=null)
        if(blurEvent.getSource()==editWindow.getPesel()) {
            fillSexAndBirthDate(editWindow);
        }
        if(addWindow!=null)
        if(blurEvent.getSource()==addWindow.getPesel()) {
            fillSexAndBirthDate(addWindow);
        }
    }

    private void fillSexAndBirthDate(EditPatientSubWindow window){
        Validator validator = new Validator(window.getPesel().getValue());
        if (validator.isValid()) {
            DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("rok z pesel\t" + validator.getBirthYear() + validator.getBirthDay() + validator.getBirthMonth());
            java.util.Date date = new GregorianCalendar(validator.getBirthYear(), validator.getBirthMonth()-1, validator.getBirthDay()).getTime();
            System.out.println("data z pesel\t" + date + "\tplec\t" + validator.getSex() + "\tdata\t" + writeFormat.format(date));
            window.getBirthDate().setValue(date);
            window.getPlec().setValue(validator.getSex());
        }
    }
}
