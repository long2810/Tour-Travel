package com.example.travel.post.emotion;

import com.example.travel.authentication.user.entity.UserEntity;
import com.example.travel.baseEntity.BaseEntity;
import com.example.travel.post.posting.entity.Posting;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Like extends BaseEntity {
    private boolean like;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private Posting posting;
}
