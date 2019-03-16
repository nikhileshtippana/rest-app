package com.nik.rest.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.nik.rest.app.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
}
