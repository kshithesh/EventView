package com.eventview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventtypes")
public class EvenType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer event_type_id;
	private Integer event_id;
	private String event_type;

	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	public Integer getEvent_type_id() {
		return event_type_id;
	}

	public void setEvent_type_id(Integer event_type_id) {
		this.event_type_id = event_type_id;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

}
