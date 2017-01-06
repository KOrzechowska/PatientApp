package eu.telm.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by kasia on 05.01.17.
 */
@Repository
public class BadaniaDaoImpl implements BadaniaDao {

    SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Realizacje> findByPatient_Id(Long id) {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Realizacje.class);
        criteria = criteria.createCriteria("patient").add(Restrictions.eq("id", id));
        List<Realizacje> realizacjes = criteria.list();
        System.out.println("wynik\t"+realizacjes.size());
        for(Realizacje realizacje : realizacjes)
            System.out.println("wynik\t"+realizacje.getOperacja().toString()+"\n"+realizacje.getOperacja().getNazwa());
        session.close();
        return realizacjes;
    }

    @Override
    public List<Realizacje> findByDate(java.util.Date date) {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Realizacje.class);
        criteria = criteria.add(Restrictions.eq("data", date));
        List<Realizacje> realizacjes = criteria.list();
        System.out.println("wynik\t"+realizacjes.size());
        for(Realizacje realizacje : realizacjes)
            System.out.println("wynik\t"+realizacje.getData()+"\t"+realizacje.getPatient().getImie()+"\t"+realizacje.getOperacja().getNazwa()
            +"\t"+realizacje.getOperacja().getOpis()+"\t"+realizacje.getPatient().getNazwisko());
        session.close();
        return realizacjes;
    }


    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Realizacje realizacje = findByID(id);
        session.delete(realizacje);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully deleted " + realizacje.toString());
    }

    public Realizacje findByID(Long id) {
        Session session = this.sessionFactory.openSession();
        Realizacje realizacje = (Realizacje) session.load(Realizacje.class, id);
        session.close();
        return realizacje;
    }

}
