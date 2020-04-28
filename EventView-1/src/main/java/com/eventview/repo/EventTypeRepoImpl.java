package com.eventview.repo;

import com.eventview.dao.EvenTypeRowMapper;
import com.eventview.model.EvenTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
	public EvenTypes findByEventtypeId(Integer eventTypeId) {
		for (EvenTypes evenTypes : getAllEvenTypes()) {
			if (evenTypes.getEventTypeId().equals(eventTypeId)) {
				String fbeti = "select * from eventtypes where event_type_id = ?";
				return jdbcTemplate.queryForObject(fbeti, new Object[] {eventTypeId}, new EvenTypeRowMapper());
			}
		}
		return null;
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
	public void deleteEventType(Integer eventTypeId) {
		Object[] del = new Object[] {eventTypeId};
		jdbcTemplate.update("delete from eventtypes where event_type_id=?", del);
	}
}
