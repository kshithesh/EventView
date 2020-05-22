package com.eventview.service;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

import java.util.Date;
import java.util.List;

public interface EventService {

	List<EventsPayload> getAllEvens();

    List<Events> getAllEventsCustom();

    Events findByEventsId(Integer eventId);

	void createEvent(Events event);

	void updateEvent(Events event);

	void deleteEvent(Integer eventId);

	boolean exists(Events event);

	List<Date> getAllEventDates();
}
