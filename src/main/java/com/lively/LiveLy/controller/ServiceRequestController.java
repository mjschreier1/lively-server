package com.lively.LiveLy.controller;

import com.lively.LiveLy.model.DeleteAllServiceRequestsResponse;
import com.lively.LiveLy.repo.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ServiceRequestController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @DeleteMapping("/service/all")
    public DeleteAllServiceRequestsResponse deleteAllServiceRequests() {
        serviceRequestRepository.deleteAll();
        return new DeleteAllServiceRequestsResponse(200, "OK");
    }
}
