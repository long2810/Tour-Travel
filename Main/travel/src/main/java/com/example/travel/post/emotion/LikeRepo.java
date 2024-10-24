package com.example.travel.post.emotion;

import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.post.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepo extends JpaRepository<Like, Long> {
    boolean existsByPostingAndUser(Posting posting, UserEntity user);
}
