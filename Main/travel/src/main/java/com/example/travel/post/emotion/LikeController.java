package com.example.travel.post.emotion;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @PostMapping
    public LikeDto like(@RequestBody LikeDto dto){
        return likeService.create(dto);
    }

    @GetMapping
    public boolean checkLike(@RequestBody LikeDto dto){
        return likeService.existLike(dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long likeId){
        likeService.delete(likeId);
    }
}
