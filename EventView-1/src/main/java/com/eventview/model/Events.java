
package com.eventview.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name="Events")
@Table(name = "events")
public class Events {

	@Id
	@Column(name="event_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventId;

	@Column(name="user_id")
	@NotNull
	private Integer userId;

	@Column(name="event_type_id")
	@NotNull
	private Integer eventTypeId;

	@Column(name="event_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@NotNull
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

