package com.example.travel.post.emotion;

import com.example.travel.authentication.component.UserComponent;
import com.example.travel.post.posting.entity.Posting;
import com.example.travel.post.posting.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepo likeRepo;
    private final PostRepo postRepo;
    private final UserComponent userComponent;
    public LikeDto create(LikeDto dto){
        Posting posting = postRepo.findById(dto.getPostingId())
                .orElseThrow(()-> new IllegalArgumentException("No exist posting"));
        Like like = Like.builder()
                .like(true)
                .user(userComponent.userLogin())
                .posting(posting)
                .build();
        return LikeDto.dto(likeRepo.save(like));
    }

//    public LikeDto checkLike (Long likeId){
//        Like like = likeRepo.findById(likeId).orElseThrow(
//                ()-> new IllegalArgumentException("No exist Like")
//        );
//        if (!like.getUser().getId().equals(userComponent.userLogin().getId()))
//            throw new IllegalArgumentException("Like is not available!!");
//        return LikeDto.dto(like);
//    }

    public boolean existLike(LikeDto dto){
        Posting posting = postRepo.findById(dto.getPostingId())
                .orElseThrow(()-> new IllegalArgumentException("No exist posting"));
        return likeRepo.existsByPostingAndUser(posting, userComponent.userLogin());
    }
    public void delete(Long likeId){
        likeRepo.deleteById(likeId);
    }

    public long getLikeCountByPostId(Long postId) {
        return likeRepo.countLikesByPostId(postId);
    }
}
