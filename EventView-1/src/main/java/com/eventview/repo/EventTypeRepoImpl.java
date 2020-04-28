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
		return jdbcTemplate.query(etsql, new EvenTypeRowMapper());
	}

	@Override
	public EvenTypes findByEventtypeId(Integer eventtypeid) {
		String fbeti = "select * from eventtypes where event_type_id = ?";
		return jdbcTemplate.queryForObject(fbeti, new Object[] { eventtypeid }, new EvenTypeRowMapper());
	}

	@Override
	public void createEventType(EvenTypes evenTypes) {
		jdbcTemplate.update(
				"INSERT INTO eventtypes (event_type_id, event_id, event_type) VALUES (?,?,?)", evenTypes.getEventTypeId(), evenTypes.getEventid(), evenTypes.getEventType());
	}

	@Override
	public void updateEventType(EvenTypes evenTypes) {
		jdbcTemplate.update("update eventtypes set event_id=?, event_type=? where event_type_id=?", evenTypes.getEventid(), evenTypes.getEventType(), evenTypes.getEventTypeId());
	}

	@Override
	public void deleteEventType(Integer eventtypeid) {
		Object[] del = new Object[] {eventtypeid};
		jdbcTemplate.update("delete from eventtypes where event_type_id=?", del);
	}
}
