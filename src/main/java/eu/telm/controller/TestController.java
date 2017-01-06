package eu.telm.controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import eu.telm.model.Patient;
import eu.telm.model.Realizacje;
import eu.telm.view.DefaultView;
import eu.telm.view.AddTestSubWindow;

import java.util.List;

/**
 * Created by Magda on 05.01.2017.
 */
public class TestController implements Button.ClickListener {

    private AddTestSubWindow subWindowAddTest;
    private DefaultView defaultView;
    private Patient model;

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
    }
}
