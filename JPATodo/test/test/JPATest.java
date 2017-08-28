package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Todo;
import entities.User;

public class JPATest {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	User u;
	
	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("Todo");
		em = emf.createEntityManager();
		u = em.find(User.class, 1);
	}
	
	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

	@Test
	public void test_that_user_has_many_todos() {
		List<Todo> todos = u.getTodoList();
		assertNotNull(todos);
		assertEquals(todos.get(0).getTask(), "Juggle");
		assertEquals(todos.get(1).getTask(), "Dance");
	}
	
	@Test 
	public void test_that_todo_has_one_user() {
		Todo t = em.find(Todo.class, 1);
		assertNotNull(t.getUser());
		assertEquals(t.getUser().getEmail(), "ca@sd.com");
	}

}
