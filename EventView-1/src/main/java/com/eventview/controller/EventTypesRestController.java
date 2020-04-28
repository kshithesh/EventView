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

    @GetMapping(path = "/type/{eventtypeid}")
    public ResponseEntity<EvenTypes> findByEventtypeId(@PathVariable Integer eventtypeid) {
        log.info("getting eventtype by eventtype_id{}", eventtypeid);
        EvenTypes evenTypes = eventTypeService.findByEventtypeId(eventtypeid);

        if (evenTypes == null) {
            log.info("eventtype with the id{} doesn't exist", eventtypeid);
            return new ResponseEntity<EvenTypes>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<EvenTypes>(evenTypes, HttpStatus.OK);
    }


    @PostMapping(path = "/type")
    public ResponseEntity<EvenTypes>
    createEventType(@RequestBody EvenTypes eventtype) {
        log.info("creating new eventtype:{}", eventtype);

        /*
        if (eventTypeService.exists(eventtype)) {
            log.info("eventtype with same id " + eventtype.getEventTypeId() + " exists");
            return new ResponseEntity<EvenTypes>(HttpStatus.CONFLICT);
        }

         */

        eventTypeService.createEventType(eventtype);
        log.info("eventtype created");
        return new ResponseEntity<EvenTypes>(HttpStatus.CREATED);
    }

    @PostMapping(path = "/type/{eventtypeid}")
    public ResponseEntity<Void>
    updateEventType(@PathVariable Integer eventtypeid, @RequestBody EvenTypes evenTypes) {
        log.info("updating eventtype:{}", evenTypes);
        EvenTypes evenTypes1 = eventTypeService.findByEventtypeId(eventtypeid);

        if (evenTypes1 == null) {
            log.info("eventtype with id{} doesn't exist", eventtypeid);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        evenTypes1.setEventTypeId(evenTypes.getEventTypeId());
        evenTypes1.setEventid(evenTypes.getEventid());
        evenTypes1.setEventType(evenTypes.getEventType());

        eventTypeService.updateEventType(evenTypes);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/type/{eventtypeid}")
    public ResponseEntity<Void>
    deleteEventType(@PathVariable Integer eventtypeid) {
        log.info("deleting eventTypes with eventtype_id{}:", eventtypeid);
        EvenTypes evenTypes = eventTypeService.findByEventtypeId(eventtypeid);

        if (evenTypes == null) {
            log.info("eventType with eventtype_id{} doesn't exist", eventtypeid);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        eventTypeService.deleteEventType(eventtypeid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
