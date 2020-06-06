package com.eventview.repo;

import com.eventview.model.EmailTemplate;
import com.eventview.model.EmailText;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

import java.util.List;

public interface EventRepo {

    List<EventsPayload> getAllEventsCustom();

    List<Events> getAllEvents();

    EventsPayload findByEventsIdCustom(Integer eventId);

    Events findByEventsId(Integer eventId);

    void createEvent(Events event);

    void updateEvent(Events event);

    int deleteEvent(Integer eventId);

    boolean eventExists(Integer eventTypeId);

    List<EmailText> getUpcomingFNameEventType();
}
