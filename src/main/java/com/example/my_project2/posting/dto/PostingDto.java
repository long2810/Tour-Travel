package com.example.my_project2.posting.dto;

import com.example.my_project2.Comment.dto.CommentDto;
import com.example.my_project2.Comment.entity.Comment;
import com.example.my_project2.posting.entity.Image_Posting;
import com.example.my_project2.naver.Naver;
import com.example.my_project2.posting.entity.Posting;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@ToString
@Getter
public class PostingDto {
    private Long id;
    private String title;
    private String content;
    private boolean like;
    private String places;

    private Long userId;
    @Builder.Default
    private List<CommentDto> commentDtos =  new ArrayList<>();
    @Builder.Default
    private List<ImageDto> imageDtos  =  new ArrayList<>();

    public static PostingDto fromEntity(Posting entity) {
        PostingDto postingDto = PostingDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .like(entity.isLike())
                .places(entity.getPlaces())
                .userId(entity.getUser().getId())
                .build();
        for (Comment comment : entity.getComments()) {
            postingDto.getCommentDtos().add(CommentDto.fromEntity(comment));
        }
        for (Image_Posting image : entity.getImages()) {
            postingDto.getImageDtos().add(ImageDto.fromEntity(image));
        }
        return postingDto;
    }
}
