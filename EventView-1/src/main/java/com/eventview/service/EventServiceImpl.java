package com.eventview.service;

import com.eventview.model.EventsPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventview.model.Events;
import com.eventview.repo.EventRepo;

import java.util.List;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepo eventRepo;

	@Override
	public List<EventsPayload> getAllEvens(){
		return eventRepo.getAllEvens();
	}
	
	@Override
	public Events findByEventsId(Integer event_id) {
		return eventRepo.findByEventsId(event_id);
	}

	@Override
	public void createEvent(Events event) {
		 eventRepo.createEvent(event);
	}

	@Override
	public void updateEvent(Events event) {
		eventRepo.updateEvent(event);
	}

	@Override
	public void deleteEvent(Integer event_id) {
		 eventRepo.deleteEvent(event_id);
	}

	@Override
	public boolean exists(Events event) {
		return findByEventsId(event.getEvent_id()) != null;
	}

}
