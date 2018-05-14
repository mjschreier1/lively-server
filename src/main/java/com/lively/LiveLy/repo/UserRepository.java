package com.lively.LiveLy.repo;

import com.lively.LiveLy.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findAllByLast(String last);
    User findById(long id);
}
