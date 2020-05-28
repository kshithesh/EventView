
package com.eventview.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Events {

	private Integer eventId;
	private Integer userId;
	private Integer eventTypeId;
	private Date eventDate;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Events(Integer eventId, @NotNull Integer userId, @NotNull Integer eventTypeId, @NotNull Date eventDate) {
		this.eventId = eventId;
		this.userId = userId;
		this.eventTypeId = eventTypeId;
		this.eventDate = eventDate;
	}

	public Events() {
	}
}

