package com.nik.rest.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.rest.app.ServiceException;
import com.nik.rest.app.domain.User;
import com.nik.rest.app.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(1L, "Nikhilesh", "Tippana"));
    	users.add(new User(2L, "Priyanka", "Tera"));
	}
	
	@Autowired
	private UserService userService;
	
    @GetMapping
    public List<User> all() {
    	
        return userService.getUsers();
    }
	
    @GetMapping("/{id}")
    public User user(@PathVariable("id") long id) {
    	
    	for (User user : users) {
    		
    		if (user.getId() == id) {
    			return user;
    		}
    	}
    	
        return null;
    }
	
    @PostMapping
    public User add(@RequestBody User user) throws ServiceException {
    	
        return userService.addUser(user);
    }
	
    @PutMapping("/{id}")
    public User update(@PathVariable("id") long id, @RequestBody User user) {
    	
    	User u = user(id);
    	
    	u.setFirstName(user.getFirstName());
    	u.setLastName(user.getLastName());
    	
        return u;
    }
	
    @DeleteMapping("/{id}")
    public User update(@PathVariable("id") long id) {
    	
    	User u = user(id);
    	
    	users.remove(u);
    	
        return u;
    }
}
