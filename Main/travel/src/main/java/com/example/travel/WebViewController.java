package com.example.travel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("travel")
public class WebViewController {
    @GetMapping("map")
    public String view(){
        return "map";
    }
    @GetMapping("message")
    public String message(){
        return "message/user/mesUser";
    }
    @GetMapping("login")
    public String login(){
        return "user/login";
    }
    @GetMapping("register")
    public String register(){
        return "user/register";
    }
    @GetMapping("/tour")
    public String tourist(){
        return "/tourist/allTourist";
    }
    @GetMapping("/package/{packageId}")
    public String tourPackage(){
        return "/tourPackage/package";
    }
    @GetMapping("booking")
    public String booking(){
        return "booking/user/booking";
    }
    @GetMapping("your-booking")
    public String allBooking(){
        return "booking/user/allBooking";
    }
}
