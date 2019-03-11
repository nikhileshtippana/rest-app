package com.nik.rest.app.service;

import java.util.List;

import com.nik.rest.app.ServiceException;
import com.nik.rest.app.domain.User;

public interface UserService {
	
	List<User> getUsers();
	
	User addUser(User user) throws ServiceException;

}
