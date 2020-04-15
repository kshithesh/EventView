package com.eventview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventview.model.EvenTypes;
import com.eventview.repo.EventTypeRepo;

@Service("eventTypeService")
public class EventTypeServiceImpl implements EventTypeService{

	@Autowired
	private EventTypeRepo eventTypeRepo;
	
	@Override
	public List<EvenTypes> getAllEvenTypes(){
		return eventTypeRepo.getAllEvenTypes();
	}
	
	@Override
	public EvenTypes findByEventtypeId(Integer event_type_id) {
		return eventTypeRepo.findByEventtypeId(event_type_id);
	}

	@Override
	public EvenTypes createEventType(EvenTypes eventtype) {
		return eventTypeRepo.createEventType(eventtype);
	}

	@Override
	public EvenTypes updateEventType(EvenTypes eventtype) {
		return eventTypeRepo.updateEventType(eventtype);

	}

	@Override
	public EvenTypes deleteEventType(EvenTypes eventtype) {
		return eventTypeRepo.deleteEventType(eventtype);
	}
}
