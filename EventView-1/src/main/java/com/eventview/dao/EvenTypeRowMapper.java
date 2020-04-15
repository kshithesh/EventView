package com.eventview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.model.EvenTypes;

public class EvenTypeRowMapper implements RowMapper<EvenTypes> {

	@Override
	public EvenTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
		EvenTypes eventype = new EvenTypes();
		eventype.setEvent_id(rs.getInt("event_id"));
		eventype.setEvent_type_id(rs.getInt("event_type_id"));
		eventype.setEvent_type(rs.getString("event_type"));
		return eventype;
	}

}
