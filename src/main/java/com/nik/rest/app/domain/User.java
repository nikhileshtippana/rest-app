package com.nik.rest.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nik.rest.app.entity.UserEntity;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private boolean male;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date birthDate;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String fullName() {
		return firstName + " " + lastName;
	}

	public boolean isMale() {
		return male;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", male=" + male
				+ ", birthDate=" + birthDate + "]";
	}

	public static User instance(UserEntity userEntity) {
		
		User user = new User();
		user.setId(userEntity.getId());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setMale(userEntity.isMale());
		user.setBirthDate(userEntity.getBirthDate());
		
		return user;
	}
	
	public static List<User> instance(List<UserEntity> userEntities) {
		
		if (CollectionUtils.isEmpty(userEntities)) {
    		return null;
    	}
    		
    	List<User> users = new ArrayList<>();
    	
    	for (UserEntity userEntity : userEntities) {
    		users.add(User.instance(userEntity));
    	}
    	
        return users;
	}
}
