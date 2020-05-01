package com.eventview.repo;

import java.util.List;

import com.eventview.model.EvenTypes;

public interface EventTypeRepo {

	List<EvenTypes> getAllEvenTypes();

	EvenTypes findByEventTypeId(Integer eventTypeId);

	void createEventType(EvenTypes evenTypes);

	void updateEventType(EvenTypes evenTypes);

	int deleteEventType(Integer eventTypeId);

}
