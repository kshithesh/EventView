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
	public EvenTypes findByEventtypeId(Integer eventtypeid) {
		return eventTypeRepo.findByEventtypeId(eventtypeid);
	}

	@Override
	public void createEventType(EvenTypes eventtype) {
		 eventTypeRepo.createEventType(eventtype);
	}

	@Override
	public void updateEventType(EvenTypes evenTypes) {
		eventTypeRepo.updateEventType(evenTypes);
	}

	@Override
	public void deleteEventType(Integer eventtypeid) {
		 eventTypeRepo.deleteEventType(eventtypeid);
	}

    @Override
    public boolean exists(EvenTypes eventtype) {
        return eventTypeRepo.findByEventtypeId(eventtype.getEventTypeId()) !=null;
    }
}
