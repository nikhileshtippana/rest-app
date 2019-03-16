package com.nik.rest.app.service;

import java.util.Date;
import java.util.List;

import com.nik.rest.app.entity.UserEntity;
import com.nik.rest.app.exception.ServiceException;

public interface UserService {

	List<UserEntity> getUsers();

	UserEntity getUser(long id) throws ServiceException;

	UserEntity addUser(String firstName, String lastName, boolean male, Date birthDate) throws ServiceException;

	UserEntity updateUser(long id, String firstName, String lastName, boolean male, Date birthDate) throws ServiceException;

	UserEntity deleteUser(long id) throws ServiceException;
}
