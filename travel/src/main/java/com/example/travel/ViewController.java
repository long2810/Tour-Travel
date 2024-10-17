package com.example.travel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class ViewController {
    @GetMapping("map")
    public String view(){
        return "map";
    }
    @GetMapping("message")
    public String message(){
        return "mes";
    }
    @GetMapping("admin/message")
    public String adminMessage(){
        return "mesAdmin";
    }
    @GetMapping("login")
    public String login(){
        return "user/login";
    }
    @GetMapping("register")
    public String register(){
        return "user/register";
    }
    @GetMapping("/tour/{pageId}")
    public String tourist(){
        return "/tourist/allTourist";
    }
}
