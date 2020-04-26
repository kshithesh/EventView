package com.eventview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.model.EventsPayload;

public class EventsPayloadRowMapper implements RowMapper<EventsPayload> {

	@Override
	public EventsPayload mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventsPayload payload = new EventsPayload();
		payload.setEventid(rs.getInt("eventid"));
		payload.setFname(rs.getString("fullname"));
		payload.setEventtype(rs.getString("eventtype"));
		payload.setEventdate(rs.getString("eventdate"));
		return payload;
	}

}
