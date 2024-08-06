package com.example.Jwt_spring_security.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

            @GetMapping("/salut")
            public String Salut()
            {
                return  "Salut";
            }
}
