package com.eventview.service;

import com.eventview.model.EmailText;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import com.eventview.model.Users;

import java.util.List;
import java.util.Map;

public interface EventService {

	List<EventsPayload> getAllEventsCustom();

    List<Events> getAllEvents();

    EventsPayload findByEventsIdCustom(Integer eventId);

    Events findByEventsId(Integer eventId);

	void createEvent(Events event);

	void updateEvent(Events event);

	void deleteEvent(Integer eventId);

	boolean exists(Events event);

	List<EmailText> getUpcomingFNameEventType();
}
