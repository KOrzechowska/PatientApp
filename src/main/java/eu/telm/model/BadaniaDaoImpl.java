package eu.telm.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String result = formatter.format(date);
        try {
            criteria = criteria.add(Restrictions.eq("data", formatter.parse(result)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Realizacje> realizacjes = criteria.list();
        System.out.println("wynik\t"+realizacjes.size());
        for(Realizacje realizacje : realizacjes)
            System.out.println("wynik\t"+realizacje.getData()+"\t"+realizacje.getPatient().getImie()+"\t"+realizacje.getOperacja().getNazwa()
            +"\t"+realizacje.getOperacja().getOpis()+"\t"+realizacje.getPatient().getNazwisko());
        session.close();
        return realizacjes;
    }

    @Override
    public Realizacje findById(Long id) {
        Session session = this.sessionFactory.openSession();
        Realizacje realizacje = (Realizacje) session.load(Realizacje.class, id);
        System.out.println("FindByID Realizacje\t"+realizacje.getData()+"\t"+realizacje.getPatient().getImie()+"\t"+realizacje.getOperacja().getNazwa()
                +"\t"+realizacje.getOperacja().getOpis()+"\t"+realizacje.getPatient().getNazwisko());
        session.close();
        return realizacje;
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

    @Override
    public void update(Realizacje realizacje) {
            Session session = this.sessionFactory.openSession();
            session.beginTransaction();
            Realizacje realizacje1 = (Realizacje) session.load(Realizacje.class, realizacje.getId());
            realizacje1.setWynik(realizacje.getWynik());
            realizacje1.setUwagi(realizacje.getUwagi());
            realizacje1.setData(realizacje.getData());
            realizacje1.setOperacja(realizacje.getOperacja());
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully updated " + realizacje.toString());

    }


    public Realizacje findByID(Long id) {
        Session session = this.sessionFactory.openSession();
        Realizacje realizacje = (Realizacje) session.load(Realizacje.class, id);
        session.close();
        return realizacje;
    }

    @Override
    public Long save(Realizacje realizacje) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(realizacje);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + realizacje.toString());
        return realizacje.getId();
    }

}
