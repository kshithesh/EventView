package com.eventview.repo;

import java.util.List;

import com.eventview.model.EvenTypes;

public interface EventTypeRepo {

	List<EvenTypes> getAllEvenTypes();

	EvenTypes findByEventtypeId(Integer eventtypeid);

	void createEventType(EvenTypes evenTypes);

	void updateEventType(EvenTypes evenTypes);

	void deleteEventType(Integer eventtypeid);

}
