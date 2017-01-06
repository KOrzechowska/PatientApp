package eu.telm.controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import eu.telm.model.*;
import eu.telm.view.DefaultView;
import eu.telm.view.AddTestSubWindow;

import java.sql.Date;
import java.util.List;

/**
 * Created by Magda on 05.01.2017.
 */
public class TestController implements Button.ClickListener {

    private AddTestSubWindow subWindowAddTest;
    private DefaultView defaultView;
    private Patient model;
    private Boolean editting = false;
    private Realizacje realizacje;

    public TestController(AddTestSubWindow subWindowAddTest, Patient model , DefaultView defaultView){
        this. subWindowAddTest = subWindowAddTest;
        this.defaultView = defaultView;
        this. model = model;
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        Object source = clickEvent.getSource();

       /* if(source == subWindow.getGetSelectedPatientButton()){
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
            //System.out.println(model.getId());
            List<Realizacje> realizacjeList = defaultView.getBadaniaDao().findByPatient_Id( model.getId());
            defaultView.getTabelaBadan().setContainerDataSource(
                    new BeanItemContainer(Realizacje.class, defaultView.getBadaniaDao().findByPatient_Id( model.getId())));



        }*/
        if (editting){
            if(source == subWindowAddTest.getAddTestButton()){
                realizacje.setUwagi(subWindowAddTest.getCommentsTextField().getValue());
                realizacje.setWynik(subWindowAddTest.getResultTextField().getValue());
                realizacje.setData(java.sql.Date.valueOf(subWindowAddTest.getDateField().getValue().toString()));
                OperacjeDao operacjeDao = (OperacjeDao)DefaultView.context.getBean("operacjeDao");
                Operacja operacja = operacjeDao.getByName(subWindowAddTest.getNameComboBox().getValue().toString());
                realizacje.setOperacja(operacja);
                BadaniaDao badaniaDao = (BadaniaDao)DefaultView.context.getBean("badaniaDao");
                badaniaDao.update(realizacje);
                subWindowAddTest.close();

            }
        }
    }
    public void fillComboBox(){
        OperacjeDao operacjeDao = (OperacjeDao) DefaultView.context.getBean("operacjeDao");
        List<String> operacjeByTyp = operacjeDao.getOperacjeByTyp(Operacja.typ.BADANIE);
        subWindowAddTest.getNameComboBox().addItems(operacjeByTyp);
    }

    public void fillWindow(Realizacje realizacje, String nazwa){
        this.realizacje = realizacje;
        editting = true;
        if(realizacje.getWynik() !=null)
        subWindowAddTest.getResultTextField().setValue(realizacje.getWynik());
        if(realizacje.getUwagi() !=null)
        subWindowAddTest.getCommentsTextField().setValue(realizacje.getUwagi());
        subWindowAddTest.getDateField().setValue(realizacje.getData());
        subWindowAddTest.getNameComboBox().setValue(nazwa);
    }
}
