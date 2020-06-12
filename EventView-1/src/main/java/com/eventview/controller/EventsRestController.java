package com.eventview.controller;

import com.eventview.exceptions.EventExistsException;
import com.eventview.exceptions.EventNotFoundException;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import com.eventview.model.Users;
import com.eventview.service.EventService;
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
public class EventsRestController {

    private final Logger log = LoggerFactory.getLogger(EventsRestController.class);

    @Autowired
    private EventService eventService;

    @GetMapping(path = "/events")
    @ApiOperation(value = "View a list of Events")
    public String getAllEvents(Model model) {
        List<EventsPayload> eventsPayload = eventService.getAllEventsCustom();
        if (eventsPayload == null || eventsPayload.isEmpty()) throw new EventNotFoundException("No Events found to retrieve");
        //return new ResponseEntity<>(eventsPayload, HttpStatus.OK);
        model.addAttribute("events",eventsPayload);
        return "events/viewevents";
    }


    @GetMapping(path = "/event/{eventId}")
    @ApiOperation(value = "Search an Event by the EventID")
    public ResponseEntity<EventsPayload> findByEventsId(@PathVariable("eventId") Integer eventId) {
        log.debug("getting event by id - {}", eventId);
        EventsPayload eventsPayload = eventService.findByEventsIdCustom(eventId);

        return new ResponseEntity<>(eventsPayload, HttpStatus.OK);
    }

    @RequestMapping("/event/form")
    public String addForm(Model model){
        model.addAttribute("command", new Events());
        return "events/eventform";
    }

    @PostMapping(path = "/save/event")
    @ApiOperation(value = "Create an Event by providing UserID, EventTypeID and EventDate")
    public String createEvent(@Valid @ModelAttribute("events") Events event) {
        log.debug("creating event" + event);
        if (eventService.exists(event)) throw new EventExistsException("Event already exists");
        eventService.createEvent(event);
        return "redirect:/events";
    }

    @RequestMapping("/edit/event/{eventId}")
    public String editForm(@PathVariable("eventId") Integer eventId, Model model){
        Events events = eventService.findByEventsId(eventId);
        model.addAttribute("command", events);
        return "events/editevent";
    }

    @PostMapping(path = "/update/event/{eventId}")
    @ApiOperation(value = "Update an Event by providing EventID, UserID, EventTypeID and EventDate")
    public String updateEvent(@Valid @ModelAttribute("events") Events events) {
        log.info("Updating event{}", events);
        /*
        Events events1 = eventService.findByEventsId(eventId);
        if (events1 != null) {
            events1.setEventId(eventId);
            events1.setUserId(events.getUserId());
            events1.setEventTypeId(events.getEventTypeId());
            events1.setEventDate(events.getEventDate());
        }
         */
        eventService.updateEvent(events);
        log.debug("update successful");
        return "redirect:/events";
    }

    @GetMapping(path = "/del/event/{eventId}")
    @ApiOperation(value = "Delete Event based on the EventID")
    public String deleteEvent(@PathVariable("eventId") Integer eventId) {
        log.info("Deleting Event with id{}", eventId);
        Events events = eventService.findByEventsId(eventId);
        if (events != null) {
            eventService.deleteEvent(eventId);
        }
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return "redirect:/events";
    }

}
