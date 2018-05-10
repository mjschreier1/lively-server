package com.lively.LiveLy.controller;

import com.lively.LiveLy.model.User;
import com.lively.LiveLy.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<User> getUser(@RequestParam("last") String last, @RequestParam("pin") String pin) {
        Iterable<User> lastNameMatches = userRepository.findAllByLast(last);
        long size = lastNameMatches.spliterator().getExactSizeIfKnown();
        if (size == 0) return new ResponseEntity(HttpStatus.NOT_FOUND);
        User targetUser;
        boolean recordFound = false;
        for (User user:lastNameMatches) {
            if (user.getPin() == Integer.parseInt(pin)) {
                targetUser = user;
                recordFound = true;
            }
        }
        if (recordFound) {
            return new ResponseEntity<User>(targetUser, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public String addUser(@RequestParam("admin") boolean admin, @RequestBody Map<String, String> payload) {
        User user = new User(payload.get("first"), payload.get("last"), Integer.parseInt(payload.get("pin")), admin, payload.get("email"));
        userRepository.save(user);
        return user.toString();
    }
}
