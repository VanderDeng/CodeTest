package com.vcg.zombie.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/test")
    public String hello() {
        return "test value is 123 ";
    }
}
