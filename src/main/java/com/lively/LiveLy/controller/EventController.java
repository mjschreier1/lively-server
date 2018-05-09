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

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    public Iterable<Event> getEvents() {
        return eventRepository.findAll();
    }

    @PostMapping("/events")
    public String addEvent(@RequestBody Map<String, String> req) {
        Event event = new Event(req.get("start"), req.get("finish"), req.get("name"), req.get("location"));
        eventRepository.save(event);
        return "Success";
    }
}
