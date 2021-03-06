package com.lively.LiveLy.repo;

import com.lively.LiveLy.model.Event;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findById(long id);
    List<Event> findByFinishAfter(LocalDateTime now);
}
