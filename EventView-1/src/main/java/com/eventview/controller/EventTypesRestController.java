package com.eventview.controller;


import com.eventview.model.EvenTypes;
import com.eventview.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventTypesRestController {

    private final EventTypeService eventTypeService;

    @Autowired
    public EventTypesRestController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @GetMapping(path = "/eventtypes")
    public List<EvenTypes> getAllEventypes() {
        return eventTypeService.getAllEvenTypes();
    }

    @GetMapping(path = "/eventtypes/{event_type_id}")
    public EvenTypes findByEventtypeId(@PathVariable Integer event_type_id) {
        return eventTypeService.findByEventtypeId(event_type_id);
    }

    @PostMapping(path = "/eventtypecreate")
    public @ResponseBody
    EvenTypes createEventType(@RequestBody EvenTypes eventtype) {
        return eventTypeService.createEventType(eventtype);
    }

    @PostMapping(path = "/eventtypeedit")
    public @ResponseBody EvenTypes updateEventType(@RequestBody EvenTypes eventtype) {
        return eventTypeService.updateEventType(eventtype);
    }

    @DeleteMapping(path = "/eventtypedel")
    public @ResponseBody EvenTypes deleteEventType(@RequestBody EvenTypes eventtype) {
        return eventTypeService.deleteEventType(eventtype);
    }
}
