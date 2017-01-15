package eu.telm.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        }
        System.out.println("Robić" + operacjas.size());
        session.close();
        return operacjas;
    }

    @Override
    public List<String> getOperacjeByTyp(Operacja.typ typ) {
            Session session = this.sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Operacja.class);
            criteria.add(Restrictions.eq("typ", typ));
            List<Operacja> operacjas = criteria.list();
            List<String> nazwyBadan = new ArrayList<>();
            System.out.println("Słownik\t"+operacjas.size());
            for(Operacja operacja : operacjas) {
                System.out.println("Wyszukanie\t" + operacja.getNazwa() + "\t" + operacja.getOpis());
                nazwyBadan.add(operacja.getNazwa());
            }
            session.close();
            return nazwyBadan;

    }

    @Override
    public Operacja getByName(String nazwa) {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Operacja.class);
        criteria.add(Restrictions.eq("nazwa", nazwa));
        Operacja operacja = (Operacja)criteria.uniqueResult();
            System.out.println("Wyszukanie\t" + operacja.getNazwa() + "\t" + operacja.getOpis());
        session.close();
        return operacja;
    }
}
