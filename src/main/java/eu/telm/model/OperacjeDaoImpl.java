package eu.telm.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kasia on 05.01.17.
 */
@Repository
public class OperacjeDaoImpl implements OperacjeDao {

    SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Operacja> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Operacja> operacjas = session.createQuery("from Operacja").list();
        for(Operacja operacja : operacjas) {
            System.out.println("Imie\t" + operacja.getNazwa() + " nazwisko\t"+operacja.getTyp()+"\t"+operacja.getOpis());
            //if(operacja.getRealizacjeList().size()>0)
                //System.out.println("badanie\t"+operacja.getRealizacjeList().get(0).getPatient().getImie());
        }
        System.out.println("Robić" + operacjas.size());
        session.close();
        return operacjas;
    }
}
