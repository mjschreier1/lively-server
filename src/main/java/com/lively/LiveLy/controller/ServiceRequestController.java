package com.lively.LiveLy.controller;

import com.lively.LiveLy.model.DeleteAllServiceRequestsResponse;
import com.lively.LiveLy.model.ServiceRequest;
import com.lively.LiveLy.repo.ServiceRequestRepository;
import com.lively.LiveLy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@CrossOrigin
@RestController
public class ServiceRequestController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/service")
    public ServiceRequest addServiceRequest(@RequestBody Map<String, String> body, HttpServletResponse response) {
        ServiceRequest serviceRequest = new ServiceRequest(
                userRepository.findById(Integer.parseInt(body.get("id"))),
                body.get("unit"),
                body.get("contact"),
                body.get("subject"),
                body.get("description"),
                true
                );
        serviceRequestRepository.save(serviceRequest);
        response.setStatus(201);
        return serviceRequest;
    }

    @DeleteMapping("/service/all")
    public DeleteAllServiceRequestsResponse deleteAllServiceRequests() {
        serviceRequestRepository.deleteAll();
        return new DeleteAllServiceRequestsResponse(200, "OK");
    }
}
