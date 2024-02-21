package com.java.dockerplayground.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 
  
@SpringBootApplication
@RestController
public class HelloWorldController {
  
    @RequestMapping("/") 
    public String home() { 
        return "Exploring git actions";
    } 

}