package com.eventview.service;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

import java.util.List;

public interface EventService {

	List<EventsPayload> getAllEvens();

	Events findByEventsId(Integer event_id);

	void createEvent(Events event);

	void updateEvent(Events event);

	void deleteEvent(Integer event_id);

	boolean exists(Events event);
}
