package com.example.my_project2.Comment.controller;

import com.example.my_project2.Comment.service.CommentViewService;
import com.example.my_project2.Comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posting/{post_id}/commentView")
// this is comment controller for role of user is VIEWER + USER + ADMIN
public class CommentViewController {
    private final CommentViewService commentService;

    @GetMapping
    public List<CommentDto> readListComment(
            @PathVariable("post_id")
            Long posting_id
    ) {
        return commentService.readListOfOnePost(posting_id);
    }


    @GetMapping("{comment_order}")
    public CommentDto readOne(
            @PathVariable("post_id")
            Long post_id,
            @PathVariable("comment_order")
            int comment_order
    ) {
        return commentService.readOne(post_id,comment_order);
    }

}
