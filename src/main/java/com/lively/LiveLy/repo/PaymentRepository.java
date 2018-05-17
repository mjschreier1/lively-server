package com.lively.LiveLy.repo;

import com.lively.LiveLy.model.Payment;
import com.lively.LiveLy.model.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
    Iterable<Payment> findBySubmittedOnBetween(LocalDateTime min, LocalDateTime max);
    Iterable<Payment> findByUser(User user);
}
