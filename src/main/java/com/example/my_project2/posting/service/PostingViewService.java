package com.example.my_project2.posting.service;

import com.example.my_project2.posting.dto.PostingDto;
import com.example.my_project2.posting.entity.Posting;
import com.example.my_project2.posting.repo.ImageRepository;
import com.example.my_project2.posting.repo.PostRepository;
import com.example.my_project2.service.WebService;
import com.example.my_project2.user.entity.UserEntity;
import com.example.my_project2.user.repo.UserRepo;
import com.example.my_project2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostingViewService {
    private final UserRepo userRepo;
    private final UserService userService;
    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final WebService webService;

    //read one posting
    public PostingDto readPosting(Long id) {
        Posting posting = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Posting does not exists!")
        );
        log.info(posting.getUser().getId().toString());
        return PostingDto.fromEntity(posting);
    }

    //read post List
    public List<PostingDto> readPostingList() {
        UserEntity curUser = webService.userLogin();
//        if(!curUser.getAuthorities().stream().anyMatch(authority -> authority.getRole().equals(Authority.Role.ROLE_ADMIN))) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }
        return postRepository.findAll().stream().map(PostingDto :: fromEntity).toList();
    }

    //Search Post

    public List<PostingDto> searchPostByUserId(Long userId) {
        // if current user is owner of post or admin so can read post list
        UserEntity curUser = webService.userLogin();
        UserEntity entity = userRepo.findById(userId)
                .orElseThrow(()-> new UsernameNotFoundException("Username does not exist"));

//        if(curUser.getAuthorities().stream().anyMatch(authority -> authority.getRole().equals(Authority.Role.ROLE_ADMIN)) || curUser.getId() == entity.getId()){
//            List<Posting> postings = postRepository.findAllByUserId(userId);
//            return postings.stream().map(PostingDto :: fromEntity).toList();
//        }
//        else {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
//        }

        List<Posting> postings = postRepository.findAllByUserId(userId);
        return postings.stream().map(PostingDto :: fromEntity).toList();
    }

    public List<PostingDto> searchPostByTitle(String title) {
        List<Posting> postings = postRepository.findAllByTitle(title);
        return postings.stream().map(PostingDto :: fromEntity).toList();
    }
}
