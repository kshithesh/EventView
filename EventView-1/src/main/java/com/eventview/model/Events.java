
package com.eventview.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Events {

	@ApiModelProperty(notes = "The database generated EventID")
	private Integer eventId;

	@NotNull(message = "UserId cannot be empty")
	@ApiModelProperty(notes = "UserID from the Users Model")
	private Integer userId;

	@NotNull(message = "EventTypeId cannot be empty")
	@ApiModelProperty(notes = "EventTypeID from the EventTypes Model")
	private Integer eventTypeId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "EventDate cannot be empty")
	@ApiModelProperty(notes = "Date of the Event")
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

