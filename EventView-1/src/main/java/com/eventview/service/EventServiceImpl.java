package com.eventview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import com.eventview.repo.EventRepo;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepo eventRepo;
	
	@Override
	public List<EventsPayload> getAllEvens(){
		return eventRepo.getAllEvens();
	}
	
	@Override
	public Object findByEventsId(Integer event_id) {
		return eventRepo.findByEventsId(event_id);
	}

	@Override
	public Events createEvent(Events event) {
		return eventRepo.createEvent(event);
	}

	@Override
	public Events updateEvent(Events event) {
		return eventRepo.updateEvent(event);
	}

	@Override
	public Events deleteEvent(Events event) {
		return eventRepo.deleteEvent(event);
	}

}
