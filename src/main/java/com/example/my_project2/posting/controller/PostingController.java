package com.example.my_project2.posting.controller;

import com.example.my_project2.posting.dto.PostingDto;
import com.example.my_project2.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("posting")
public class PostingController {
    private final PostingService postingService;

    @PostMapping
    public PostingDto create(
            @RequestBody
            PostingDto dto
    ) {
        log.info(dto.getTitle());
        return postingService.createPost(dto);
    }


    @PutMapping("/update/{id}")
    public PostingDto update(
            @PathVariable("id")
            Long id,
            @RequestBody
            PostingDto dto
    ) {
        return postingService.updatePost(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void postDelete(
            @PathVariable("id")
            Long id
    ) {
        postingService.delete(id);
    }

    @PutMapping("/updateImage/{id}")
    public PostingDto updateImage(
            @PathVariable("id")
            Long id,
            @RequestParam("image")
            MultipartFile image
    ) {
        return postingService.updatePostingImg(id,image);
    }
}
