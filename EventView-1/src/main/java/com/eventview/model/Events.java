
package com.eventview.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name="Events")
@Table(name = "events")
public class Events {

	@Id
	@Column(name="event_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventid;

	@Column(name="user_id")
	@NotNull
	private Integer userid;

	@Column(name="event_type_id")
	@NotNull
	private Integer eventtypeid;

	@Column(name="event_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date eventdate;

	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getEventtypeid() {
		return eventtypeid;
	}

	public void setEventtypeid(Integer eventtypeid) {
		this.eventtypeid = eventtypeid;
	}

	public Date getEventdate() {
		return eventdate;
	}

	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}

	public Events(Integer eventid, @NotNull Integer userid, @NotNull Integer eventtypeid, @NotNull Date eventdate) {
		this.eventid = eventid;
		this.userid = userid;
		this.eventtypeid = eventtypeid;
		this.eventdate = eventdate;
	}

	public Events() {
	}
}

