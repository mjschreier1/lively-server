package com.lively.LiveLy.repo;

import com.lively.LiveLy.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
