package com.example.my_project2.naver;

import com.example.my_project2.posting.entity.Posting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Naver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;
    private String destination;
    private int distance;
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    private Posting posting;
}
