package com.example.my_project2.Comment.dto;

import com.example.my_project2.Comment.entity.Comment;
import lombok.*;

@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private String ownerName;
    private Long posting_id;

    public static CommentDto fromEntity(Comment entity) {
        CommentDto dto = CommentDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .ownerName(entity.getOwnerName())
                .posting_id(entity.getPosting().getId())
                .build();
        return dto;
    }
}
