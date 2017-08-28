package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.User;

@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Set<User> index() {
		String query = "SELECT u FROM User u";
		List<User> allUsersList = em.createQuery(query, User.class)
							   .getResultList();
		
		Set<User> allUsers = new HashSet<>(allUsersList);
		return allUsers;
	}

	@Override
	public User show(int userId) {
		String query = "SELECT u FROM User u WHERE u.id = :id";
		User u = em.createQuery(query, User.class)
				   .setParameter("id", userId)
				   .getResultList()
				   .get(0);
		return u;
	}

	@Override
	public User create(String jsonUser) {
		ObjectMapper om = new ObjectMapper();
		User u;
		try {
			u = om.readValue(jsonUser, User.class);
			em.persist(u);
			em.flush();
		} catch (Exception e) {
			u = null;
		}
		return u;
	}

	@Override
	public User update(int id, String jsonUser) {
		ObjectMapper om = new ObjectMapper();
		User u;
		try {
			u = om.readValue(jsonUser, User.class);
			User managed = em.find(User.class, id);
			managed.setEmail(u.getEmail());
			managed.setPassword(u.getPassword());
			managed.setTodoList(u.getTodoList());
			return managed;
		} catch (Exception e) {
			u = null;
			return u;
		}
	}

	@Override
	public boolean destroy(int userId) {
		try {
			User u = em.find(User.class, userId);
			em.remove(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
