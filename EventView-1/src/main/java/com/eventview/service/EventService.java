package com.eventview.service;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

import java.util.List;

public interface EventService {

	List<EventsPayload> getAllEvens();

	Events findByEventsId(Integer eventid);

	void createEvent(Events event);

	void updateEvent(Events event);

	void deleteEvent(Integer eventid);

	boolean exists(Events event);
}
