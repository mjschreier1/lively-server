package com.lively.LiveLy.controller;

import com.lively.LiveLy.model.Event;
import com.lively.LiveLy.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    public String getEvents() {
        return "Success";
    }
}
