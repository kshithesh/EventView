package com.eventview.repo;

import java.util.List;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

public interface EventRepo {

	List<EventsPayload> getAllEvents();

	List<Events> getAllEventsCustom();

	EventsPayload findByEventsIdCustom(Integer eventId);

	Events findByEventsId(Integer eventId);

	void createEvent(Events event);

	void updateEvent(Events event);

	int deleteEvent(Integer eventId);

	boolean eventExists(Integer eventTypeId);

	List<Integer> getAllEventMonths();

	List<Integer> getAllEventDay();

    List<String> getTodayFNameEventType();

    List<String> getUpcomingFNameEventType();
}
