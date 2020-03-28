package com.eventview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventview.dao.EventViewRepo;
import com.eventview.model.EvenType;
import com.eventview.model.EventsPayload;
import com.eventview.model.Users;

@org.springframework.web.bind.annotation.RestController
@RequestMapping
public class RestController {

	@Autowired
	EventViewRepo eventviewRepo;

	@GetMapping(path = "/users")
	public List<Users> getAllUsers() {

		return eventviewRepo.getAllUsers();

	}

	@GetMapping(path = "/events")
	public List<EventsPayload> getAllEvens() {
		return eventviewRepo.getAllEvens();
	}

	@GetMapping(path = "/eventtypes")
	public List<EvenType> getAllEventypes() {
		return eventviewRepo.getAllEvenTypes();
	}

	// @GetMapping("/view")
	// public List<Users> listAll(){
	// return userservice.listAll1();

	// }
}
