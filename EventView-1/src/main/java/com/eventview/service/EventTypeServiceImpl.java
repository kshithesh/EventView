package com.eventview.service;

import java.util.List;

import com.eventview.model.EventTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventview.repo.EventTypeRepo;

@Service("eventTypeService")
public class EventTypeServiceImpl implements EventTypeService{

	@Autowired
	private EventTypeRepo eventTypeRepo;
	
	@Override
	public List<EventTypes> getAllEvenTypes(){
		return eventTypeRepo.getAllEvenTypes();
	}
	
	@Override
	public EventTypes findByEventTypeId(Integer eventTypeId) {
		return eventTypeRepo.findByEventTypeId(eventTypeId);
	}

	@Override
	public void createEventType(EventTypes eventTypes) {
		 eventTypeRepo.createEventType(eventTypes);
	}

	@Override
	public void updateEventType(EventTypes eventTypes) {
		eventTypeRepo.updateEventType(eventTypes);
	}

	@Override
	public void deleteEventType(Integer eventTypeId) {
		 eventTypeRepo.deleteEventType(eventTypeId);
	}

    @Override
    public boolean exists(EventTypes eventTypes) {
        return eventTypeRepo.eventTypeExists(eventTypes.getEventTypeId());
    }

}
