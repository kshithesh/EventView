package com.eventview.controller;


import com.eventview.exceptions.EventTypeExistsException;
import com.eventview.model.EvenTypes;
import com.eventview.service.EventTypeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EventTypesRestController {

    private final Logger log = LoggerFactory.getLogger(EventTypesRestController.class);

    @Autowired
    private EventTypeService eventTypeService;

    @GetMapping(path = "/event/types")
    @ApiOperation(value = "View a list of EventType")
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

    @GetMapping(path = "/event/type/{eventTypeId}")
    @ApiOperation(value = "Search EventType by the EventTypeID")
    public ResponseEntity<EvenTypes> findByEventTypeId(@PathVariable Integer eventTypeId) {
        log.info("getting eventType by eventTypeId{}", eventTypeId);
        EvenTypes evenTypes = eventTypeService.findByEventTypeId(eventTypeId);

        return new ResponseEntity<>(evenTypes, HttpStatus.OK);
    }


    @PostMapping(path = "/event/type")
    @ApiOperation(value = "Create an EventType by providing EventType")
    public ResponseEntity<EvenTypes>
    createEventType(@Valid @RequestBody EvenTypes evenTypes) {
        log.info("creating new evenTypes:{}", evenTypes);
        if (eventTypeService.exists(evenTypes)) throw new EventTypeExistsException("EventType already exists");

        eventTypeService.createEventType(evenTypes);
        log.info("evenTypes created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "/event/type/{eventTypeId}")
    @ApiOperation(value = "Update an EventType by providing EventTypeID and EventType")
    public ResponseEntity<Void>
    updateEventType(@PathVariable Integer eventTypeId,@Valid @RequestBody EvenTypes evenTypes) {
        log.info("updating eventType:{}", evenTypes);
        EvenTypes evenTypes1 = eventTypeService.findByEventTypeId(eventTypeId);
            evenTypes1.setEventTypeId(eventTypeId);
            evenTypes1.setEventType(evenTypes.getEventType());
        eventTypeService.updateEventType(evenTypes1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/event/type/{eventTypeId}")
    @ApiOperation(value = "Delete EventType based on the EventTypeID")
    public ResponseEntity<Void>
    deleteEventType(@PathVariable Integer eventTypeId) {
        log.info("deleting eventTypes with eventTypeId{}:", eventTypeId);
        EvenTypes evenTypes = eventTypeService.findByEventTypeId(eventTypeId);

        if (evenTypes != null){
            eventTypeService.deleteEventType(eventTypeId);
        }
        log.info("eventType deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
