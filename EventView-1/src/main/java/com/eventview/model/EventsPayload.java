package com.eventview.model;

import java.util.Date;

public class EventsPayload {

    private Integer eventid;
    private String fname;
    private String eventtype;
    private String eventdate;

	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fullname) {
		this.fname = fullname;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public String getEventdate() {
		return eventdate;
	}

	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}

	public EventsPayload(Integer eventid, String fullname, String eventtype, String eventdate) {
		this.eventid = eventid;
		this.fname = fullname;
		this.eventtype = eventtype;
		this.eventdate = eventdate;
	}

	public EventsPayload() {
	}
}
