package com.lyn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/secu")
public class SecuController {


    @GetMapping("/test")
    public String hello(){
        return UUID.randomUUID().toString();
    }

}
