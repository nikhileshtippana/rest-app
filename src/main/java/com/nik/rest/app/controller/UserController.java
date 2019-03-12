package com.nik.rest.app.controller;

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

	@Autowired
	private UserService userService;
	
    @GetMapping
    public List<User> all() {
    	
        return userService.getUsers();
    }
	
    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id) {
    	
        return userService.getUser(id);
    }
	
    @PostMapping
    public User add(@RequestBody User user) throws ServiceException {
    	
        return userService.addUser(user);
    }
	
    @PutMapping("/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody User user) throws ServiceException {
    	
    	user.setId(id);
    	
        return userService.updateUser(user);
    }
	
    @DeleteMapping("/{id}")
    public User update(@PathVariable("id") Long id) {
    	
        return userService.deleteUser(id);
    }
}
