package controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.UserDAO;
import entities.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(path = "users", method = RequestMethod.GET)
	public Set<User> index() {
		return userDao.index();
	}
	
	@RequestMapping(path = "users/{id}", method = RequestMethod.GET)
	public User show(@PathVariable int id) { 
		return userDao.show(id);
	}
	
	@RequestMapping(path = "users", method = RequestMethod.POST) 
	public User create(@RequestBody String json) {
		return userDao.create(json);
	}
	
	@RequestMapping(path = "users/{id}", method = RequestMethod.PUT)
	public User update(@PathVariable int id, @RequestBody String json) {
		return userDao.update(id, json);
	}
	
	@RequestMapping(path = "users/{id}", method = RequestMethod.DELETE)
	public boolean destroy(@PathVariable int id) {
		return userDao.destroy(id);
	}

}
