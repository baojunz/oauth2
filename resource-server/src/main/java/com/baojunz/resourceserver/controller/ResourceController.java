package com.baojunz.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/resource")
    public String showResource(){
        return "hahahhahah";
    }
}
