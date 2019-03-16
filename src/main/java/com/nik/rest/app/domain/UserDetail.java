package com.nik.rest.app.domain;

import com.nik.rest.app.entity.AddressEntity;
import com.nik.rest.app.entity.UserEntity;

public class UserDetail extends User {

	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserDetail [" + super.toString() + ", address=" + address + "]";
	}

	public static UserDetail instance(UserEntity userEntity, AddressEntity addressEntity) {
		
		UserDetail userDetail = new UserDetail();
		userDetail.setId(userEntity.getId());
		userDetail.setFirstName(userEntity.getFirstName());
		userDetail.setLastName(userEntity.getLastName());
		userDetail.setMale(userEntity.isMale());
		userDetail.setBirthDate(userEntity.getBirthDate());
		
		if (addressEntity != null) {
			userDetail.setAddress(Address.instance(addressEntity));
		}
		
		return userDetail;
	}
}
