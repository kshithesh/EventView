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

	public void setEventTypeId(Integer eventtypeid) {
		this.eventTypeId = eventtypeid;
	}

	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventtype) {
		this.eventType = eventtype;
	}

	public EvenTypes(Integer eventTypeId, @NotNull Integer eventid, String eventType) {
		this.eventTypeId = eventTypeId;
		this.eventid = eventid;
		this.eventType = eventType;
	}

	public EvenTypes() {
	}
}
