package com.example.travel.post.posting.dto;

import com.example.travel.post.posting.entity.ImagePosting;
import com.example.travel.post.posting.entity.Posting;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private final List<String> images = new ArrayList<>();

    public static PostingDto fromEntity(Posting posting) {
        PostingDto postingDto = PostingDto.builder()
                .id(posting.getId())
                .title(posting.getTitle())
                .content(posting.getContent())
                .writerId(posting.getWriter().getId())
                .build();
        for (ImagePosting image: posting.getImages()){
            postingDto.images.add(image.getLink());
        }
        return postingDto;
    }
}
