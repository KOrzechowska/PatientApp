package eu.telm.model;

public interface UserDao {

	User findByUserName(String username);

}