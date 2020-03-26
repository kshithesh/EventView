/*
package com.eventview.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.eventview.demo.model.EvenType;
import com.eventview.demo.model.Evens;
import com.eventview.demo.model.Users;
import com.eventview.demo.dao.EvenRepo;
import com.eventview.demo.dao.EvenTypeRepo;
import com.eventview.demo.dao.UserRepo;

@Service
@Transactional
public class UserService {

	private final UserRepo userRepo;
	private final EvenTypeRepo typeRepo;
	private final EvenRepo evenRepo;

	public UserService(UserRepo userRepo, EvenTypeRepo typeRepo, EvenRepo evenRepo) {
		this.typeRepo = typeRepo;
		this.userRepo = userRepo;
		this.evenRepo = evenRepo;
	}

	public List<Users> listAll() {
		return (List<Users>) userRepo.findAll();
	}

	public void saveUser(Users user) {

		userRepo.save(user);
	}

	public Users get(Integer user_id) {
		return userRepo.findById(user_id).get();
	}

	public void delete(Integer user_id) {
		userRepo.deleteById(user_id);
	}

	public List<EvenType> listAll2() {
		return (List<EvenType>) typeRepo.findAll();
	}

	public void saveTypeDetails2(EvenType type) {

		typeRepo.save(type);
	}

	public EvenType get2(Integer event_type_id) {
		return typeRepo.findById(event_type_id).get();
	}

	public void delete2(Integer event_type_id) {
		typeRepo.deleteById(event_type_id);
	}

	public List<Users> listAll1() {
		return (List<Users>) userRepo.findAll();
	}

	public void saveEventDetails1(Evens even) {

		evenRepo.save(even);
	}

	public Evens get1(Integer event_id) {
		return evenRepo.findById(event_id).get();
	}

	public void delete1(Integer event_id) {
		evenRepo.deleteById(event_id);
	}
}
*/