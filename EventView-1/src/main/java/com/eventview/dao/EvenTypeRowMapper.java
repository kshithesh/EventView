package com.eventview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.eventview.model.EvenTypes;

public class EvenTypeRowMapper implements RowMapper<EvenTypes> {

	@Override
	public EvenTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
		EvenTypes evenTypes = new EvenTypes();
		evenTypes.setEventid(rs.getInt("event_id"));
		evenTypes.setEventtypeid(rs.getInt("event_type_id"));
		evenTypes.setEventtype(rs.getString("event_type"));
		return evenTypes;
	}

}
