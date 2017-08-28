package data;

import java.util.Set;

import entities.User;

public interface UserDAO {

	public Set<User> index();
	
	public User show(int userId);
	
	public User create(String jsonUser);
	
	public User update(int id, String jsonUser);
	
	public boolean destroy(int userId);
	
}
