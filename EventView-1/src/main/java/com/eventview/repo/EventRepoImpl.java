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
    public List<EventsPayload> getAllEvens() {
        String sql1 = "select e.event_id,concat(u.first_name,' ',u.last_name) as full_name, et.event_type,e.event_date from events e left join users u on e.user_id = u.user_id left join eventtypes et on et.event_type_id=e.event_type_id";
        return jdbcTemplate.query(sql1, new EventsPayloadRowMapper());
    }

    @Override
    public List<Events> getAllEvents() {
        String sql2 = "select event_id,user_id,event_type_id,event_date from events";
        return jdbcTemplate.query(sql2, new EventRowMapper());
    }

    @Override
    public Events findByEventsId(Integer eventId) {
        Events events = null;
        try {
            String sql3 = "select * from events where event_id = ?";
            jdbcTemplate.queryForObject(sql3, new Object[]{eventId}, new EventRowMapper());
            log.info("query generated " + sql3 + "-----" + eventId);
        } catch (Exception e){
            throw new EventNotFoundException("Event not found with id: "+eventId);
        }
        return events;
    }

    @Override
    public void createEvent(Events event) {
        String sql4 = "INSERT INTO events (event_id, user_id,event_type_id, event_date) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql4, event.getEventId(), event.getUserId(), event.getEventTypeId(), event.getEventDate());
    }

    @Override
    public void updateEvent(Events event) {
        jdbcTemplate.update("update events set user_id=?,event_type_id=?, event_date=? where event_id=?",
                event.getUserId(), event.getEventTypeId(), event.getEventDate(), event.getEventId());
    }

    @Override
    public int deleteEvent(Integer eventId) {
        String del = "delete from events where event_id=?";
        int size = jdbcTemplate.update(del, new Object[]{eventId}, new EventRowMapper());
        if(size==0) {
            throw new EventNotFoundException("No Event found to delete: "+eventId);
        }
        return size;
    }
}
