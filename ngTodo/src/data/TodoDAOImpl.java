package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Todo;
import entities.User;

@Transactional
public class TodoDAOImpl implements TodoDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<Todo> index(int uid) {
		String query = "SELECT t FROM Todo t WHERE t.user.id = :id";
		List<Todo> todoList = em.createQuery(query, Todo.class).setParameter("id", uid).getResultList();
		Set<Todo> todos = new HashSet<Todo>(todoList);
		return todos;
	}

	@Override
	public Todo show(int uid, int tid) {
		String query = "SELECT t FROM Todo t WHERE t.user.id = :uid AND t.id = :tid";
		Todo t = em.createQuery(query, Todo.class).setParameter("uid", uid).setParameter("tid", tid).getResultList()
				.get(0);
		return t;
	}

	@Override
	public Todo create(int uid, String todoJson) {
		User u = em.find(User.class, uid);
		Todo t = null;
		ObjectMapper om = new ObjectMapper();
		try {
			t = om.readValue(todoJson, Todo.class);
			t.setUser(u);
			em.persist(t);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public Todo update(int uid, int tid, String todoJson) {
		User u = em.find(User.class, uid);
		Todo managed = em.find(Todo.class, tid);
		Todo t = null;
		try {
			ObjectMapper om = new ObjectMapper();
			t = om.readValue(todoJson, Todo.class);
			managed.setCompleted(t.isCompleted());
			managed.setCompleteDate(t.getCompleteDate());
			managed.setCreatedAt(t.getCreatedAt());
			managed.setDescription(t.getDescription());
			managed.setDueDate(t.getDueDate());
			managed.setTask(t.getTask());
			managed.setUpdatedAt(t.getUpdatedAt());
			managed.setUser(u);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return managed;
	}

	@Override
	public Boolean destroy(int uid, int tid) {
		User u = em.find(User.class, uid);
		Todo t = em.find(Todo.class, tid);
		try {
			em.remove(t);
			List<Todo> userTodos = u.getTodoList();
			for (Todo todo : userTodos) {
				if (todo.getId() == tid) {
					userTodos.remove(todo);
					break;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
