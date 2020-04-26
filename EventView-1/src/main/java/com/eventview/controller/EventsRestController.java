package com.eventview.controller;

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

    @GetMapping(path = "/")
    public ResponseEntity<List<EventsPayload>> getAllEvents(){
        log.info("getting all events");
        List<EventsPayload> eventsPayload = eventService.getAllEvens();

        if (eventsPayload == null || eventsPayload.isEmpty()) {
            log.info("no users found");
            return new ResponseEntity<List<EventsPayload>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<EventsPayload>>(eventsPayload,HttpStatus.OK);
    }



    @GetMapping(path = "/{eventid}")
    public ResponseEntity<Events> findByEventId(@PathVariable("eventid") Integer eventid) {
        log.debug("getting event by id - {}", eventid);
        Events events = eventService.findByEventsId(eventid);

        if (events == null) {
            log.info("event by the id{} doesn't exist", eventid);
            return new ResponseEntity<Events>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Events>(events, HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Void> createEvent(@RequestBody Events event) {
        log.info("creating event" +event);
        /*
        if (eventService.exists(event)) {
            log.info("Event by the id{} already exists", event.getEventid());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        */
        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/{eventid}")
    public ResponseEntity<Void> updateEvent(@PathVariable Integer eventid, @RequestBody Events events) {
        log.info("Updating event{}", events);
        Events events1 = eventService.findByEventsId(eventid);

        if (events == null) {
            log.info("event by the id{} doesn't exist",eventid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        events1.setEventId(events.getEventId());
        events1.setUserId(events.getUserId());
        events1.setEventTypeId(events.getEventTypeId());
        events1.setEventdate(events.getEventdate());

        eventService.updateEvent(events);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{eventid}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer eventid) {
        log.info("Deleting Event with id{}", eventid);
        Events events = eventService.findByEventsId(eventid);

        if (events == null) {
            log.info("event with the id{}", eventid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        eventService.deleteEvent(eventid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
