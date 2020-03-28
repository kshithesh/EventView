package com.eventview.model;

public class EventsPayload {

	private Integer event_id;
	private String full_name;
	private String event_type;
	private String event_date;

	public EventsPayload() {

	}

	public EventsPayload(Integer event_id, String full_name, String event_type_name, String event_date,
			String event_type) {
		super();
		this.event_id = event_id;
		this.full_name = full_name;
		this.event_type = event_type;
		this.event_date = event_date;
	}

	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

}
