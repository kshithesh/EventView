package com.eventview.model;

public class EventsPayload {

    private Integer eventId;
	private String fullName;
    private String eventType;
    private String eventDate;


	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public EventsPayload(Integer eventId, String fName, String lName, String eventType, String eventDate) {
		this.eventId = eventId;
		this.fullName = fName + " " + lName;
		this.eventType = eventType;
		this.eventDate = eventDate;
	}

	public EventsPayload() {
	}
}
