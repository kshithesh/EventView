package com.eventview.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "eventtypes")
public class EvenTypes {

	@Id
	@Column(name="event_type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventtypeid;

	@Column(name = "event_id")
	@NotNull
	private Integer eventid;

	@Column(name = "event_type")
	private String eventtype;

	public Integer getEventtypeid() {
		return eventtypeid;
	}

	public void setEventtypeid(Integer eventtypeid) {
		this.eventtypeid = eventtypeid;
	}

	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public EvenTypes(Integer eventtypeid, @NotNull Integer eventid, String eventtype) {
		this.eventtypeid = eventtypeid;
		this.eventid = eventid;
		this.eventtype = eventtype;
	}

	public EvenTypes() {
	}
}
