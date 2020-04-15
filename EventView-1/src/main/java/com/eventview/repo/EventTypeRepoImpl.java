package com.eventview.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eventview.dao.EvenTypeRowMapper;
import com.eventview.model.EvenTypes;

@Repository("eventTypeRepo")
public class EventTypeRepoImpl implements EventTypeRepo{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<EvenTypes> getAllEvenTypes() {
		String etsql = "select event_type_id,event_id,event_type from eventtypes";
		List<EvenTypes> evens = jdbcTemplate.query(etsql, new EvenTypeRowMapper());
		return evens;
	}

	@Override
	public EvenTypes findByEventtypeId(Integer event_type_id) {
		String fbeti = "select * from eventtypes where event_type_id = ?";
		return jdbcTemplate.queryForObject(fbeti, new Object[] { event_type_id }, new EvenTypeRowMapper());
	}

	@Override
	public EvenTypes createEventType(EvenTypes eventtype) {
		jdbcTemplate.update(
				"INSERT INTO eventtypes (event_type_id, event_id, event_type) VALUES (?,?,?)", eventtype.getEvent_type_id(), eventtype.getEvent_id(), eventtype.getEvent_type());
		return eventtype;
	}

	@Override
	public EvenTypes updateEventType(EvenTypes eventtype) {
		jdbcTemplate.update("update eventtypes set event_id=?, event_type=? where event_type_id=?", eventtype.getEvent_id(), eventtype.getEvent_type(), eventtype.getEvent_type_id());
		return eventtype;
	}

	@Override
	public EvenTypes deleteEventType(EvenTypes eventtype) {
		jdbcTemplate.update("delete from eventtypes where event_type_id=?", eventtype.getEvent_type_id());
		return eventtype;
	}
}
