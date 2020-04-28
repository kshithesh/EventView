package com.eventview.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "eventtypes")
public class EvenTypes {

	@Id
	@Column(name="event_type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventTypeId;

	@Column(name = "event_id")
	@NotNull
	private Integer eventid;

	@Column(name = "event_type")
	private String eventType;

	public Integer getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventId) {
		this.eventid = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public EvenTypes(Integer eventTypeId, @NotNull Integer eventId, String eventType) {
		this.eventTypeId = eventTypeId;
		this.eventid = eventId;
		this.eventType = eventType;
	}

	public EvenTypes() {
	}
}
