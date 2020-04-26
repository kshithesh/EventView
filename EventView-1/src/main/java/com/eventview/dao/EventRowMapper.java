package com.eventview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.model.Events;

public class EventRowMapper implements RowMapper<Object> {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Events events = new Events();
		events.setEventId(rs.getInt("event_id"));
		events.setUserId(rs.getInt("user_id"));
		events.setEventTypeId(rs.getInt("event_type_id"));
		events.setEventdate(rs.getDate("event_date"));
		return events;
	}

}
