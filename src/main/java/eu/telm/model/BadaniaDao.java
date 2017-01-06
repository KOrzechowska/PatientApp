package eu.telm.model;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;

/**
 * Created by kasia on 13.11.16.
 */
public interface BadaniaDao{

    List<Realizacje> findByPatient_Id(Long id);
    List<Realizacje> findByDate(Date date);
    public void delete(Long id);

}
