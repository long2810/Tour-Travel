package com.example.my_project2.posting.controller;

import com.example.my_project2.posting.dto.PostingDto;
import com.example.my_project2.posting.service.PostingViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("postingView")
public class PostingViewController {
    private final PostingViewService noAuthService;

    @GetMapping("/{id}")
    public PostingDto readOne(
            @PathVariable("id")
            Long id
    ) {
        return noAuthService.readPosting(id);
    }

    @GetMapping("/postList")
    public List<PostingDto> readAllPosting () {
        return noAuthService.readPostingList();
    }

    @GetMapping("/search/{userId}")
    public List<PostingDto> searchByUser(
            @PathVariable("userId")
            Long userId
    ) {
        return noAuthService.searchPostByUserId(userId);
    }

    @GetMapping("/searchTitle")
    public List<PostingDto> searchByTitle(
            @RequestParam
            String title
    ) {
        return noAuthService.searchPostByTitle(title);
    }
}
