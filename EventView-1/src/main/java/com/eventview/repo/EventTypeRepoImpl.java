package com.eventview.repo;

import com.eventview.dao.EvenTypeRowMapper;
import com.eventview.exceptions.EventNotFoundException;
import com.eventview.exceptions.EventTypeNotFoundException;
import com.eventview.model.EvenTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("eventTypeRepo")
public class EventTypeRepoImpl implements EventTypeRepo{

	private final Logger log = LoggerFactory.getLogger(EventRepoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<EvenTypes> getAllEvenTypes() {
		String sql = "select event_type_id,event_id,event_type from eventtypes";
		return jdbcTemplate.query(sql, new EvenTypeRowMapper());
	}

	@Override
	public EvenTypes findByEventTypeId(Integer eventTypeId) {
		EvenTypes evenTypes = null;
		try{
				String sql = "select * from eventtypes where event_type_id = ?";
				jdbcTemplate.queryForObject(sql, new Object[] {eventTypeId}, new EvenTypeRowMapper());
				log.info("query generated " + sql + "-----" + eventTypeId);
			} catch (Exception e){
			throw new EventTypeNotFoundException("EventType not found");
		}
		return evenTypes;
	}

	@Override
	public void createEventType(EvenTypes evenTypes) {
		String sql = "INSERT INTO eventtypes (event_type_id, event_id, event_type) VALUES (?,?,?)";
		jdbcTemplate.update(sql, evenTypes.getEventTypeId(), evenTypes.getEventid(), evenTypes.getEventType());
	}

	@Override
	public void updateEventType(EvenTypes evenTypes) {
		String sql = "update eventtypes set event_id=?, event_type=? where event_type_id=?";
		jdbcTemplate.update(sql, evenTypes.getEventid(), evenTypes.getEventType(), evenTypes.getEventTypeId());
	}

	@Override
	public int deleteEventType(Integer eventTypeId) {
		String sql = "delete from eventtypes where event_type_id=?";
		Object[] del = new Object[] {eventTypeId};
		int size = jdbcTemplate.update(sql, del);
		if(size==0) {
			throw new EventNotFoundException("No Event found to delete: "+eventTypeId);
		}
		return size;
	}
}
