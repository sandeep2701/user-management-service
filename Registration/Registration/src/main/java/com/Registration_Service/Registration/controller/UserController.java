package com.Registration_Service.Registration.controller;

import com.Registration_Service.Registration.entities.User;
import com.Registration_Service.Registration.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/home")
public class UserController {
    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/create-user")
    public ResponseEntity<?>    createUser(@RequestBody User user){
        kafkaService.updateUser(user);
        return new ResponseEntity<>(Map.of("message","user updated"),HttpStatus.OK);
    }
}
