package com.nik.rest.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.rest.app.entity.UserEntity;
import com.nik.rest.app.exception.ResourceNotFoundException;
import com.nik.rest.app.exception.ServiceException;
import com.nik.rest.app.exception.ValidationException;
import com.nik.rest.app.repository.UserRepository;
import com.nik.rest.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserEntity> getUsers() {

		try {
			return (List<UserEntity>) userRepository.findAll();

		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}

		return null;
	}

	@Override
	public UserEntity getUser(long id) throws ServiceException {
		
		try {
			Optional<UserEntity> userEntity = userRepository.findById(id);
			
			if (!userEntity.isPresent()) {
				throw new ResourceNotFoundException("No such user exists with id " + id);
			}
			
			return userEntity.get();
			
		} catch (ResourceNotFoundException e) {
			
			throw e;
			
		} catch (Exception e) {
			
			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public UserEntity addUser(String firstName, String lastName, boolean male, Date birthDate) throws ServiceException {
		
		validateUser(firstName, lastName, birthDate);
		
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(firstName);
		userEntity.setLastName(lastName);
		userEntity.setMale(male);
		userEntity.setBirthDate(birthDate);
		
		try {
			UserEntity addedUser = userRepository.save(userEntity);
			
			System.out.println("Added user " + addedUser.fullName());
			
			return addedUser;
			
		} catch (Exception e) {
			
			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	private void validateUser(String firstName, String lastName, Date birthDate) throws ServiceException {
		
		List<String> errors = new ArrayList<>();
		
		if (StringUtils.isBlank(firstName)) {
			errors.add("First Name is required");
		}
		
		if (StringUtils.isBlank(lastName)) {
			errors.add("Last Name is required");
		}
		
		if (birthDate == null || new Date().before(birthDate)) {
			errors.add("Birth Date must be today or a past date");
		}
		
		if (errors.size() > 0) {
			
			throw new ValidationException("User input validation failed", errors);
		}
	}

	@Override
	public UserEntity updateUser(long id, String firstName, String lastName, boolean male, Date birthDate)
			throws ServiceException {
		
		UserEntity userEntity = getUser(id);
		
		validateUser(firstName, lastName, birthDate);

		userEntity.setFirstName(firstName);
		userEntity.setLastName(lastName);
		userEntity.setMale(male);
		userEntity.setBirthDate(birthDate);
		
		try {
			UserEntity updatedUser = userRepository.save(userEntity);
			
			System.out.println("Updated user " + updatedUser.fullName());
			
			return updatedUser;
			
		} catch (Exception e) {
			
			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public UserEntity deleteUser(long id) throws ServiceException {
		
		UserEntity userEntity = getUser(id);
		
		try {
			userRepository.delete(userEntity);
			
			System.out.println("Deleted user " + userEntity.fullName());
			
			return userEntity;
			
		} catch (Exception e) {
			
			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}
}
