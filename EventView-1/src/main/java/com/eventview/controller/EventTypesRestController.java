package com.eventview.controller;


import com.eventview.model.EvenTypes;
import com.eventview.service.EventTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventTypesRestController {

    private final Logger log = LoggerFactory.getLogger(EventTypesRestController.class);

    @Autowired
    private EventTypeService eventTypeService;

    @GetMapping(path = "/types")
    public ResponseEntity<List<EvenTypes>> getAllEvenTypes() {
        log.info("getting all eventTypes");
        List<EvenTypes> evenTypes = eventTypeService.getAllEvenTypes();
        if (evenTypes == null || evenTypes.isEmpty()) {
            log.info("no eventTypes found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("eventTypes received");
        return new ResponseEntity<>(evenTypes, HttpStatus.OK);
    }

    @GetMapping(path = "/type/{eventTypeId}")
    public ResponseEntity<EvenTypes> findByEventTypeId(@PathVariable Integer eventTypeId) {
        log.info("getting eventType by eventTypeId{}", eventTypeId);
        EvenTypes evenTypes = eventTypeService.findByEventTypeId(eventTypeId);

        if (evenTypes == null) {
            log.info("eventType with the id{} doesn't exist", eventTypeId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(evenTypes, HttpStatus.OK);
    }


    @PostMapping(path = "/type")
    public ResponseEntity<EvenTypes>
    createEventType(@RequestBody EvenTypes evenTypes) {
        log.info("creating new evenTypes:{}", evenTypes);
        if (eventTypeService.exists(evenTypes)) {
            log.info("evenTypes with same id " + evenTypes.getEventTypeId() + " exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        eventTypeService.createEventType(evenTypes);
        log.info("evenTypes created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/type/{eventTypeId}")
    public ResponseEntity<Void>
    updateEventType(@PathVariable Integer eventTypeId, @RequestBody EvenTypes evenTypes) {
        log.info("updating eventType:{}", evenTypes);
        EvenTypes evenTypes1 = eventTypeService.findByEventTypeId(eventTypeId);

        if (evenTypes1 == null) {
            log.info("eventType with id{} doesn't exist", eventTypeId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        evenTypes1.setEventTypeId(evenTypes.getEventTypeId());
        evenTypes1.setEventid(evenTypes.getEventid());
        evenTypes1.setEventType(evenTypes.getEventType());

        eventTypeService.updateEventType(evenTypes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/type/{eventTypeId}")
    public ResponseEntity<Void>
    deleteEventType(@PathVariable Integer eventTypeId) {
        log.info("deleting eventTypes with eventTypeId{}:", eventTypeId);
        EvenTypes evenTypes = eventTypeService.findByEventTypeId(eventTypeId);

        if (evenTypes == null) {
            log.info("eventType with eventTypeId{} doesn't exist", eventTypeId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        eventTypeService.deleteEventType(eventTypeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
