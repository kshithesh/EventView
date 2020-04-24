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
        log.info("getting all eventtypes");
        List<EvenTypes> evenTypes = eventTypeService.getAllEvenTypes();
        if (evenTypes == null || evenTypes.isEmpty()) {
            log.info("no eventTypes found");
            return new ResponseEntity<List<EvenTypes>>(HttpStatus.NO_CONTENT);
        }
        log.info("eventTypes received");
        return new ResponseEntity<List<EvenTypes>>(evenTypes, HttpStatus.OK);
    }

    @GetMapping(path = "/type/{event_type_id}")
    public ResponseEntity<EvenTypes> findByEventtypeId(@PathVariable Integer event_type_id) {
        log.info("getting eventtype by event_type_id{}", event_type_id);
        EvenTypes evenTypes = eventTypeService.findByEventtypeId(event_type_id);

        if (evenTypes == null) {
            log.info("eventtype with the id{} doesn't exist", event_type_id);
            return new ResponseEntity<EvenTypes>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<EvenTypes>(evenTypes, HttpStatus.OK);
    }


    @PostMapping(path = "/type")
    public ResponseEntity<EvenTypes>
    createEventType(@RequestBody EvenTypes eventtype) {
        log.info("creating new eventtype:{}", eventtype);


        if (eventTypeService.exists(eventtype)) {
            log.info("eventtype with same id " + eventtype.getEvent_type_id() + " exists");
            return new ResponseEntity<EvenTypes>(HttpStatus.CONFLICT);
        }

        eventTypeService.createEventType(eventtype);
        log.info("eventtype created");
        return new ResponseEntity<EvenTypes>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/type/{event_type_id}")
    public ResponseEntity<Void>
    updateEventType(@PathVariable Integer event_type_id, @RequestBody EvenTypes evenTypes) {
        log.info("updating eventtype:{}", evenTypes);
        EvenTypes evenTypes1 = eventTypeService.findByEventtypeId(event_type_id);

        if (evenTypes1 == null) {
            log.info("eventtype with id{} doesn't exist", event_type_id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        evenTypes1.setEvent_type_id(evenTypes.getEvent_type_id());
        evenTypes1.setEvent_id(evenTypes.getEvent_id());
        evenTypes1.setEvent_type(evenTypes.getEvent_type());

        eventTypeService.updateEventType(evenTypes);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/type/{event_type_id}")
    public ResponseEntity<Void>
    deleteEventType(@PathVariable Integer event_type_id) {
        log.info("deleting eventTypes with event_type_id{}:", event_type_id);
        EvenTypes evenTypes = eventTypeService.findByEventtypeId(event_type_id);

        if (evenTypes == null) {
            log.info("eventType with event_type_id{} doesn't exist", event_type_id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        eventTypeService.deleteEventType(event_type_id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
