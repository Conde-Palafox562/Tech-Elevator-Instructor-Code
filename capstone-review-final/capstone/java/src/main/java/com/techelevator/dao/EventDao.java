package com.techelevator.dao;

import com.techelevator.model.Event;

import java.util.List;

public interface EventDao {
    public Event createEvent(Event event);
    public Event getEvent(int id);
    public List<Event> getEvents();
}
