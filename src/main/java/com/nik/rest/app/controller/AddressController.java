package com.nik.rest.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nik.rest.app.domain.Address;
import com.nik.rest.app.entity.AddressEntity;
import com.nik.rest.app.exception.ServiceException;
import com.nik.rest.app.service.AddressService;

@RequestMapping("/addresses")
@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

    @GetMapping
    public List<Address> all() {
    	
    	List<AddressEntity> addressEntities = addressService.getAddresses();
    	
        return Address.instance(addressEntities);
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable("id") long id) throws ServiceException {

    	AddressEntity addressEntity = addressService.getAddress(id);
    	
        return Address.instance(addressEntity);
    }
	
    @PostMapping("/entity/{entity-id}/type/{type}")
    public Address addAddress(@PathVariable("entity-id") long entityId, @PathVariable("type") String type, @RequestBody Address address) throws ServiceException {
    	
		AddressEntity addressEntity = addressService.addAddress(entityId, type, address.getStreet1(),
				address.getStreet2(), address.getCity(), address.getState(), address.getCountry(), address.getZip());
    	
        return Address.instance(addressEntity);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable("id") long id, @RequestBody Address address) throws ServiceException {

		AddressEntity addressEntity = addressService.updateAddress(id, address.getStreet1(), address.getStreet2(),
				address.getCity(), address.getState(), address.getCountry(), address.getZip());
    	
        return Address.instance(addressEntity);
    }

    @DeleteMapping("/{id}")
    public Address deleteAddress(@PathVariable("id") long id) throws ServiceException {

    	AddressEntity addressEntity = addressService.deleteAddress(id);
    	
        return Address.instance(addressEntity);
    }
}
