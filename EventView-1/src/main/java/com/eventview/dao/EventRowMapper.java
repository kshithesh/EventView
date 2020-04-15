package com.eventview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.model.Events;

public class EventRowMapper implements RowMapper<Object> {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Events events = new Events();
		events.setEvent_id(rs.getInt("event_id"));
		events.setUser_id(rs.getInt("user_id"));
		events.setEvent_type_id(rs.getInt("event_type_id"));
		events.setEvent_date(rs.getString("event_date"));
		return events;
	}

}
