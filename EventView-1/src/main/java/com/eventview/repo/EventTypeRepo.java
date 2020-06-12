package com.eventview.repo;

import java.util.List;

import com.eventview.model.EventTypes;

public interface EventTypeRepo {

	List<EventTypes> getAllEvenTypes();

	EventTypes findByEventTypeId(Integer eventTypeId);

	void createEventType(EventTypes eventTypes);

	void updateEventType(EventTypes eventTypes);

	int deleteEventType(Integer eventTypeId);

	boolean eventTypeExists(Integer eventTypeId);
}
