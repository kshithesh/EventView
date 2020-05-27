package com.eventview.service;

import java.util.List;

import com.eventview.model.EvenTypes;

public interface EventTypeService {

	List<EvenTypes> getAllEvenTypes();

	EvenTypes findByEventTypeId(Integer eventTypeId);

	void createEventType(EvenTypes evenTypes);

	void updateEventType(EvenTypes evenTypes);

	void deleteEventType(Integer eventTypeId);

	boolean exists(EvenTypes evenTypes);

	List<String> getEventTypeToday();

	List<String> getEventTypeWeek();
}
