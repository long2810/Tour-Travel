package com.example.my_project2.posting.entity;

import com.example.my_project2.Comment.entity.Comment;
import com.example.my_project2.naver.Naver;
import com.example.my_project2.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posting")
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    private Long id;

    private String title;
    private String content;
    private boolean like;
    private String places;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "posting",cascade = CascadeType.ALL)
    private final List<Comment> comments =  new ArrayList<>();

    @OneToMany(mappedBy = "posting",cascade = CascadeType.ALL)
    private final List<Image_Posting> images  =  new ArrayList<>();


}
