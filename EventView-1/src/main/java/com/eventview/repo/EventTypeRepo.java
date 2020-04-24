package com.eventview.repo;

import java.util.List;

import com.eventview.model.EvenTypes;

public interface EventTypeRepo {

	List<EvenTypes> getAllEvenTypes();

	EvenTypes findByEventtypeId(Integer event_type_id);

	EvenTypes createEventType(EvenTypes evenTypes);

	void updateEventType(EvenTypes evenTypes);

	void deleteEventType(Integer event_type_id);

}
