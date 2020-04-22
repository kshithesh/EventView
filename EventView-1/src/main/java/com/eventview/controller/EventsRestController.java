package com.eventview.controller;

import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import com.eventview.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventsRestController {

    private final EventService eventService;

    @Autowired
    public EventsRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "/")
    public List<EventsPayload> getAllEvens() {
        return eventService.getAllEvens();
    }

    @GetMapping(path = "/{event_id}")
    public Object findByEventId(@PathVariable Integer event_id) {
        return eventService.findByEventsId(event_id);
    }

    @PostMapping(path = "/")
    public @ResponseBody
    Events createEvent(@RequestBody Events event) {
        return eventService.createEvent(event);
    }

    @PostMapping(path = "/eventedit")
    public @ResponseBody Events updateEvent(@RequestBody Events event) {
        return eventService.updateEvent(event);
    }

    @DeleteMapping(path = "/eventdel")
    public @ResponseBody Events deleteEvent(@RequestBody Events event) {
        return eventService.deleteEvent(event);
    }

}
