package com.nik.rest.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nik.rest.app.constant.AddressType;
import com.nik.rest.app.entity.AddressEntity;
import com.nik.rest.app.exception.ResourceNotFoundException;
import com.nik.rest.app.exception.ServiceException;
import com.nik.rest.app.exception.ValidationException;
import com.nik.rest.app.repository.AddressRepository;
import com.nik.rest.app.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<AddressEntity> getAddresses() {

		try {
			return (List<AddressEntity>) addressRepository.findAll();

		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}

		return null;
	}
	
	@Override
	public AddressEntity getAddress(long id) throws ServiceException {

		try {
			Optional<AddressEntity> addressEntity = addressRepository.findById(id);
			
			if (!addressEntity.isPresent()) {
				throw new ResourceNotFoundException("No such address exists with id " + id);
			}
			
			return addressEntity.get();

		} catch (ResourceNotFoundException e) {
			
			throw e;
			
		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}

		return null;
	}

	@Override
	public List<AddressEntity> getAddressesByEntityId(long entityId) throws ServiceException {
		
		try {
			return addressRepository.findByEntityId(entityId);
			
		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public AddressEntity getAddress(long entityId, String type) throws ServiceException {
		
		if (type == null) {
			
			throw new ValidationException("Address type validation failed", Arrays.asList("Address Type is required"));
		}
		
		try {
			return addressRepository.findTopByEntityIdAndType(entityId, type);
			
		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public AddressEntity addAddress(long entityId, String type, String street1, String street2, String city, String state,
			String country, String zip) throws ServiceException {
		
		validateAddress(type, street1, street2, city, state, country, zip);
		
		AddressEntity addressEntity = getAddress(entityId, type);
		
		if (addressEntity != null) {
			throw new ValidationException("Address input validation failed",
					Arrays.asList("Address already exists for entity " + entityId + " and type " + type));
		}
		
		addressEntity = new AddressEntity();
		addressEntity.setEntityId(entityId);
		addressEntity.setType(type);
		addressEntity.setStreet1(street1);
		addressEntity.setStreet2(street2);
		addressEntity.setCity(city);
		addressEntity.setState(state);
		addressEntity.setCountry(country);
		addressEntity.setZip(zip);
		
		try {
			AddressEntity addedAddress = addressRepository.save(addressEntity);
			
			System.out.println("Added " + addedAddress);
			
			return addedAddress;
			
		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}
	
	private void validateAddress(String type, String street1, String street2, String city, String state,
			String country, String zip) throws ServiceException {
		
		List<String> errors = new ArrayList<>();
		
		if (StringUtils.isBlank(type)) {
			errors.add("Address Type is required");
		}
		else if (AddressType.fromCode(type) == null) {

			errors.add("Address Type value is invalid");
		}
		
		if (StringUtils.isBlank(street1)) {
			errors.add("Street1 is required");
		}
		
		if (StringUtils.isBlank(city)) {
			errors.add("City is required");
		}
		
		if (StringUtils.isBlank(state)) {
			errors.add("State is required");
		}
		
		if (StringUtils.isBlank(country)) {
			errors.add("Country is required");
		}
		
		if (StringUtils.isBlank(zip)) {
			errors.add("Zip is required");
		}
		
		if (errors.size() > 0) {
			
			throw new ValidationException("Address input validation failed", errors);
		}
	}

	@Override
	public AddressEntity updateAddress(long id, String street1, String street2, String city, String state,
			String country, String zip) throws ServiceException {
		
		AddressEntity addressEntity = getAddress(id);
		
		validateAddress(addressEntity.getType(), street1, street2, city, state, country, zip);
		
		addressEntity.setStreet1(street1);
		addressEntity.setStreet2(street2);
		addressEntity.setCity(city);
		addressEntity.setState(state);
		addressEntity.setCountry(country);
		addressEntity.setZip(zip);
		
		try {
			AddressEntity addedAddress = addressRepository.save(addressEntity);
			
			System.out.println("Updated " + addedAddress);
			
			return addedAddress;
			
		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public AddressEntity updateAddress(long entityId, String type, String street1, String street2, String city,
			String state, String country, String zip) throws ServiceException {
		
		validateAddress(type, street1, street2, city, state, country, zip);
		
		AddressEntity addressEntity = null;
		
		try {
			addressEntity = getAddress(entityId, type);
			
		} catch (ResourceNotFoundException e) {
			
			System.out.println("Address not found for entity " + entityId);
		}
		
		if (addressEntity == null) {
			
			System.out.println("Adding address for entity " + entityId);
			
			addressEntity = new AddressEntity();
			addressEntity.setEntityId(entityId);
			addressEntity.setType(type);
		}
		
		addressEntity.setStreet1(street1);
		addressEntity.setStreet2(street2);
		addressEntity.setCity(city);
		addressEntity.setState(state);
		addressEntity.setCountry(country);
		addressEntity.setZip(zip);
		
		try {
			AddressEntity updatedAddress = addressRepository.save(addressEntity);
			
			System.out.println("Updated " + updatedAddress);
			
			return updatedAddress;
			
		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public AddressEntity deleteAddress(long entityId, String type) throws ServiceException {
		
		AddressEntity addressEntity = getAddress(entityId, type);
		
		try {
			addressRepository.delete(addressEntity);
			
			System.out.println("Deleted " + addressEntity);
			
			return addressEntity;
			
		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<AddressEntity> deleteAddressesByEntityId(long entityId) throws ServiceException {
		
		List<AddressEntity> addressEntities = getAddressesByEntityId(entityId);
		
		try {
			for (AddressEntity addressEntity : addressEntities) {
				
				addressRepository.delete(addressEntity);
				
				System.out.println("Deleted " + addressEntity);
			}
			
			return addressEntities;
			
		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public AddressEntity deleteAddress(long id) throws ServiceException {
		
		AddressEntity addressEntity = getAddress(id);
		
		try {
			addressRepository.delete(addressEntity);
			
			System.out.println("Deleted " + addressEntity);
			
			return addressEntity;
			
		} catch (Exception e) {

			System.err.println("Caught '" + e.getClass().getName() + "' with message: " + e.getMessage());
		}
		
		return null;
	}

}
