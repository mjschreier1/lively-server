package com.lively.LiveLy.repo;

import com.lively.LiveLy.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
