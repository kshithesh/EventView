package com.eventview.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class EvenTypes {

	@ApiModelProperty(notes = "The database generated EventTypeID")
	private Integer eventTypeId;

	@NotNull(message = "EventType cannot be empty")
	@ApiModelProperty(notes = "Type of Event")
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
