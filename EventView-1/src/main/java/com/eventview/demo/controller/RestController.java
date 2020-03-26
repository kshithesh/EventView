package com.eventview.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventview.demo.dao.UserRepo;
import com.eventview.demo.model.EvenType;
import com.eventview.demo.model.Evens;
import com.eventview.demo.model.EventsPayload;
import com.eventview.demo.model.Users;

@org.springframework.web.bind.annotation.RestController
@RequestMapping
public class RestController {

	@Autowired
	UserRepo userRepository;

	@GetMapping(path = "/users")
	public List<Users> getAllUsers() {

		return userRepository.getAllUsers();

	}

	@GetMapping(path = "/events")
	public List<EventsPayload> getAllEvens() {
		return userRepository.getAllEvens();
	}

	@GetMapping(path = "/eventtypes")
	public List<EvenType> getAllEventypes() {
		return userRepository.getAllEvenTypes();
	}

	// @GetMapping("/view")
	// public List<Users> listAll(){
	// return userservice.listAll1();

	// }
}
