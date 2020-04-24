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
    public ResponseEntity<List<EventsPayload>> getAllEvens() {
        log.info("getting all events");
        List<EventsPayload> payloads = eventService.getAllEvens();

        if (payloads == null || payloads.isEmpty()) {
            log.info("no events found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("events received");
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(path = "/{event_id}")
    public ResponseEntity<Events> findByEventId(@PathVariable Integer event_id) {
        log.info("getting event by id{}", event_id);
        Events events = eventService.findByEventsId(event_id);

        if (events == null) {
            log.info("event by the id{} doesn't exist", event_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Void> createEvent(@RequestBody Events event) {
        log.info("creating event");

        if (eventService.exists(event)) {
            log.info("Event by the id{} already exists", event.getEvent_id());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/{event_id}")
    public ResponseEntity<Void> updateEvent(@PathVariable Integer event_id, @RequestBody Events events) {
        log.info("Updating event{}", events);
        Events events1 = eventService.findByEventsId(event_id);

        if (events == null) {
            log.info("event by the id{} doesn't exist",event_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        events1.setEvent_id(events.getEvent_id());
        events1.setUser_id(events.getUser_id());
        events1.setEvent_type_id(events.getEvent_type_id());
        events1.setEvent_date(events.getEvent_date());

        eventService.updateEvent(events);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{event_id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer event_id) {
        log.info("Deleting Event with id{}", event_id);
        Events events = eventService.findByEventsId(event_id);

        if (events == null) {
            log.info("event with the id{}", event_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        eventService.deleteEvent(event_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
