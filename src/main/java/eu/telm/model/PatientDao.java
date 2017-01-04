package eu.telm.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by kasia on 12.11.16.
 */
public interface PatientDao {
    List<Patient> findByNazwisko(String nazwisko);
    List<Patient> findByNazwiskoStartsWithIgnoreCase(String nazwisko);

    public List<Patient> getAll();

}
