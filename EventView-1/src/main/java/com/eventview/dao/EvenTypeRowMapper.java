package com.eventview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eventview.model.EventTypes;
import org.springframework.jdbc.core.RowMapper;

public class EvenTypeRowMapper implements RowMapper<EventTypes> {

	@Override
	public EventTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventTypes eventTypes = new EventTypes();
		eventTypes.setEventTypeId(rs.getInt("event_type_id"));
		eventTypes.setEventType(rs.getString("event_type"));
		return eventTypes;
	}

}
