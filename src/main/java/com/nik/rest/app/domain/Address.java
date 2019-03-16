package com.nik.rest.app.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.nik.rest.app.entity.AddressEntity;

public class Address {

	private long id;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String country;
	private String zip;

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", state="
				+ state + ", country=" + country + ", zip=" + zip + "]";
	}

	public static Address instance(AddressEntity addressEntity) {
		
		if (addressEntity == null) {
			return null;
		}
		
		Address address = new Address();
		address.setId(addressEntity.getId());
		address.setStreet1(addressEntity.getStreet1());
		address.setStreet2(addressEntity.getStreet2());
		address.setCity(addressEntity.getCity());
		address.setState(addressEntity.getState());
		address.setCountry(addressEntity.getCountry());
		address.setZip(addressEntity.getZip());
		
		return address;
	}
	
	public static List<Address> instance(List<AddressEntity> addressEntities) {
		
		if (CollectionUtils.isEmpty(addressEntities)) {
			return null;
		}
		
		List<Address> addresses = new ArrayList<>();
		
		for (AddressEntity addressEntity : addressEntities) {
			addresses.add(instance(addressEntity));
		}
		
		return addresses;
	}

}
