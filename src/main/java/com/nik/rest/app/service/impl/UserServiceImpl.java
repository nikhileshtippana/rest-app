package com.nik.rest.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.rest.app.ServiceException;
import com.nik.rest.app.domain.User;
import com.nik.rest.app.repository.UserRepository;
import com.nik.rest.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		
		try {
			return (List<User>) userRepository.findAll();
			
		} catch (Exception e) {
			
			System.out.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public User addUser(User user) throws ServiceException {
		
		validateUser(user);
		
		user.setId(null);
		
		try {
			User addedUser = userRepository.save(user);
			
			System.out.println("Added user " + addedUser.fullName());
			
			return addedUser;
			
		} catch (Exception e) {
			
			System.out.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}
	
	private void validateUser(User user) throws ServiceException {
		
		List<String> errors = new ArrayList<>();
		
		if (StringUtils.isBlank(user.getFirstName())) {
			errors.add("First Name is required");
		}
		
		if (StringUtils.isBlank(user.getLastName())) {
			errors.add("Last Name is required");
		}
		
		if (errors.size() > 0) {
			
			String message = String.join(",", errors);
			
			throw new ServiceException(message);
		}
	}

}
