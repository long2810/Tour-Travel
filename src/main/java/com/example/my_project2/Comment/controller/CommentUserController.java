package com.example.my_project2.Comment.controller;

import com.example.my_project2.Comment.dto.CommentDto;
import com.example.my_project2.Comment.service.CommentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posting/{post_id}/comment")

// this is comment controller for role of user is USER + ADMIN
public class CommentUserController {
     private final CommentUserService userService;

    @PostMapping("/create")
    public CommentDto create(
            @PathVariable("post_id")
            Long posting_id,
            @RequestBody
            String content
    ) {
        return userService.createComment(posting_id,content);
    }

    @GetMapping("{comment_order}/update")
    public CommentDto update(
            @PathVariable("post_id")
            Long post_id,
            @PathVariable("comment_order")
            int comment_order,
            @RequestBody
            String content
    ) {
        return userService.update(post_id,comment_order,content);
    }

    @GetMapping("{id}/delete")
    public void delete(
            @PathVariable("id")
            Long id
    ) {
        userService.delete(id);
    }
}

