package com.eventview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventview.dao.EventViewRepo;
import com.eventview.model.EvenType;
import com.eventview.model.Evens;
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

	@GetMapping(path = "/users/{user_id}")
	public Users findByUserId(@PathVariable Integer user_id) {
		return eventviewRepo.findByUserId(user_id);
	}

	@GetMapping(path = "/events/{event_id}")
	public Object findByEventId(@PathVariable Integer event_id) {
		return eventviewRepo.findByEventsId(event_id);
	}

	@GetMapping(path = "/eventtypes/{event_type_id}")
	public EvenType findByEventtypeId(@PathVariable Integer event_type_id) {
		return eventviewRepo.findByEventtypeId(event_type_id);
	}
}
