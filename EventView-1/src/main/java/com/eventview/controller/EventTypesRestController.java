package com.eventview.controller;


import com.eventview.exceptions.EventTypeExistsException;
import com.eventview.exceptions.EventTypeNotFoundException;
import com.eventview.model.EventTypes;
import com.eventview.model.Users;
import com.eventview.service.EventTypeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventTypesRestController {

    private final Logger log = LoggerFactory.getLogger(EventTypesRestController.class);

    @Autowired
    private EventTypeService eventTypeService;

    @GetMapping(path = "/event/types")
    @ApiOperation(value = "View a list of EventType")
    public String getAllEvenTypes(Model model) {
        List<EventTypes> eventTypes = eventTypeService.getAllEvenTypes();
        if (eventTypes == null || eventTypes.isEmpty())
            throw new EventTypeNotFoundException("No EventTypes found to retrieve");
        model.addAttribute("eventtypes", eventTypes);
        return "eventtypes/vieweventtypes";
        //return new ResponseEntity<>(eventTypes, HttpStatus.OK);
    }

    @GetMapping(path = "/event/type/{eventTypeId}")
    @ApiOperation(value = "Search EventType by the EventTypeID")
    public ResponseEntity<EventTypes> findByEventTypeId(@PathVariable Integer eventTypeId) {
        log.info("getting eventType by eventTypeId{}", eventTypeId);
        EventTypes eventTypes = eventTypeService.findByEventTypeId(eventTypeId);

        return new ResponseEntity<>(eventTypes, HttpStatus.OK);
    }

    @RequestMapping("/event/type/form")
    public String addForm(Model model){
        model.addAttribute("command", new EventTypes());
        return "eventtypes/eventtypeform";
    }

    @PostMapping(path = "/save/event/type")
    @ApiOperation(value = "Create an EventType by providing EventType")
    public String createEventType(@Valid @ModelAttribute("eventtypes") EventTypes eventTypes) {
        log.debug("creating new eventTypes:{}", eventTypes);
        if (eventTypeService.exists(eventTypes)) throw new EventTypeExistsException("EventType already exists");
        eventTypeService.createEventType(eventTypes);
        log.debug("eventTypes created");
        return "redirect:/event/types";
    }

    @RequestMapping("/edit/event/type/{eventTypeId}")
    public String editform(@PathVariable("eventTypeId") Integer eventTypeId, Model model){
        EventTypes eventTypes = eventTypeService.findByEventTypeId(eventTypeId);
        model.addAttribute("command", eventTypes);
        return "eventtypes/editeventtype";
    }

    @PostMapping(path = "/update/event/type/{eventTypeId}")
    @ApiOperation(value = "Update an EventType by providing EventTypeID and EventType")
    public String updateEventType(@Valid @ModelAttribute("eventtypes") EventTypes eventTypes) {
        log.debug("updating eventType:{}", eventTypes);
        /*
        EventTypes evenTypes1 = eventTypeService.findByEventTypeId(eventTypeId);
        evenTypes1.setEventTypeId(eventTypeId);
        evenTypes1.setEventType(eventTypes.getEventType());
        */
        eventTypeService.updateEventType(eventTypes);
        return "redirect:/event/types";
    }

    @GetMapping(path = "/del/event/type/{eventTypeId}")
    @ApiOperation(value = "Delete EventType based on the EventTypeID")
    public String deleteEventType(@PathVariable Integer eventTypeId) {
        log.info("deleting eventTypes with eventTypeId{}:", eventTypeId);
        EventTypes eventTypes = eventTypeService.findByEventTypeId(eventTypeId);
        if (eventTypes != null) {
            eventTypeService.deleteEventType(eventTypeId);
        }
        log.info("eventType deleted");
        return "redirect:/event/types";
    }
}
