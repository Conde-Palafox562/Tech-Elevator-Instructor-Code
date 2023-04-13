package com.techelevator.dao;

import com.techelevator.model.Event;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Event createEvent(Event event){

        String sql = "INSERT INTO events(event_name, event_date, event_start, event_end, truck_id )\n" +
                "VALUES(?, ?, ?, ?, ?) RETURNING event_id;";

        int eventId = jdbcTemplate.queryForObject(sql, int.class, event.getEventName(),
                event.getEventDate(), event.getEventStart(), event.getEventEnd(),
                event.getTruckId());

        return getEvent(eventId);

    }

    public Event getEvent(int id) {

        Event event = null;

        String sql = "select event_id, event_name, event_date, event_start, event_end, truck_id, location_id \n" +
                "from events " +
                "WHERE event_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        while(results.next()){
            event = convertRowToEvent(results);
        }

        return event;
    }

    public List<Event> getEvents() {

        List<Event> events = new ArrayList<>();

        String sql = "select event_id, event_name, event_date, event_start, event_end, truck_id, location_id \n" +
                "from events;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()){
            Event event = convertRowToEvent(results);
            events.add(event);
        }

        return events;
    }

    private Event convertRowToEvent(SqlRowSet results) {
        Event event = new Event();
        event.setEventDate(results.getDate("event_date").toLocalDate());
        event.setEventEnd(results.getTimestamp("event_end").toLocalDateTime());
        event.setEventId(results.getInt("event_id"));
        event.setEventName(results.getString("event_name"));
        event.setEventStart(results.getTimestamp("event_start").toLocalDateTime());
        event.setLocationId(results.getInt("location_id"));
        event.setTruckId(results.getInt("truck_id"));
        return event;
    }

}
