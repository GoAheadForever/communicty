package com.mobai.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;

@Controller
public class ThymeleafDemoController {
    @GetMapping("/hello")
    public String getName(@PathParam("name") String name, Model mode){
        mode.addAttribute("name",name);
        return "hello";
    }
}
