package com.nik.rest.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nik.rest.app.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByFirstName(String firstName);
	
	List<User> findByLastName(String lastName);

}
