package com.example.my_project2.posting;

import com.example.my_project2.posting.entity.Posting;
import com.example.my_project2.posting.repo.PostRepository;
import com.example.my_project2.user.entity.UserEntity;
import com.example.my_project2.user.repo.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class PostTestData {
    private final PostRepository postRepository;
    private final UserRepo userRepo;

    public PostTestData(
            PostRepository postRepository,
            UserRepo userRepo
    ) {
        this.postRepository = postRepository;
        this.userRepo = userRepo;
        this.fixPost();
    }

    public void fixPost() {
        UserEntity user1 = userRepo.findById(1L)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));
        UserEntity user2 = userRepo.findById(2L)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));
        UserEntity user3 = userRepo.findById(3L)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));

        Posting posting1 = Posting.builder()
                .title("This is post one")
                .content("This post is fantasic")
                .like(true)
                .user(user1)
                .build();
        postRepository.save(posting1);
        Posting posting2 = Posting.builder()
                .title("This is post one")
                .content("This post is fantasic")
                .like(true)
                .user(user1)
                .build();
        postRepository.save(posting1);
        Posting posting3 = Posting.builder()
                .title("This is post one")
                .content("This post is fantasic")
                .like(true)
                .user(user1)
                .build();
        postRepository.save(posting1);

        Posting posting4 = Posting.builder()
                .title("This is post 2")
                .content("This post is fantasic")
                .like(true)
                .user(user2)
                .build();
        postRepository.save(posting2);
        Posting posting5 = Posting.builder()
                .title("This is post 2")
                .content("This post is fantasic")
                .like(true)
                .user(user2)
                .build();
        postRepository.save(posting2);
        Posting posting6 = Posting.builder()
                .title("This is post 2")
                .content("This post is fantasic")
                .like(true)
                .user(user2)
                .build();
        postRepository.save(posting2);

        Posting posting7 = Posting.builder()
                .title("This is post 3")
                .content("This post is fantasic")
                .like(true)
                .user(user3)
                .build();
        postRepository.save(posting3);
        Posting posting8 = Posting.builder()
                .title("This is post 3")
                .content("This post is fantasic")
                .like(true)
                .user(user3)
                .build();
        postRepository.save(posting3);
        Posting posting9 = Posting.builder()
                .title("This is post 3")
                .content("This post is fantasic")
                .like(true)
                .user(user3)
                .build();
        postRepository.save(posting3);
    }
}
