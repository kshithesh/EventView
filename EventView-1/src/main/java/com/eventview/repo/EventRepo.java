package com.eventview.repo;

import java.util.List;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

public interface EventRepo {

	List<EventsPayload> getAllEvens();

	Events findByEventsId(Integer event_id);

	void createEvent(Events event);

	void updateEvent(Events event);

	void deleteEvent(Integer event_id);

}
