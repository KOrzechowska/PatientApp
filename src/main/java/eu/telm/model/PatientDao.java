package eu.telm.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by kasia on 12.11.16.
 */
public interface PatientDao {
    List<Patient> findByNazwisko(String nazwisko);
    List<Patient> findByNazwiskoStartsWithIgnoreCase(String nazwisko);

    List<Patient> findByCriteriums(String imie, String nazwisko, Date date);

    public List<Patient> getAll();
    public Long save(Patient p);
    public void update(Patient p);
    public boolean check(String pesel);
}
