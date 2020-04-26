package com.eventview.repo;

import com.eventview.dao.EventRowMapper;
import com.eventview.dao.EventsPayloadRowMapper;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("eventRepo")
public class EventRepoImpl implements EventRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EventsPayload> getAllEvens() {
        String esql = "select e.event_id,concat(u.first_name,' ',u.last_name) as full_name, et.event_type,e.event_date from events e left join users u on e.user_id = u.user_id left join eventtypes et on et.event_type_id=e.event_type_id";
        return jdbcTemplate.query(esql, new EventsPayloadRowMapper());
    }

    @Override
    public Events findByEventsId(Integer eventid) {
        String fbei = "select * from events where event_id = ?";
        return (Events) jdbcTemplate.queryForObject(fbei, new Object[] {eventid}, new EventRowMapper());
    }

    @Override
    public void createEvent(Events event) {
        String ce = "INSERT INTO events (event_id, user_id,event_type_id, event_date) VALUES (?,?,?,?)";
        jdbcTemplate.update(ce,event.getEventid(),event.getUserid(), event.getEventtypeid(), event.getEventdate());
    }

    @Override
    public void updateEvent(Events event) {
        jdbcTemplate.update("update events set user_id=?,event_type_id=?, event_date=? where event_id=?",
                event.getEventid());
    }

    @Override
    public void deleteEvent(Integer eventid) {
        jdbcTemplate.update("delete from events where event_id=?", new Object[]{eventid}, new EventRowMapper());
        System.out.println("Record with id:" + eventid + " are deleted");
    }
}
