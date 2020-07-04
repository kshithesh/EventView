package com.eventview.controller;


import com.eventview.exceptions.EventTypeExistsException;
import com.eventview.exceptions.EventTypeNotFoundException;
import com.eventview.model.EventTypes;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EventTypesRestController {

    private final Logger log = LoggerFactory.getLogger(EventTypesRestController.class);

    @Autowired
    private EventTypeService eventTypeService;

    @GetMapping(path = "/event/types")
    @ApiOperation(value = "View a list of EventType")
    public ResponseEntity<List<EventTypes>> getAllEvenTypes() {
        List<EventTypes> eventTypes = eventTypeService.getAllEvenTypes();
        if (eventTypes == null || eventTypes.isEmpty())
            throw new EventTypeNotFoundException("No EventTypes found to retrieve");
        return new ResponseEntity<>(eventTypes, HttpStatus.OK);
    }

    @GetMapping(path = "/event/type/{eventTypeId}")
    @ApiOperation(value = "Search EventType by the EventTypeID")
    public ResponseEntity<EventTypes> findByEventTypeId(@PathVariable Integer eventTypeId) {
        log.info("getting eventType by eventTypeId{}", eventTypeId);
        EventTypes eventTypes = eventTypeService.findByEventTypeId(eventTypeId);

        return new ResponseEntity<>(eventTypes, HttpStatus.OK);
    }

    @PostMapping(path = "/event/type")
    @ApiOperation(value = "Create an EventType by providing EventType")
    public ResponseEntity<EventTypes> createEventType(@Valid @RequestBody EventTypes eventTypes) {
        log.debug("creating new eventTypes:{}", eventTypes);
        if (eventTypeService.exists(eventTypes)) throw new EventTypeExistsException("EventType already exists");
        eventTypeService.createEventType(eventTypes);
        log.debug("eventTypes created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping(path = "/event/type/{eventTypeId}")
    @ApiOperation(value = "Update an EventType by providing EventTypeID and EventType")
    public ResponseEntity<Void> updateEventType(@PathVariable Integer eventTypeId,@Valid @RequestBody EventTypes eventTypes) {
        log.debug("updating eventType:{}", eventTypes);
        EventTypes eventTypes1 = eventTypeService.findByEventTypeId(eventTypeId);
        if (eventTypes1 != null) {
            eventTypes1.setEventTypeId(eventTypeId);
            eventTypes1.setEventType(eventTypes.getEventType());
        }
        eventTypeService.updateEventType(eventTypes1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/event/type/{eventTypeId}")
    @ApiOperation(value = "Delete EventType based on the EventTypeID")
    public ResponseEntity<Void> deleteEventType(@PathVariable Integer eventTypeId) {
        log.info("deleting eventTypes with eventTypeId{}:", eventTypeId);
        EventTypes eventTypes = eventTypeService.findByEventTypeId(eventTypeId);
        if (eventTypes != null) {
            eventTypeService.deleteEventType(eventTypeId);
        }
        log.info("eventType deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
