package com.eventview.model;

import io.swagger.annotations.ApiModelProperty;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.Arrays;

public class EventsPayload {

	@ApiModelProperty(notes = "EventID from the Events Model")
    private Integer eventId;
	@ApiModelProperty(notes = "FirstName + LastName from the Users Model")
	private String fullName;
	@ApiModelProperty(notes = "Type of Event from the EventType Model")
    private String eventType;
	@ApiModelProperty(notes = "Date of the Event from the Events Model")
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

	public EventsPayload(Integer eventId, String firstName, String lastName, String eventType, String eventDate) {
		this.eventId = eventId;
		this.fullName = StringUtils.join(Arrays.asList(new String[]{firstName, lastName}), ' ');
		this.eventType = eventType;
		this.eventDate = eventDate;
	}

	public EventsPayload() {
	}
}
