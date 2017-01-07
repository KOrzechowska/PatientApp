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

        if (editting){
            if(source == subWindowAddTest.getAddTestButton()){
                DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                realizacje.setUwagi(subWindowAddTest.getCommentsTextField().getValue());
                realizacje.setWynik(subWindowAddTest.getResultTextField().getValue());
                realizacje.setData(subWindowAddTest.getDateField().getValue());
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
                DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                real.setData(subWindowAddTest.getDateField().getValue());
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
        subWindowAddTest.getResultTextField().clear();
        subWindowAddTest.getCommentsTextField().clear();
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
