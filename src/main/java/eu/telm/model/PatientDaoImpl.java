package eu.telm.model;

import eu.telm.dataBase.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kasia on 04.01.17.
 */
@Repository
public class PatientDaoImpl implements PatientDao {

    SessionFactory sessionFactory;
    public void setSessionFactory(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }


    @Override
    public List<Patient> findByNazwisko(String nazwisko) {
        return null;
    }

    @Override
    public List<Patient> findByNazwiskoStartsWithIgnoreCase(String nazwisko) {
        return null;
    }

    @Override
    public List<Patient> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Patient> patients = session.createQuery("from Patient").list();
        System.out.println("ROIBC");
        session.close();
        return patients;
    }
}
