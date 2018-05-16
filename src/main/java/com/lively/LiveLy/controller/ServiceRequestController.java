package com.lively.LiveLy.controller;

import com.lively.LiveLy.model.DeleteAllServiceRequestsResponse;
import com.lively.LiveLy.model.ServiceRequest;
import com.lively.LiveLy.model.User;
import com.lively.LiveLy.repo.ServiceRequestRepository;
import com.lively.LiveLy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
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

    @GetMapping("/service/{user_id}")
    public Iterable<ServiceRequest> getServiceRequestsByUser(@PathVariable("user_id") long id) {
        Iterable<ServiceRequest> response = serviceRequestRepository.findByUser(userRepository.findById(id));
        // remove user PIN from response
        for(ServiceRequest serviceRequest:response) {
            serviceRequest.setUser(new User(
                    serviceRequest.getUser().getFirst(),
                    serviceRequest.getUser().getLast(),
                    0,
                    serviceRequest.getUser().isAdmin(),
                    serviceRequest.getUser().getEmail()
            ));
        }
        return response;
    }

    @GetMapping("/service/open")
    public Iterable<ServiceRequest> getOpenServiceRequests() {
        Iterable<ServiceRequest> response = serviceRequestRepository.findByOpen(true);
        // remove user PIN from response
        for(ServiceRequest serviceRequest:response) {
            serviceRequest.setUser(new User(
                    serviceRequest.getUser().getFirst(),
                    serviceRequest.getUser().getLast(),
                    0,
                    serviceRequest.getUser().isAdmin(),
                    serviceRequest.getUser().getEmail()
            ));
        }
        return response;
    }

    @PostMapping("/service")
    public ServiceRequest addServiceRequest(@RequestBody Map<String, String> body, HttpServletResponse http) {
        ServiceRequest serviceRequest = new ServiceRequest(
                userRepository.findById(Integer.parseInt(body.get("userId"))),
                body.get("unit"),
                body.get("contact"),
                body.get("subject"),
                body.get("description"),
                true
                );
        serviceRequestRepository.save(serviceRequest);
        http.setStatus(201);
        // remove user PIN from response
        serviceRequest.setUser(new User(
                serviceRequest.getUser().getFirst(),
                serviceRequest.getUser().getLast(),
                0,
                serviceRequest.getUser().isAdmin(),
                serviceRequest.getUser().getEmail()
        ));
        return serviceRequest;
    }

    @PostMapping("/service/admin")
    public ServiceRequest addServiceRequestForResident(@RequestBody Map<String, String> body, HttpServletResponse http) {
        ServiceRequest serviceRequest = new ServiceRequest(
                userRepository.findByLastAndEmail(body.get("last"), body.get("email")),
                body.get("unit"),
                body.get("contact"),
                body.get("subject"),
                body.get("description"),
                true
        );
        serviceRequestRepository.save(serviceRequest);
        http.setStatus(201);
        // remove user PIN from response
        serviceRequest.setUser(new User(
                serviceRequest.getUser().getFirst(),
                serviceRequest.getUser().getLast(),
                0,
                serviceRequest.getUser().isAdmin(),
                serviceRequest.getUser().getEmail()
        ));
        return serviceRequest;
    }

    @PutMapping("/service")
    public ServiceRequest updateServiceRequest(@RequestBody Map<String, String> body) {
        System.out.println(body);
        ServiceRequest serviceRequest = serviceRequestRepository.findById(Integer.parseInt(body.get("id")));
        serviceRequest.setAdmin_notes(body.get("admin_notes"));
        serviceRequest.setOpen(Boolean.parseBoolean(body.get("open")));
        serviceRequestRepository.save(serviceRequest);
        // remove user PIN from response
        serviceRequest.setUser(new User(
                serviceRequest.getUser().getFirst(),
                serviceRequest.getUser().getLast(),
                0,
                serviceRequest.getUser().isAdmin(),
                serviceRequest.getUser().getEmail()
        ));
        return serviceRequest;
    }

    @DeleteMapping("/service/all")
    public DeleteAllServiceRequestsResponse deleteAllServiceRequests() {
        serviceRequestRepository.deleteAll();
        return new DeleteAllServiceRequestsResponse(200, "OK");
    }
}
