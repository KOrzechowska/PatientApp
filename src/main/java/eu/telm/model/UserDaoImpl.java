package eu.telm.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria = criteria.add(Restrictions.eq("username", username));
		List<User> users = criteria.list();
		for(User user : users) {
			System.out.println("wynik\t" + user.getUsername());
			for(UserRole userRole : user.getUserRole())
				System.out.println("role\t" + userRole.getRole());
		}
		session.close();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}


}