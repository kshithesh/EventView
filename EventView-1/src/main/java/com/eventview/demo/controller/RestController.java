package com.eventview.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventview.demo.dao.EventViewRepo;
import com.eventview.demo.model.EvenType;
import com.eventview.demo.model.EventsPayload;
import com.eventview.demo.model.Users;

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
