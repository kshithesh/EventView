package com.eventview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventview.model.EvenTypes;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import com.eventview.model.Users;
import com.eventview.service.EventService;
import com.eventview.service.EventTypeService;
import com.eventview.service.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventTypeService eventTypeService;


//Users
	
	@GetMapping(path = "/users")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(path = "/users/{user_id}")
	public Users findByUserId(@PathVariable Integer user_id) {
		return userService.findByUserId(user_id);
	}
	
	@PostMapping(path = "/usercreate")
	public @ResponseBody Users createUser(@RequestBody Users user) {
		return userService.createUser(user);
	}
	
	@PostMapping(path = "/useredit")
	public @ResponseBody Users updateUser(@RequestBody Users user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping(path = "/userdel")
	public @ResponseBody Users deleteRide(@RequestBody Users user) {
		return userService.deleteUser(user);
	}

//Events
	
	@GetMapping(path = "/events")
	public List<EventsPayload> getAllEvens() {
		return eventService.getAllEvens();
	}

	@GetMapping(path = "/events/{event_id}")
	public Object findByEventId(@PathVariable Integer event_id) {
		return eventService.findByEventsId(event_id);
	}
	
	@PostMapping(path = "/eventcreate")
	public @ResponseBody Events createEvent(@RequestBody Events event) {
		return eventService.createEvent(event);
	}
	
	@PostMapping(path = "/eventedit")
	public @ResponseBody Events updateEvent(@RequestBody Events event) {
		return eventService.updateEvent(event);
	}
	
	@DeleteMapping(path = "/eventdel")
	public @ResponseBody Events deleteEvent(@RequestBody Events event) {
		return eventService.deleteEvent(event);
	}
	
//EventTypes
	
	@GetMapping(path = "/eventtypes")
	public List<EvenTypes> getAllEventypes() {
		return eventTypeService.getAllEvenTypes();
	}

	@GetMapping(path = "/eventtypes/{event_type_id}")
	public EvenTypes findByEventtypeId(@PathVariable Integer event_type_id) {
		return eventTypeService.findByEventtypeId(event_type_id);
	}
	
	@PostMapping(path = "/eventtypecreate")
	public @ResponseBody EvenTypes createEventType(@RequestBody EvenTypes eventtype) {
		return eventTypeService.createEventType(eventtype);
	}
	
	@PostMapping(path = "/eventtypeedit")
	public @ResponseBody EvenTypes updateEventType(@RequestBody EvenTypes eventtype) {
		return eventTypeService.updateEventType(eventtype);
	}
	
	@DeleteMapping(path = "/eventtypedel")
	public @ResponseBody EvenTypes deleteEventType(@RequestBody EvenTypes eventtype) {
		return eventTypeService.deleteEventType(eventtype);
	}
	
	
}
