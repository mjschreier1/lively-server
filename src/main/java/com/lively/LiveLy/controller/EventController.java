package com.lively.LiveLy.controller;

import com.lively.LiveLy.model.Event;
import com.lively.LiveLy.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

@CrossOrigin
@RestController
public class EventController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    public Iterable<Event> getEvents() {
        return eventRepository.findAll();
    }

    @PostMapping("/events")
    public Event addEvent(
            @RequestBody Map<String, String> details,
            @RequestParam("startYear") int startYear,
            @RequestParam("startMonth") int startMonth,
            @RequestParam("startDate") int startDate,
            @RequestParam("startHour") int startHour,
            @RequestParam("startMinute") int startMinute,
            @RequestParam("finishYear") int finishYear,
            @RequestParam("finishMonth") int finishMonth,
            @RequestParam("finishDate") int finishDate,
            @RequestParam("finishHour") int finishHour,
            @RequestParam("finishMinute") int finishMinute) {
        LocalDateTime start = LocalDateTime.of(startYear, startMonth, startDate, startHour, startMinute);
        LocalDateTime finish = LocalDateTime.of(finishYear, finishMonth, finishDate, finishHour, finishMinute);

        Event event = new Event(start, finish, details.get("name"), details.get("location"), details.get("description"));
        eventRepository.save(event);
        return event;
    }

}
