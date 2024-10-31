package com.example.travel.post.emotion;

import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.post.posting.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepo extends JpaRepository<Like, Long> {
    boolean existsByPostingAndUser(Posting posting, UserEntity user);

    @Query("SELECT COUNT(*) from Like WHERE posting.id = :postId AND like = true")
    Long countLikesByPostId(@Param("postId") Long postId);
}
