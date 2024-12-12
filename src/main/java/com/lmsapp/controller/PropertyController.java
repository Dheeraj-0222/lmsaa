package com.lmsapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    @PostMapping("/addProperty")
    public String addProperty(){
        return "property";
    }
}
