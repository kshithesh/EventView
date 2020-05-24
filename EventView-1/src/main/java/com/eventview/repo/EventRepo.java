package com.eventview.repo;

import java.util.Date;
import java.util.List;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

public interface EventRepo {

	List<EventsPayload> getAllEvents();

	List<Events> getAllEventsCustom();

	Events findByEventsId(Integer eventId);

	void createEvent(Events event);

	void updateEvent(Events event);

	int deleteEvent(Integer eventId);

	boolean eventExists(Integer eventTypeId);

	List<Date> getAllEventDates();


}
