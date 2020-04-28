package com.eventview.service;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import com.eventview.repo.EventRepo;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepo eventRepo;

    @Override
    public List<EventsPayload> getAllEvens() {
        return eventRepo.getAllEvens();
    }

    @Override
    public List<Events> getAllEvents() {
        return eventRepo.getAllEvents();
    }

    @Override
    public Events findByEventsId(Integer eventId) {
            return eventRepo.findByEventsId(eventId);
        }

    @Override
    public void createEvent(Events event) {
        eventRepo.createEvent(event);
    }

    @Override
    public void updateEvent(Events event) {
        eventRepo.updateEvent(event);
    }

    @Override
    public void deleteEvent(Integer eventId) {
        eventRepo.deleteEvent(eventId);
    }

    @Override
    public boolean exists(Events event) {
        return eventRepo.findByEventsId(event.getEventId()) != null;
    }

}
