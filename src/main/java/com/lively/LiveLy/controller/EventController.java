package com.lively.LiveLy.controller;

import com.lively.LiveLy.model.Event;
import com.lively.LiveLy.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.*;

@RestController
public class EventController {

    int idCounter = 1;
    final Map<Long, Event> map = new HashMap<>();

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    public Collection<Event> getEvents() {
        return map.values();
    }

    @PostMapping("/events")
    public Event addEvent(@RequestBody Map<String, String> req) {
        Calendar start = Calendar.getInstance();
        start.set(Calendar.YEAR, Integer.parseInt(req.get("start")));
        start.set(Calendar.MONTH, Calendar.MAY);
        start.set(Calendar.DAY_OF_MONTH, 20);
        Calendar end = Calendar.getInstance();
        end.set(Calendar.YEAR, Integer.parseInt(req.get("end")));
        end.set(Calendar.MONTH, Calendar.MAY);
        end.set(Calendar.DAY_OF_MONTH, 20);
        Event event = new Event(start.getTime(), end.getTime(), req.get("name"), req.get("location"));
        event.setId(idCounter++);
        map.put(event.getId(), event);
        return event;
    }
}
