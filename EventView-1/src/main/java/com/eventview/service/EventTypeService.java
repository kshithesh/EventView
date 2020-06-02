package com.eventview.service;

import com.eventview.model.EvenTypes;

import java.util.List;

public interface EventTypeService {

    List<EvenTypes> getAllEvenTypes();

    EvenTypes findByEventTypeId(Integer eventTypeId);

    void createEventType(EvenTypes evenTypes);

    void updateEventType(EvenTypes evenTypes);

    void deleteEventType(Integer eventTypeId);

    boolean exists(EvenTypes evenTypes);

}
