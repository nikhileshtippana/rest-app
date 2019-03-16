package com.nik.rest.app.service;

import java.util.List;

import com.nik.rest.app.entity.AddressEntity;
import com.nik.rest.app.exception.ServiceException;

public interface AddressService {

	List<AddressEntity> getAddresses();

	AddressEntity getAddress(long id) throws ServiceException;

	List<AddressEntity> getAddressesByEntityId(long entityId) throws ServiceException;

	AddressEntity getAddress(long entityId, String type) throws ServiceException;
	
	AddressEntity addAddress(long entityId, String type, String street1, String street2, String city, String state, String country, String zip) throws ServiceException;
	
	AddressEntity updateAddress(long id, String street1, String street2, String city, String state, String country, String zip) throws ServiceException;
	
	AddressEntity updateAddress(long entityId, String type, String street1, String street2, String city, String state, String country, String zip) throws ServiceException;

	AddressEntity deleteAddress(long entityId, String type) throws ServiceException;

	List<AddressEntity> deleteAddressesByEntityId(long entityId) throws ServiceException;

	AddressEntity deleteAddress(long id) throws ServiceException;
}
