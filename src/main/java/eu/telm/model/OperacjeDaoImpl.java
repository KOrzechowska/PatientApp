package eu.telm.model;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by kasia on 05.01.17.
 */
@Repository
public class OperacjeDaoImpl implements OperacjeDao {

    SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


}
