package com.eventview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.model.EventsPayload;

public class EventsPayloadRowMapper implements RowMapper<EventsPayload> {

	@Override
	public EventsPayload mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventsPayload payload = new EventsPayload();
		payload.setEventId(rs.getInt("event_id"));
		payload.setFullName(rs.getString("full_name"));
		payload.setEventType(rs.getString("event_type"));
		payload.setEventDate(rs.getString("event_date"));
		return payload;
	}

}
