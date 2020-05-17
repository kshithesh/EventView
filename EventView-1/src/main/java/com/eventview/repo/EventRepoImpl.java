package com.eventview.repo;

import com.eventview.dao.EventRowMapper;
import com.eventview.dao.EventsPayloadRowMapper;
import com.eventview.exceptions.EventNotFoundException;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("eventRepo")
public class EventRepoImpl implements EventRepo {

    private final Logger log = LoggerFactory.getLogger(EventRepoImpl.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EventsPayload> getAllEvents() {
        String SELECT_ALL_EVENTS_CUS = "select e.event_id,concat(u.first_name,' ',u.last_name) as full_name, et.event_type,e.event_date from events e left join users u on e.user_id = u.user_id left join eventtypes et on et.event_type_id=e.event_type_id";
        return jdbcTemplate.query(SELECT_ALL_EVENTS_CUS, new EventsPayloadRowMapper());
    }

    @Override
    public List<Events> getAllEventsCustom() {
        String SELECT_ALL_EVENTS = "select event_id,user_id,event_type_id,event_date from events";
        return jdbcTemplate.query(SELECT_ALL_EVENTS, new EventRowMapper());
    }

    @Override
    public Events findByEventsId(Integer eventId) {
        String SELECT_EVENT_BYID = "select * from events where event_id = ?";
        try {
            Events events = jdbcTemplate.queryForObject(SELECT_EVENT_BYID, new Object[]{eventId}, new EventRowMapper());
            log.info("query generated " + SELECT_EVENT_BYID + "-----" + eventId);
            return events;
        } catch (Exception e) {
            throw new EventNotFoundException("Event not found with id: " + eventId);
        }
    }

    @Override
    public void createEvent(Events event) {
        String INSERT_EVENT = "INSERT INTO events (event_id, user_id,event_type_id, event_date) VALUES (?,?,?,?)";
        jdbcTemplate.update(INSERT_EVENT, event.getEventId(), event.getUserId(), event.getEventTypeId(), event.getEventDate());
    }

    @Override
    public void updateEvent(Events event) {
        String UPDATE_EVENT = "update events set user_id=?,event_type_id=?, event_date=? where event_id=?";
        jdbcTemplate.update(UPDATE_EVENT,
                event.getUserId(), event.getEventTypeId(), event.getEventDate(), event.getEventId());
    }

    @Override
    public int deleteEvent(Integer eventId) {
        String DELETE_EVENT = "delete from events where event_id=?";
        int size = jdbcTemplate.update(DELETE_EVENT, new Object[]{eventId}, new EventRowMapper());
        if(size==0) {
            throw new EventNotFoundException("No Event found to delete: "+eventId);
        }
        return size;
    }
}
