package com.example.travel.post.posting.service;

import com.example.travel.authentication.component.UserComponent;
import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.post.posting.dto.PostingDto;
import com.example.travel.post.posting.entity.ImagePosting;
import com.example.travel.post.posting.entity.Posting;
import com.example.travel.post.posting.repo.ImagePostRepo;
import com.example.travel.post.posting.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostingService {
    private final PostRepo postRepo;
    private final ImagePostRepo imageRepo;
    private final UserComponent userComponent;


    //create
    public PostingDto create(PostingDto dto) {
        UserEntity writer = userComponent.userLogin();
        ImagePosting image1 = imageRepo.findById(1L).orElseThrow();
        ImagePosting image2 = imageRepo.findById(2L).orElseThrow();
        ImagePosting image3 = imageRepo.findById(3L).orElseThrow();
        ImagePosting image4 = imageRepo.findById(4L).orElseThrow();
        Posting posting = Posting.builder()
                .title(dto.getTitle())
                .writer(writer)
                .content(dto.getContent())
                .build();
        posting.getImages().add(image1);
        posting.getImages().add(image2);
        posting.getImages().add(image3);
        posting.getImages().add(image4);
        return PostingDto.fromEntity(postRepo.save(posting));
    }

    //update post
    public PostingDto update(Long postId, PostingDto dto) {
        UserEntity writer = userComponent.userLogin();
        Posting posting = postRepo.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("No exist posting!")
        );
        if (!posting.getWriter().getId().equals(writer.getId()))
            throw new IllegalArgumentException("This posting is not available!!!");
        posting.setTitle(dto.getTitle());
        posting.setContent(dto.getContent());
        return PostingDto.fromEntity(postRepo.save(posting));
    }
    //delete post
    public void delete(Long postId) {
        UserEntity writer = userComponent.userLogin();
        Posting posting = postRepo.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("No exist posting!")
        );
        if (!posting.getWriter().getId().equals(writer.getId()))
            throw new IllegalArgumentException("This posting is not available!!!");
        postRepo.delete(posting);
    }

}
