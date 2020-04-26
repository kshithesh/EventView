package com.eventview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.model.Events;

public class EventRowMapper implements RowMapper<Object> {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Events events = new Events();
		events.setEventid(rs.getInt("eventid"));
		events.setUserid(rs.getInt("userid"));
		events.setEventtypeid(rs.getInt("eventtypeid"));
		events.setEventdate(rs.getDate("eventdate"));
		return events;
	}

}
