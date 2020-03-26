/*
package com.eventview.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.eventview.demo.dao.EvenTypeRepo;
import com.eventview.demo.model.EvenType;
import com.eventview.demo.model.Evens;
import com.eventview.demo.model.Users;

@Service 
@Transactional
public class EvenTypeService {

	private final EvenTypeRepo typeRepo;

	public EvenTypeService(EvenTypeRepo typeRepo) {
		this.typeRepo = typeRepo;
	}
	
	public List<EvenType> listAll(){
		return (List<EvenType>) typeRepo.findAll();
	}

	public void saveTypeDetails(EvenType type) {

		typeRepo.save(type);
	}
	
	public EvenType get(Integer event_type_id) {
		return typeRepo.findById(event_type_id).get();
	}

	public void delete(Integer event_type_id) {
		typeRepo.deleteById(event_type_id);
	}
}
*/