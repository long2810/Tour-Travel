package com.example.travel.post.location.entity;

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
public class Location extends BaseEntity {
    private String title;
    private String address;
    private String mapx;
    private String mapy;
    @ManyToOne
    private Posting posting;
}
