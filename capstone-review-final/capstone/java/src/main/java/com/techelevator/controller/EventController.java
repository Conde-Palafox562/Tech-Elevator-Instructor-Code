package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EventController {

    @Autowired
    private EventDao eventDao;

    @RequestMapping(path="/events", method= RequestMethod.GET)
    public List<Event> getEvents(){
        return eventDao.getEvents();
    }

    @RequestMapping(path="/events/{id}", method=RequestMethod.GET)
    public Event getEvent(@PathVariable int id){
        return eventDao.getEvent(id);
    }

    @RequestMapping(path="/events", method=RequestMethod.POST)
    public Event createEvent(@RequestBody Event event){
        return eventDao.createEvent(event);
    }
}
