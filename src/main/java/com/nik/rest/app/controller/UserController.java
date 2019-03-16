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

import com.nik.rest.app.constant.AddressType;
import com.nik.rest.app.domain.Address;
import com.nik.rest.app.domain.User;
import com.nik.rest.app.domain.UserDetail;
import com.nik.rest.app.entity.AddressEntity;
import com.nik.rest.app.entity.UserEntity;
import com.nik.rest.app.exception.ServiceException;
import com.nik.rest.app.service.AddressService;
import com.nik.rest.app.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;
	
    @GetMapping
    public List<User> all() {
    	
    	List<UserEntity> userEntities = userService.getUsers();
    	
    	return User.instance(userEntities);
    }
	
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id) throws ServiceException {
    	
    	UserEntity userEntity = userService.getUser(id);
    	
        return User.instance(userEntity);
    }
	
    @GetMapping("/{id}/detail")
    public User getUserDetail(@PathVariable("id") long id) throws ServiceException {
    	
    	UserEntity userEntity = userService.getUser(id);
    	
    	AddressEntity addressEntity = addressService.getAddress(userEntity.getId(), AddressType.PHYSICAL.getCode());
    	
        return UserDetail.instance(userEntity, addressEntity);
    }
	
    @GetMapping("/{id}/addresses")
    public List<Address> getUserAddresses(@PathVariable("id") long id) throws ServiceException {
    	
    	List<AddressEntity> addressEntities = addressService.getAddressesByEntityId(id);
    	
        return Address.instance(addressEntities);
    }
	
    @PostMapping
    public User addUser(@RequestBody User user) throws ServiceException {
    	
		UserEntity userEntity = userService.addUser(user.getFirstName(), user.getLastName(), user.isMale(),
				user.getBirthDate());
    	
        return User.instance(userEntity);
    }
	
    @PostMapping("/detail")
    public UserDetail addUserDetail(@RequestBody UserDetail userDetail) throws ServiceException {
    	
		UserEntity userEntity = userService.addUser(userDetail.getFirstName(), userDetail.getLastName(),
				userDetail.isMale(), userDetail.getBirthDate());
		
		Address address = userDetail.getAddress();
		AddressEntity addressEntity = null;
		
		if (address != null) {
			
			addressEntity = addressService.addAddress(userEntity.getId(), AddressType.PHYSICAL.getCode(), address.getStreet1(),
					address.getStreet2(), address.getCity(), address.getState(), address.getCountry(),
					address.getZip());
		}
    	
        return UserDetail.instance(userEntity, addressEntity);
    }
	
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) throws ServiceException {
    	
		UserEntity userEntity = userService.updateUser(id, user.getFirstName(), user.getLastName(), user.isMale(),
				user.getBirthDate());
    	
        return User.instance(userEntity);
    }
	
    @PutMapping("/{id}/detail")
    public UserDetail updateUserDetail(@PathVariable("id") long id, @RequestBody UserDetail userDetail) throws ServiceException {
    	
		UserEntity userEntity = userService.updateUser(id, userDetail.getFirstName(), userDetail.getLastName(),
				userDetail.isMale(), userDetail.getBirthDate());
		
		Address address = userDetail.getAddress();
		AddressEntity addressEntity = null;
		
		if (address != null) {
			
			addressEntity = addressService.updateAddress(userEntity.getId(), AddressType.PHYSICAL.getCode(), address.getStreet1(),
					address.getStreet2(), address.getCity(), address.getState(), address.getCountry(),
					address.getZip());
		}
    	
        return UserDetail.instance(userEntity, addressEntity);
    }
	
    @DeleteMapping("/{id}")
    public User update(@PathVariable("id") Long id) throws ServiceException {
    	
    	UserEntity userEntity = userService.deleteUser(id);
    	
    	addressService.deleteAddressesByEntityId(userEntity.getId());
    	
        return User.instance(userEntity);
    }
}
