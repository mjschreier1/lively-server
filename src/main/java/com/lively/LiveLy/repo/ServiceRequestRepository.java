package com.lively.LiveLy.repo;

import com.lively.LiveLy.model.ServiceRequest;
import com.lively.LiveLy.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRequestRepository extends CrudRepository<ServiceRequest, Long> {
    Iterable<ServiceRequest> findByUser(User user);
    Iterable<ServiceRequest> findByOpen(boolean open);
}
