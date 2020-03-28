package com.eventview.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.demo.model.EvenType;

public class EvenTypeRowMapper implements RowMapper<EvenType> {

	@Override
	public EvenType mapRow(ResultSet rs, int rowNum) throws SQLException {
		EvenType eventype = new EvenType();
		eventype.setEvent_id(rs.getInt("event_id"));
		eventype.setEvent_type_id(rs.getInt("event_type_id"));
		eventype.setEvent_type(rs.getString("event_type"));
		return eventype;
	}

}
