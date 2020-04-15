package com.eventview.repo;

import java.util.List;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

public interface EventRepo {

	List<EventsPayload> getAllEvens();

	Object findByEventsId(Integer event_id);

	Events createEvent(Events event);

	Events updateEvent(Events event);

	Events deleteEvent(Events event);

}
