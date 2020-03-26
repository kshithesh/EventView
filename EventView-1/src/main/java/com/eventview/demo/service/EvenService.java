/*
package com.eventview.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.eventview.demo.model.Evens;
import com.eventview.demo.model.Users;
import com.eventview.demo.dao.EvenRepo;

@Service
@Transactional
public class EvenService {

	private final EvenRepo evenRepo;

	public EvenService(EvenRepo evenRepo) {
		this.evenRepo = evenRepo;
	}
	
	public List<Evens> listAll(){
		return (List<Evens>) evenRepo.findAll();
	}

	public void saveEventDetails(Evens even) {

		evenRepo.save(even);
	}

	public Evens get(Integer event_id) {
		return evenRepo.findById(event_id).get();
	}

	public void delete(Integer event_id) {
		evenRepo.deleteById(event_id);
	}
}
*/