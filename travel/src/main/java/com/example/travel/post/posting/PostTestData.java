//package com.example.travel.post.posting;
//
//import com.example.travel.authentication.user.entity.UserEntity;
//import com.example.travel.authentication.user.repo.UserRepo;
//import com.example.travel.post.posting.entity.Posting;
//import com.example.travel.post.posting.repo.PostRepo;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class PostTestData {
//    private final PostRepo postRepo;
//    private final UserRepo userRepo;
//
//    public PostTestData(
//            PostRepo postRepo,
//            UserRepo userRepo
//    ) {
//        this.postRepo = postRepo;
//        this.userRepo = userRepo;
//        this.fixPost();
//    }
//
//    public void fixPost() {
//        UserEntity user1 = userRepo.findById(1L)
//                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));
//        UserEntity user2 = userRepo.findById(2L)
//                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));
//
//        Posting posting1 = Posting.builder()
//                .title("This is post one")
//                .content("This post is fantasic")
//                .writer(user1)
//                .build();
//        postRepo.save(posting1);
//        Posting posting2 = Posting.builder()
//                .title("This is post one")
//                .content("This post is fantasic")
//                .writer(user1)
//                .build();
//        postRepo.save(posting2);
//        Posting posting3 = Posting.builder()
//                .title("This is post one")
//                .content("This post is fantasic")
//                .writer(user1)
//                .build();
//        postRepo.save(posting3);
//
//        Posting posting4 = Posting.builder()
//                .title("This is post 2")
//                .content("This post is fantasic")
//                .writer(user2)
//                .build();
//        postRepo.save(posting4);
//        Posting posting5 = Posting.builder()
//                .title("This is post 2")
//                .content("This post is fantasic")
//                .writer(user2)
//                .build();
//        postRepo.save(posting5);
//        Posting posting6 = Posting.builder()
//                .title("This is post 2")
//                .content("This post is fantasic")
//                .writer(user2)
//                .build();
//        postRepo.save(posting6);
//
//
//    }
//}
