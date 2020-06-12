package com.eventview.service;

import com.eventview.model.EventTypes;

import java.util.List;

public interface EventTypeService {

    List<EventTypes> getAllEvenTypes();

    EventTypes findByEventTypeId(Integer eventTypeId);

    void createEventType(EventTypes eventTypes);

    void updateEventType(EventTypes eventTypes);

    void deleteEventType(Integer eventTypeId);

    boolean exists(EventTypes eventTypes);

}
