package com.eventview.model;

public class EvenTypes {

	private Integer eventTypeId;
	private String eventType;

	public Integer getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public EvenTypes(Integer eventTypeId, String eventType) {
		this.eventTypeId = eventTypeId;
		this.eventType = eventType;
	}

	public EvenTypes() {
	}
}
