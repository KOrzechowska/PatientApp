package eu.telm.model;

import eu.telm.controller.AuditLogInterceptor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by kasia on 04.01.17.
 */
@Repository
public class PatientDaoImpl implements PatientDao {

    SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Patient> findByNazwisko(String nazwisko) {
        return null;
    }

    @Override
    public List<Patient> findByNazwiskoStartsWithIgnoreCase(String nazwisko) {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Patient.class);
        criteria.add(Restrictions.ilike("nazwisko",nazwisko, MatchMode.START));
        List<Patient> patients = criteria.list();
        for(Patient patient : patients){
            System.out.println("Patient:\t"+patient.getImie()+"\t"+patient.getNazwisko()+"\t"+patient.getPesel());
        }
        session.close();
        return patients;
    }

    @Override
    public List<Patient> findByCriteriums(String imie, String nazwisko, Date date) {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Patient.class);
        if(!nazwisko.isEmpty())
            criteria.add(Restrictions.ilike("nazwisko",nazwisko, MatchMode.START));
        if(!imie.isEmpty())
            criteria.add(Restrictions.ilike("imie",imie, MatchMode.START));
        if(date != null) {
            criteria.add(Restrictions.eq("dataUr", date));
        }
        List<Patient> patients = criteria.list();
        for(Patient patient : patients){
            System.out.println("Patient:\t"+patient.getImie()+"\t"+patient.getNazwisko()+"\t"+patient.getPesel());
        }
        session.close();
        return patients;
    }

    @Override
    public List<Patient> getAll() {
        Session session = this.sessionFactory.openSession();
        List<Patient> patients = session.createQuery("from Patient").list();
        for(Patient patient : patients) {
            System.out.println("Imie\t" + patient.getImie() + " nazwisko\t");
            if(patient.getRealizacjeList().size()>0)
                System.out.println("badanie\t"+patient.getRealizacjeList().get(0).getOperacja().getNazwa());
        }
        System.out.println("Robić" + patients.size());
        session.close();
        return patients;
    }

    @Override
    public Long save(Patient e) {
        System.out.println(e.getImie()+"\t"+ e.getData_ur());
        AuditLogInterceptor interceptor = new AuditLogInterceptor();
        Session session = this.sessionFactory.withOptions().interceptor(interceptor).openSession();
        interceptor.setSession(session);
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return e.getId();
    }

    @Override
    public void update(Patient p) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        Patient patient = (Patient) session.load(Patient.class, p.getId());
        patient.setImie(p.getImie());
        patient.setNazwisko(p.getNazwisko());
        patient.setData_ur(p.getData_ur());
        patient.setPesel(p.getPesel());
        patient.setPlec(p.getPlec());
        patient.setNr_domu(p.getNr_domu());
        patient.setUlica( p.getUlica() );
        patient.setMiasto(p.getMiasto());
        patient.setKod_pocztowy(p.getKod_pocztowy());
        patient.setNr_tel(p.getNr_tel());
        patient.setEmail(p.getEmail());
        patient.setCzy_ubezp(p.isCzy_ubezp());

        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully updated " + p.toString());

    }

    @Override
    public boolean check(String pesel){
        Boolean check=false;
        if(pesel.isEmpty()||pesel==null) {
            System.out.print("Obcokrajowiec");
            check=true;
        }
        else {
            Session session = this.sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Patient.class);
            criteria.add(Restrictions.ilike("pesel", pesel, MatchMode.START));
            List<Patient> patients = criteria.list();
            for (Patient patient : patients) {
                System.out.println("Patient:\t" + patient.getImie() + "\t" + patient.getNazwisko() + "\t" + patient.getPesel());
                System.out.print("Pacjent jest w bazie");
            }
            session.close();
            if (patients.isEmpty())
                check=true;
            else
                check=false;
        }
        return  check;
    }


}
