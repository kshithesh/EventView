package com.eventview.repo;

import com.eventview.dao.EvenTypeRowMapper;
import com.eventview.exceptions.EventTypeNotFoundException;
import com.eventview.model.EvenTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("eventTypeRepo")
public class EventTypeRepoImpl implements EventTypeRepo {

    private final Logger log = LoggerFactory.getLogger(EventRepoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EvenTypes> getAllEvenTypes() {
        String SELECT_ALL_EVENT_TYPE = "select event_type_id,event_type from eventtypes";
        return jdbcTemplate.query(SELECT_ALL_EVENT_TYPE, new EvenTypeRowMapper());
    }

    @Override
    public EvenTypes findByEventTypeId(Integer eventTypeId) {
        try {
            String SELECT_EVENT_TYPE_BY_ID = "select * from eventtypes where event_type_id = ?";
            EvenTypes evenTypes = jdbcTemplate.queryForObject(SELECT_EVENT_TYPE_BY_ID, new Object[]{eventTypeId}, new EvenTypeRowMapper());
            log.info("query generated " + SELECT_EVENT_TYPE_BY_ID + "-----" + eventTypeId);
			return evenTypes;
        } catch (Exception e) {
            throw new EventTypeNotFoundException("EventType not found with id: "+ eventTypeId);
        }
    }

    @Override
    public void createEventType(EvenTypes evenTypes) {
        String INSERT_EVENT_TYPE = "INSERT INTO eventtypes (event_type_id, event_type) VALUES (?,?)";
        jdbcTemplate.update(INSERT_EVENT_TYPE, evenTypes.getEventTypeId(), evenTypes.getEventType());
    }

    @Override
    public void updateEventType(EvenTypes evenTypes) {
        String UPDATE_EVENT_TYPE = "update eventtypes set event_type=? where event_type_id=?";
        jdbcTemplate.update(UPDATE_EVENT_TYPE, evenTypes.getEventType(), evenTypes.getEventTypeId());
    }

    @Override
    public int deleteEventType(Integer eventTypeId) {
        String DELETE_EVENT_TYPE = "delete from eventtypes where event_type_id=?";
        Object[] del = new Object[]{eventTypeId};
        int size = jdbcTemplate.update(DELETE_EVENT_TYPE, del);
        if (size == 0) {
            throw new EventTypeNotFoundException("No Event found to delete: " + eventTypeId);
        }
        return size;
    }
    @SuppressWarnings("ConstantConditions")
    public boolean eventTypeExists(Integer eventTypeId) {
        String EVENT_TYPE_EXISTS = "SELECT count(*) FROM eventtypes WHERE event_type_id = ?";
        int count = jdbcTemplate.queryForObject(EVENT_TYPE_EXISTS, new Object[]{ eventTypeId }, Integer.class);
        return count > 0;
    }
}
