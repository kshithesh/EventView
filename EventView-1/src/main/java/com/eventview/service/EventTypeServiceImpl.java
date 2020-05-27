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
	public EvenTypes findByEventTypeId(Integer eventTypeId) {
		return eventTypeRepo.findByEventTypeId(eventTypeId);
	}

	@Override
	public void createEventType(EvenTypes evenTypes) {
		 eventTypeRepo.createEventType(evenTypes);
	}

	@Override
	public void updateEventType(EvenTypes evenTypes) {
		eventTypeRepo.updateEventType(evenTypes);
	}

	@Override
	public void deleteEventType(Integer eventTypeId) {
		 eventTypeRepo.deleteEventType(eventTypeId);
	}

    @Override
    public boolean exists(EvenTypes evenTypes) {
        return eventTypeRepo.eventTypeExists(evenTypes.getEventTypeId());
    }

	@Override
	public List<String> getEventTypeToday() {
		return eventTypeRepo.getEventTypeToday();
	}

	@Override
	public List<String> getEventTypeWeek() {
		return eventTypeRepo.getEventTypeWeek();
	}

}
