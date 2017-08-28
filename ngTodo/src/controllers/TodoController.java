package controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.TodoDAO;
import entities.Todo;

@RestController
public class TodoController {

	@Autowired
	private TodoDAO todoDao;
	
	@RequestMapping(path = "users/{id}/todos", method = RequestMethod.GET)
	public Set<Todo> index(@PathVariable int id) {
		return todoDao.index(id);
	}
	
	@RequestMapping(path = "users/{uid}/todos/{tid}", method = RequestMethod.GET)
	public Todo show(@PathVariable int uid, @PathVariable int tid) {
		return todoDao.show(uid, tid);
	}
	
	@RequestMapping(path = "users/{uid}/todos", method = RequestMethod.POST)
	public Todo create(@PathVariable int uid, @RequestBody String todoJson) {
		return todoDao.create(uid, todoJson);
	}
	
	@RequestMapping(path = "users/{uid}/todos/{tid}", method = RequestMethod.PUT)
	public Todo update(@PathVariable int uid, @PathVariable int tid, @RequestBody String todoJson) {
		return todoDao.update(uid, tid, todoJson);
	}
	
	@RequestMapping(path = "users/{uid}/todos/{tid}", method = RequestMethod.DELETE)
	public boolean destroy(@PathVariable int uid, @PathVariable int tid) {
		return todoDao.destroy(uid, tid);
	}
	
}
