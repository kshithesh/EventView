package com.eventview.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.demo.model.EventsPayload;

public class EventsPayloadRowMapper implements RowMapper<EventsPayload> {

	@Override
	public EventsPayload mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventsPayload payload = new EventsPayload();
		payload.setEvent_id(rs.getInt("event_id"));
		payload.setFull_name(rs.getString("full_name"));
		payload.setEvent_type(rs.getString("event_type"));
		payload.setEvent_date(rs.getString("event_date"));
		return payload;
	}

}
