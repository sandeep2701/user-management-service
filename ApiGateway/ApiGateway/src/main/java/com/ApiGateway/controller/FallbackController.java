package com.ApiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/loginServiceFalback")
    public String studentServiceFallback(){
        return "Student Service is down at this time !!";
        }

    @GetMapping("/registrationServiceFalback")
    public String contactServiceFalback(){
        return "Contact Service is down at this time !!";
    }
}
