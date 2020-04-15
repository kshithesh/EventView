package com.eventview.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eventview.dao.EventRowMapper;
import com.eventview.dao.EventsPayloadRowMapper;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;

@Repository("eventRepo")
public class EventRepoImpl implements EventRepo{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<EventsPayload> getAllEvens() {
		String esql = "select e.event_id,concat(u.first_name,' ',u.last_name) as full_name, et.event_type,e.event_date from events e left join users u on e.user_id = u.user_id left join eventtypes et on et.event_type_id=e.event_type_id";
		List<EventsPayload> payload = jdbcTemplate.query(esql, new EventsPayloadRowMapper());
		return payload;
	}
	
	@Override
	public Object findByEventsId(Integer event_id) {
		String fbei = "select * from events where event_id = ?";
		return jdbcTemplate.queryForObject(fbei, new Object[] { event_id }, new EventRowMapper());
	}

	@Override
	public Events createEvent(Events event) {
		jdbcTemplate.update("INSERT INTO events (event_id, user_id,event_type_id, event_date) VALUES (?,?,?,?)", event.getUser_id(), event.getEvent_type_id(), event.getEvent_date());
		return event;
	}

	@Override
	public Events updateEvent(Events event) {
		jdbcTemplate.update("update events set user_id=?,event_type_id=?, event_date=? where event_id=?", event.getEvent_id());
		return event;
	}

	@Override
	public Events deleteEvent(Events event) {
		jdbcTemplate.update("delete from events where event_id=?", event.getEvent_id());
		return event;
	}
}
