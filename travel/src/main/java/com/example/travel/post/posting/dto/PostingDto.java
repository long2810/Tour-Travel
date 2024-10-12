package com.example.travel.post.posting.dto;

import com.example.travel.post.posting.entity.Posting;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
public class PostingDto {
    private Long id;
    private String title;
    private String content;
    private Long writerId;

    public static PostingDto fromEntity(Posting posting) {
        PostingDto postingDto = PostingDto.builder()
                .id(posting.getId())
                .title(posting.getTitle())
                .content(posting.getContent())
                .writerId(posting.getWriter().getId())
                .build();
        return postingDto;
    }
}
