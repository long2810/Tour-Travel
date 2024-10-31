package com.example.travel;

import com.example.travel.post.posting.service.PostingViewerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("travel")
public class WebViewController {

    @GetMapping("home")
    public String home() {return "home/home";}
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
    @GetMapping("post-create")
    public String postCreate1(Long postId) {return "post/post-create";}
    @GetMapping("/post/{postId}")
    public String readOnePost(@PathVariable("postId") Long postId) {return "post/post";}
    @GetMapping("/post")
    public String readAllPost() { return "post/postingList";}
}

