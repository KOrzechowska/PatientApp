package eu.telm.controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import eu.telm.model.*;
import eu.telm.view.DefaultView;
import eu.telm.view.AddTestSubWindow;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Magda on 05.01.2017.
 */
public class TestController implements Button.ClickListener {

    private AddTestSubWindow subWindowAddTest;
    private DefaultView defaultView;
    private Patient model;
    private Boolean editting = false;
    private Boolean add=false;
    private Realizacje realizacje;
    private Grid tabela;

    private BadaniaDao badaniaDao;
    private OperacjeDao operacjeDao;

    public TestController(AddTestSubWindow subWindowAddTest, Patient model , DefaultView defaultView){
        this. subWindowAddTest = subWindowAddTest;
        this.defaultView = defaultView;
        this. model = model;
        badaniaDao = (BadaniaDao)DefaultView.context.getBean("badaniaDao");
        operacjeDao = (OperacjeDao)DefaultView.context.getBean("operacjeDao");
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
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
                realizacje.setUwagi(subWindowAddTest.getCommentsTextField().getValue());
                realizacje.setWynik(subWindowAddTest.getResultTextField().getValue());
                try {
                    realizacje.setData(formatter.parse(subWindowAddTest.getDateField().getValue().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                OperacjeDao operacjeDao = (OperacjeDao)DefaultView.context.getBean("operacjeDao");
                Operacja operacja = operacjeDao.getByName(subWindowAddTest.getNameComboBox().getValue().toString());
                realizacje.setOperacja(operacja);
                BadaniaDao badaniaDao = (BadaniaDao)DefaultView.context.getBean("badaniaDao");
                badaniaDao.update(realizacje);
                fillTable(tabela, badaniaDao, realizacje.getPatient().getId());
                subWindowAddTest.close();
                editting=false;

            }
        }

        if (add){
            if(source == subWindowAddTest.getAddTestButton()){
                Realizacje real = new Realizacje();
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
                try {
                    real.setData(formatter.parse(subWindowAddTest.getDateField().getValue().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                real.setPatient(model);
                OperacjeDao operacjeDao = (OperacjeDao)DefaultView.context.getBean("operacjeDao");
                Operacja operacja = operacjeDao.getByName(subWindowAddTest.getNameComboBox().getValue().toString());
                real.setOperacja(operacja);
                BadaniaDao badaniaDao = (BadaniaDao)DefaultView.context.getBean("badaniaDao");
                badaniaDao.save(real);
                fillTable(tabela, badaniaDao, real.getPatient().getId());
                subWindowAddTest.close();
                add=false;

            }
        }
    }
    public void fillComboBox(){
        OperacjeDao operacjeDao = (OperacjeDao) DefaultView.context.getBean("operacjeDao");
        List<String> operacjeByTyp = operacjeDao.getOperacjeByTyp(Operacja.typ.BADANIE);
        subWindowAddTest.getNameComboBox().addItems(operacjeByTyp);
    }

    public void fillWindow(Long id, String nazwa){
        this.realizacje = badaniaDao.findById(id);
        editting = true;
        subWindowAddTest.getResultTextField().setEnabled(true);
        subWindowAddTest.getCommentsTextField().setEnabled(true);
        if(realizacje.getWynik() !=null)
        subWindowAddTest.getResultTextField().setValue(realizacje.getWynik());
        if(realizacje.getUwagi() !=null)
        subWindowAddTest.getCommentsTextField().setValue(realizacje.getUwagi());
        subWindowAddTest.getDateField().setValue(realizacje.getData());
        subWindowAddTest.getNameComboBox().setValue(nazwa);
    }

    public void fillAddWindow(){
        subWindowAddTest.getResultTextField().setEnabled(false);
        subWindowAddTest.getCommentsTextField().setEnabled(false);
        subWindowAddTest.getNameComboBox().clear();
        add = true;
    }

    public void fillTable(Grid tabelaBadan, BadaniaDao badaniaDao, long id){
        DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd");
        List<Realizacje> realizacjeList = badaniaDao.findByPatient_Id(id);
        List<Object[]> realizacjeTable = new ArrayList<>();
        for(Realizacje realizacje : realizacjeList){
            if(realizacje.getOperacja().getTyp()== Operacja.typ.BADANIE)
                realizacjeTable.add(new Object[]{realizacje.getOperacja().getNazwa(),
                        writeFormat.format(realizacje.getData()), realizacje.getWynik(), realizacje.getUwagi(),realizacje.getId()});
        }
        tabelaBadan.getContainerDataSource().removeAllItems();
        for(Object[] objects : realizacjeTable)
            tabelaBadan.addRow(objects);

    }

    public Grid getTabela() {
        return tabela;
    }

    public void setTabela(Grid tabela) {
        this.tabela = tabela;
    }
}
