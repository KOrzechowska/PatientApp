package eu.telm.controller;

import com.vaadin.event.FieldEvents;
import com.vaadin.ui.*;
import eu.telm.model.BadaniaDao;
import eu.telm.model.PatientDao;
import eu.telm.model.Patient;
import eu.telm.util.Validator;
import eu.telm.view.DefaultView;
import eu.telm.view.SearchPatientSubWindow;
import eu.telm.view.EditPatientSubWindow;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

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
        model.setPlec(subWindow.getSelectedPatient().getPlec().toString());
        model.setEmail(subWindow.getSelectedPatient().getEmail());
        model.setNr_tel(subWindow.getSelectedPatient().getNr_tel());
        model.setData_ur(subWindow.getSelectedPatient().getData_ur());
        model.setUlica(subWindow.getSelectedPatient().getUlica());
        model.setMiasto(subWindow.getSelectedPatient().getMiasto());
        model.setNr_domu(subWindow.getSelectedPatient().getNr_domu());
        model.setKod_pocztowy(subWindow.getSelectedPatient().getKod_pocztowy());
        model.setCzy_ubezp(subWindow.getSelectedPatient().isCzy_ubezp());
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
        model.setPlec(editWindow.getPlec().getValue().toString());
        model.setEmail(editWindow.getEmail().getValue());
        model.setNr_tel(editWindow.getTel().getValue());
        model.setData_ur(editWindow.getBirthDate().getValue());
        model.setUlica(editWindow.getUlica().getValue());
        model.setMiasto(editWindow.getMiasto().getValue());
        model.setNr_domu(editWindow.getNumer().getValue());
        model.setKod_pocztowy(editWindow.getKod().getValue());
        model.setCzy_ubezp(editWindow.getCzyUbezpieczony().getValue());
    }

    public void fillEditWindow(){
        System.out.println("KONTROLER\t"+model.getImie());
        editWindow.ustawImie(model.getImie());
        editWindow.ustawNazwisko(model.getNazwisko());
        editWindow.ustawPesel(model.getPesel());
        editWindow.ustawEmail(model.getEmail());
        editWindow.ustawPlec(model.getPlec());
        editWindow.ustawNumer(model.getNr_domu());
        editWindow.ustawMiasto(model.getMiasto());
        editWindow.ustawUlice(model.getUlica());
        editWindow.ustawKod(model.getKod_pocztowy());
        editWindow.ustawTelefon(model.getNr_tel());
        editWindow.ustawDateUrodzenia(model.getData_ur());
        editWindow.ustawCzyUbezpieczony(model.isCzy_ubezp());
    }

    public void updateNewPatient() {

        System.out.println(model.getId());
        model.setImie(addWindow.getImie().getValue());
        model.setNazwisko(addWindow.getNazwisko().getValue());
        model.setPesel(addWindow.getPesel().getValue());
        model.setPlec(addWindow.getPlec().getValue().toString());
        model.setEmail(addWindow.getEmail().getValue());
        model.setNr_tel(addWindow.getTel().getValue());
        model.setUlica(addWindow.getUlica().getValue());
        model.setMiasto(addWindow.getMiasto().getValue());
        model.setNr_domu(addWindow.getNumer().getValue());
        model.setKod_pocztowy(addWindow.getKod().getValue());
        model.setData_ur(addWindow.getBirthDate().getValue());
        model.setCzy_ubezp(addWindow.getCzyUbezpieczony().getValue());
    }

    public void editPatientCancel() {
        model.setImie(defaultView.getTextFieldImie().getValue());
        model.setNazwisko(defaultView.getTextFieldNazwisko().getValue());
        model.setPesel(defaultView.getTextFieldPesel().getValue());
        model.setPlec(defaultView.getTextFieldPlec().getValue().toString());
        model.setEmail(defaultView.getTextFieldEmail().getValue());
        model.setNr_tel(defaultView.getTextFieldNrTel().getValue());
        model.setUlica(defaultView.getTextFieldUlica().getValue());
        model.setMiasto(defaultView.getTextFieldMiasto().getValue());
        model.setNr_domu(defaultView.getTextFieldNrDomu().getValue());
        model.setKod_pocztowy(defaultView.getTextFieldKodPocztowy().getValue());
        model.setData_ur(defaultView.getDataField().getValue());
        model.setCzy_ubezp(defaultView.getCheckBoxCzyUbezpieczony().getValue());
    }

    public void btnCLick(Button.ClickEvent ce) {
        Object source = ce.getSource();
        if (source == addWindow.getCancel()) {
            editPatientCancel();
            addWindow.close();
        }
        if (source == addWindow.getSave()) {
            PatientDao patientDao = (PatientDao) DefaultView.context.getBean("patientDao");
            Validator v =new Validator(addWindow.getPesel().getValue());
            if(v.WalidujWymagane(addWindow.getImie(), addWindow.getNazwisko(), addWindow.getBirthDate(), addWindow.getPlec(), addWindow.getKod(), addWindow.getTel(), addWindow.getEmail()))
                if(v.isValid())
                    if(patientDao.check(addWindow.getPesel().getValue())) {
                        updateNewPatient();
                        defaultView.fillPatientPanel(model);
                        model.setId(patientDao.save(model));
                        addWindow.close();
                        BadaniaDao badaniaDao = (BadaniaDao) DefaultView.context.getBean("badaniaDao");
                        defaultView.getDodajBadanieButton().setEnabled(true);
                        defaultView.getDodajZabiegButton().setEnabled(true);
                        defaultView.fillTables(defaultView.getTabelaBadan(), defaultView.getTabelaZabiegow(), badaniaDao, model.getId());
                        System.out.println("ID\t" + model.getId());
                        defaultView.setPatient(model);
                    }else
                        Notification.show("Pacjent o numerze pesel: "+addWindow.getPesel()+ " już istnieje w bazie");
                else
                    Notification.show("Wpisany pesel jest niepoprawny");
            else
                Notification.show("Wypełnij wymagane dane");
        }
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        Object source = clickEvent.getSource();

        if(source == subWindow.getGetSelectedPatientButton()){
            updatePatient();
            subWindow.close();
            defaultView.fillPatientPanel(model);
            BadaniaDao badaniaDao = (BadaniaDao)DefaultView.context.getBean("badaniaDao");
            defaultView.getDodajBadanieButton().setEnabled(true);
            defaultView.getDodajZabiegButton().setEnabled(true);
            defaultView.fillTables(defaultView.getTabelaBadan(), defaultView.getTabelaZabiegow(), badaniaDao,model.getId());

        }

        if (source == editWindow.getCancel()){
            System.out.println("ZAMKNIJ");
            editWindow.close();
        }
        if (source == editWindow.getSave()) {
            Validator v = new Validator(editWindow.getPesel().getValue());
            if (v.WalidujWymagane(editWindow.getImie(), editWindow.getNazwisko(), editWindow.getBirthDate(), editWindow.getPlec(), editWindow.getKod(), editWindow.getTel(), editWindow.getEmail()))
                if (v.isValid()) {
                    PatientDao patientDao = (PatientDao) DefaultView.context.getBean("patientDao");
                    updateEdittedPatient();
                    defaultView.fillPatientPanel(model);
                    patientDao.update(model);
                    editWindow.close();
                }else
                    Notification.show("Wpisany PESEL jest niepoprawny");
            else
                Notification.show("Wypełnij wymagane dane");
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
        if(editWindow.getPesel().isValid() && blurEvent.getSource()==editWindow.getPesel()) {
            fillSexAndBirthDate(editWindow);
        }
         if(addWindow!=null)
        if(addWindow.getPesel().isValid() && blurEvent.getSource()==addWindow.getPesel()) {
            fillSexAndBirthDate(addWindow);
        }
    }

    private void fillSexAndBirthDate(EditPatientSubWindow window){
        Validator validator = new Validator(window.getPesel().getValue());
        if (validator.isValid()&&!window.getPesel().isEmpty()) {
            DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("rok z pesel\t" + validator.getBirthYear() + validator.getBirthDay() + validator.getBirthMonth());
            java.util.Date date = new GregorianCalendar(validator.getBirthYear(), validator.getBirthMonth()-1, validator.getBirthDay()).getTime();
            System.out.println("data z pesel\t" + date + "\tplec\t" + validator.getSex() + "\tdata\t" + writeFormat.format(date));
            window.getBirthDate().setValue(date);
            window.getPlec().setValue(validator.getSex());
            zablokuj(window);
        }
        if(!validator.isValid()||window.getPesel().isEmpty())
            odblokuj(window);
    }
    private void odblokuj(EditPatientSubWindow window){
        window.getPlec().setEnabled(true);
        window.getBirthDate().setEnabled(true);
    }
    public void zablokuj(EditPatientSubWindow window){
        window.getPlec().setEnabled(false);
        window.getBirthDate().setEnabled(false);
    }
}
