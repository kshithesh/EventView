package com.eventview.controller;

import com.eventview.exceptions.EventExistsException;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import com.eventview.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventsRestController {

    private final Logger log = LoggerFactory.getLogger(EventsRestController.class);

    @Autowired
    private EventService eventService;

    @GetMapping(path = "/events")
    public ResponseEntity<List<EventsPayload>> getAllEvents() {
        log.info("getting all events");
        List<EventsPayload> eventsPayload = eventService.getAllEvens();

        if (eventsPayload == null || eventsPayload.isEmpty()) {
            log.info("no users found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventsPayload, HttpStatus.OK);
    }


    @GetMapping(path = "/event/{eventId}")
    public ResponseEntity<EventsPayload> findByEventsId(@PathVariable("eventId") Integer eventId) {
        log.debug("getting event by id - {}", eventId);
        EventsPayload eventsPayload = eventService.findByEventsIdCustom(eventId);

        return new ResponseEntity<>(eventsPayload, HttpStatus.OK);
    }

    @PostMapping(path = "/event")
    public ResponseEntity<Void> createEvent(@RequestBody Events event) {
        log.info("creating event" + event);
        if (eventService.exists(event)) throw new EventExistsException("Event already exists");

        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/event/{eventId}")
    public ResponseEntity<Void> updateEvent(@PathVariable Integer eventId, @RequestBody Events events) {
        log.info("Updating event{}", events);
        Events events1 = eventService.findByEventsId(eventId);

        if (events1 != null) {
            events1.setEventId(eventId);
            events1.setUserId(events.getUserId());
            events1.setEventTypeId(events.getEventTypeId());
            events1.setEventDate(events.getEventDate());
        }
        eventService.updateEvent(events1);
        log.debug("update successful");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/event/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") Integer eventId) {
        log.info("Deleting Event with id{}", eventId);
        Events events = eventService.findByEventsId(eventId);

        if (events != null) {
            eventService.deleteEvent(eventId);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
