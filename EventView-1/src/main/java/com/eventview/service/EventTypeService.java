package com.eventview.service;

import java.util.List;

import com.eventview.model.EvenTypes;

public interface EventTypeService {

	List<EvenTypes> getAllEvenTypes();

	EvenTypes findByEventtypeId(Integer event_type_id);

	EvenTypes createEventType(EvenTypes eventtype);

	EvenTypes updateEventType(EvenTypes eventtype);

	EvenTypes deleteEventType(EvenTypes eventtype);


}
