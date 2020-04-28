package com.eventview.repo;

import java.util.List;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

public interface EventRepo {

	List<EventsPayload> getAllEvens();

	List<Events> getAllEvents();

	Events findByEventsId(Integer eventId);

	void createEvent(Events event);

	void updateEvent(Events event);

	void deleteEvent(Integer eventId);

}
