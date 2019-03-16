package com.nik.rest.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nik.rest.app.entity.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

	List<AddressEntity> findByEntityId(long entityId);
	
	AddressEntity findTopByEntityIdAndType(Long entityId, String type);
}
