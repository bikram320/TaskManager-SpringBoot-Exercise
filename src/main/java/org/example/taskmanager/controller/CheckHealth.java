package org.example.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CheckHealth {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
