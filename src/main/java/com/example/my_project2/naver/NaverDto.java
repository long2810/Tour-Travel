package com.example.my_project2.naver;

import com.example.my_project2.posting.entity.Posting;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@ToString
@Getter
public class NaverDto {
    private Long id;
    private String position;
    private String destination;
    private int distance;
    private LocalDateTime time;
    private Posting posting;

    public static NaverDto from(Naver entity) {
        NaverDto dto = NaverDto.builder()
                .id(entity.getId())
                .position(entity.getPosition())
                .destination(entity.getDestination())
                .distance(entity.getDistance())
                .time(entity.getTime())
                .posting(entity.getPosting())
                .build();
        return dto;
    }
}
