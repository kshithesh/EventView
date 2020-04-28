package com.eventview.service;

import java.util.List;

import com.eventview.model.EvenTypes;

public interface EventTypeService {

	List<EvenTypes> getAllEvenTypes();

	EvenTypes findByEventtypeId(Integer eventtypeid);

	void createEventType(EvenTypes eventtype);

	void updateEventType(EvenTypes eventtype);

	void deleteEventType(Integer eventtypeid);

	boolean exists(EvenTypes eventtype);
}
