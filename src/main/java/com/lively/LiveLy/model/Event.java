package com.lively.LiveLy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = 1;

    @Column(name = "start")
    private String start;

    @Column(name = "finish")
    private String finish;

    @Column(name = "name")
    private String name;

    @Column
    private String location;

    protected Event() {

    }

    public Event(String start, String finish, String name, String location) {
        this.start = start;
        this.finish = finish;
        this.name = name;
        this.location = location;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", start=" + start +
                ", finish=" + finish +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
