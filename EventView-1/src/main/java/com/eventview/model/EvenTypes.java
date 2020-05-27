package com.eventview.model;

import javax.persistence.*;

@Entity
@Table(name = "eventtypes")
public class EvenTypes {

	@Id
	@Column(name="event_type_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eventTypeId;

	@Column(name = "event_type")
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
